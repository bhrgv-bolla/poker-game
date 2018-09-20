package org.bbolla.pokergame.fivecard;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RequestMapping("/poker/fivecard/")
@RestController
public interface FiveCardRestController {
    @RequestMapping(value = "suggestions", method = RequestMethod.POST)
    ResponseEntity<Object> getSuggestions(@RequestBody SuggestionsRequest request) throws IOException;

    @RequestMapping(value = "rank", method = RequestMethod.POST)
    ResponseEntity<Object> getCurrentHandRanking(@RequestBody Card[] cards);


    @RequestMapping(value = "generate_possibilities", method = RequestMethod.POST)
    ResponseEntity<Object> generateAllPossibilities() throws IOException;

    @GetMapping(value = "/cards")
    default ResponseEntity<Object> getDeck() {
        return ResponseEntity.ok(Deck.getCards());
    }
}
