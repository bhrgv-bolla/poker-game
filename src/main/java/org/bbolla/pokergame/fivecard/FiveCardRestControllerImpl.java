package org.bbolla.pokergame.fivecard;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class FiveCardRestControllerImpl implements FiveCardRestController {

    @Autowired
    PokerPossibilitiesGenerator pokerPossibilitiesGenerator;

    @Override
    public ResponseEntity<Object> getSuggestions(Card[] cards) {
        return null;
    }

    @Override
    public ResponseEntity<Object> getCurrentHandRanking(Card[] cards) {
        return null;
    }

    @Override
    public ResponseEntity<Object> generateAllPossibilities() throws IOException {
        if(!pokerPossibilitiesGenerator.isFree()) return ResponseEntity.badRequest().body("Already processing. Try Later");
        CompletableFuture future = CompletableFuture.runAsync(
                () -> {
                    try {
                        pokerPossibilitiesGenerator.generate();
                    } catch (IOException e) {
                        log.error("Exception while generating", e);
                    }
                }
        );

        return ResponseEntity.ok("Generating");
    }
}
