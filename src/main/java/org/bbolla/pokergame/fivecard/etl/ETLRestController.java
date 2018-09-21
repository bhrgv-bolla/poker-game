package org.bbolla.pokergame.fivecard.etl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class ETLRestController {

    @Autowired
    private BufferedETLProcessor etlProcessor;

    @PostMapping("/etl/start")
    public ResponseEntity<Object> startETL() {
        CompletableFuture future = etlProcessor.execute();
        return ResponseEntity.ok("Success; ETL In Progress");
    }
}
