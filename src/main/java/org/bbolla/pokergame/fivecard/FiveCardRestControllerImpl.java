package org.bbolla.pokergame.fivecard;

import lombok.extern.slf4j.Slf4j;
import org.bbolla.pokergame.fivecard.configurations.CombinationReader;
import org.bbolla.pokergame.fivecard.configurations.CombinationRecord;
import org.bbolla.pokergame.fivecard.configurations.PokerHand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class FiveCardRestControllerImpl implements FiveCardRestController {

    @Autowired
    PokerPossibilitiesGenerator pokerPossibilitiesGenerator;

    @Autowired
    PokerHand pokerHand;

    @Autowired
    CombinationReader combinationReader;


    @Override
    public ResponseEntity<Object> getSuggestions(SuggestionsRequest request) throws IOException {
        Card[] cards = Deck.getCards().toArray(new Card[]{}); //TODO solve problem with Card not being able to read from input request.
        cards = Arrays.copyOf(cards, 5);
//        CombinationRecord[] response = combinationReader.getTopCombinations(Arrays.copyOf(cards, 7), 1, 20);
        List<CombinationRecord> combinationRecordList = pokerHand.betterHands(cards);
        Collections.sort(combinationRecordList);
        int high = 10 > combinationRecordList.size()? combinationRecordList.size(): 10;
        return ResponseEntity.ok(new Object[]{cards, combinationRecordList.size(), combinationRecordList.subList(0, high)});
    }

    @Override
    public ResponseEntity<Object> getCurrentHandRanking(Card[] cards) {
        return ResponseEntity.ok(pokerHand.findRank(cards));
    }

    @Override
    public ResponseEntity<Object> generateAllPossibilities() throws IOException {
        if(!pokerPossibilitiesGenerator.isFree()) return ResponseEntity.badRequest().body("Already processing. Try Later");
        CompletableFuture future = CompletableFuture.runAsync(
                () -> {
                    try {
                        pokerPossibilitiesGenerator.generate();
                    } catch (Exception e) {
                        log.error("Exception while generating", e);
                    }
                }
        );

        return ResponseEntity.ok("Generating");
    }
}
