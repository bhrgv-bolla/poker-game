package org.bbolla.pokergame.fivecard.etl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * Simple ETL Process.
 */
@Component
@Slf4j
public class BufferedETLProcessor {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final int bufferSize = 1000000 * 100 * 2; // 1 million lines * 100 characters each * 2 bytes per character ( depends on charset )

    private static AtomicBoolean inProcessing = new AtomicBoolean(false);

    public CompletableFuture execute() {
        if(inProcessing.get()) throw new RuntimeException("Busy; Try Later");
        inProcessing.set(true);
        return CompletableFuture.runAsync(
                () -> {
                    try {
                        RandomAccessFile file = new RandomAccessFile("five_card_possibilities.txt", "r");
                        log.info("Seeking first line {}", file.readLine());
                        while(true) {
                            List<String> readLines = extract(file);
                            if(readLines.size() == 0) break;
                            else {
                                List<Object[]> records = transform(readLines);
                                load(records);
                            }
                        }
                    } catch (Exception e) {
                        inProcessing.set(false);
                        throw new RuntimeException("Exception completed ETL process", e);
                    }
                }
        );
    }

    private List<String> extract(RandomAccessFile file) throws IOException {
        String[] lines = FileUtils.readLines(file, bufferSize);
        if(lines == null) return null;
        log.info("Read {} lines", lines.length);
        return Arrays.asList(lines);
    }

    private List<Object[]> transform(List<String> records){
        return records.stream().map(r -> r.split(",")).collect(Collectors.toList());
    }

    private void load(List<Object[]> records) throws Exception {
        final String sql = "insert into poker.combination_matrix(?, ?, ?, ?, ?, ?, ?, hand_ranking, sub_rank) values (1,1,1,1,1,1,1,?,?)";
        int[] updateCount = jdbcTemplate.batchUpdate(sql, records);
        int sum = Arrays.stream(updateCount).sum();
        log.info("Updated {} records", sum);
    }
}
