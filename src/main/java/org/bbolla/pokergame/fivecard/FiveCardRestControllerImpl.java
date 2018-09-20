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

        CombinationRecord[] response = combinationReader.getTopCombinations(Arrays.copyOf(cards, 7), 1, 20);
        return ResponseEntity.ok(response);
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
