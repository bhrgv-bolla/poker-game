package org.bbolla.pokergame.fivecard;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RequestMapping("/poker/fivecard/")
@RestController
public interface FiveCardRestController {
    @RequestMapping(value = "suggestions", method = RequestMethod.POST)
    ResponseEntity<Object> getSuggestions(@RequestBody Card[] cards);

    @RequestMapping(value = "rank", method = RequestMethod.POST)
    ResponseEntity<Object> getCurrentHandRanking(@RequestBody Card[] cards);


    @RequestMapping(value = "generate_possibilities", method = RequestMethod.POST)
    ResponseEntity<Object> generateAllPossibilities() throws IOException;
}
