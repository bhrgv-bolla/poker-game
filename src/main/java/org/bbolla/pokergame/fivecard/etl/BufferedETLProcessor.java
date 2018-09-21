package org.bbolla.pokergame.fivecard.etl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.bbolla.pokergame.fivecard.Card;
import org.bbolla.pokergame.fivecard.Deck;
import org.bbolla.pokergame.fivecard.configurations.FiveCardPokerHandRanking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
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
    private final int bufferSize = 1000000 * 10; // ~10mb

    private static AtomicBoolean inProcessing = new AtomicBoolean(false);

    private static final LinkedHashMap<String, Integer> cardMap;
    private static final String insertSql;
    private static final List<String> errors;

    static {
        cardMap = Maps.newLinkedHashMap();
        errors = Lists.newArrayList();
        int i=0;
        for(Card card : Deck.getCards()) {
            cardMap.put(card.toString(), i++);
        }
        String[] questionMarks = new String[54];
        Arrays.fill(questionMarks, "?");
        insertSql = "insert into poker.combination_matrix_test("+ String.join(",", cardMap.keySet()) +
                    ", hand_ranking, sub_rank) values ("+String.join(",", questionMarks)+")";
        log.info("insert sql for poker combinations matrix: {}", insertSql);
    }

    public CompletableFuture execute() {
        if(inProcessing.get()) throw new RuntimeException("Busy; Try Later");
        inProcessing.set(true);
        jdbcTemplate.execute("TRUNCATE poker.combination_matrix_test"); //reset state; TODO make backup before resetting
        return CompletableFuture.runAsync(
                () -> {
                    try {
                        RandomAccessFile file = new RandomAccessFile("five_card_possibilities.txt", "r");
                        log.info("Seeking first line {}", file.readLine());
                        while(true) {
                            long offsetBefore = file.getFilePointer();
                            List<String> readLines = extract(file);
                            long offsetAfter = file.getFilePointer();
                            if(readLines.size() == 0) break;
                            else {
                                List<Object[]> records = transform(readLines);
                                load(records);
                            }
                            writeAnyErrorsQuietly(offsetBefore, offsetAfter);
                        }
                    } catch (Exception e) {
                        inProcessing.set(false);
                        log.error("Exception occurred; ETL Not completed", e);
                        throw new RuntimeException("Exception completed ETL process", e);
                    }
                }
        );
    }

    private void writeAnyErrorsQuietly(Long offsetBefore, Long offsetAfter) {
        try {
            if(errors.isEmpty()) return;
            File file = new File("errors-"+new Date().toString()+".txt");
            file.createNewFile();
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println(offsetBefore.toString());
            printWriter.println(offsetAfter.toString());
            for(String errorRecord: errors) {
                printWriter.println(errorRecord);
            }
            printWriter.close();
        } catch (IOException e) {
            log.error("While writing error records: {} {} {}", offsetBefore, offsetAfter, errors, e);
        } finally {
            errors.clear();
        }
    }

    private List<String> extract(RandomAccessFile file) throws IOException {
        String[] lines = FileUtils.readLines(file, bufferSize);
        if(lines == null) return null;
        log.info("Read {} lines", lines.length);
        return Arrays.asList(lines);
    }

    private List<Object[]> transform(List<String> records){
        log.info("records un-parsed : {}", records.get(0));
        return records.stream().map(
                record -> {
                    try {
                        String[] rec = record.split(",");
                        Object[] args = new Object[54];
                        Arrays.fill(args, 0);
                        String[] keys = Arrays.copyOf(rec, 7);
                        args[52] = FiveCardPokerHandRanking.rankOf(rec[7]);
                        args[53] = Integer.parseInt(rec[8]);
                        for (String key : keys) {
                            int idx = cardMap.get(key);
                            args[idx] = 1;
                        }
                        return args;
                    } catch (Exception ex) {
                        log.error("Error while transforming record {}", record);
                        errors.add(record);//collect errors.
                        return null;
                    }
                }
        ).filter(r -> null != r).collect(Collectors.toList());
    }

    private void load(List<Object[]> records) throws Exception {
        log.info("records sample : {}", Arrays.deepToString(records.get(0)));
        int[] updateCount = jdbcTemplate.batchUpdate(insertSql, records);
        int sum = Arrays.stream(updateCount).sum();
        log.info("Updated {} records", sum);
    }

}
