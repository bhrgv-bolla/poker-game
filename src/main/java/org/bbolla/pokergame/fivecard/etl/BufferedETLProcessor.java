package org.bbolla.pokergame.fivecard.etl;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.bbolla.pokergame.fivecard.Card;
import org.bbolla.pokergame.fivecard.Deck;
import org.bbolla.pokergame.fivecard.configurations.FiveCardPokerHandRanking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.LinkedHashMap;
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
    private final int bufferSize = 1000000 * 10; // 1 million lines * 100 characters each * 2 bytes per character ( depends on charset )

    private static AtomicBoolean inProcessing = new AtomicBoolean(false);

    private static final LinkedHashMap<String, Integer> cardMap;
    private static final String insertSql;

    static {
        cardMap = Maps.newLinkedHashMap();
        int i=0;
        for(Card card : Deck.getCards()) {
            cardMap.put(card.toString(), i++);
        }
        String[] questionMarks = new String[54];
        Arrays.fill(questionMarks, '?');
        insertSql = "insert into poker.combination_matrix("+ String.join(",", cardMap.keySet()) +
                    ", hand_ranking, sub_rank) values ("+String.join(",", questionMarks)+")";
    }

    public CompletableFuture execute() {
        if(inProcessing.get()) throw new RuntimeException("Busy; Try Later");
        inProcessing.set(true);
        jdbcTemplate.execute("TRUNCATE poker.combination_matrix"); //reset state; TODO make backup before resetting
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
        List<String[]> recs =  records.stream().map(r -> r.split(",")).collect(Collectors.toList());
        return recs.stream().map(
                rec -> {
                    Object[] args = new Object[54];
                    String[] keys = Arrays.copyOf(rec, 7);
                    args[52] = FiveCardPokerHandRanking.rankOf(rec[7]);
                    args[53] = rec[8];
                    for(String key: keys) {
                        int idx = cardMap.get(key);
                        args[idx] = 1;
                    }
                    return args;
                }
        ).collect(Collectors.toList());
    }

    private void load(List<Object[]> records) throws Exception {
        int[] updateCount = jdbcTemplate.batchUpdate(insertSql, records);
        int sum = Arrays.stream(updateCount).sum();
        log.info("Updated {} records", sum);
    }

}
