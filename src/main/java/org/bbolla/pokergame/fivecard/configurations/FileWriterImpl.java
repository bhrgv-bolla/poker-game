package org.bbolla.pokergame.fivecard.configurations;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.bbolla.pokergame.fivecard.Card;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component("fileWriter")
@Slf4j
public class FileWriterImpl implements CombinationWriter {

    private static final File file = new File("five_card_possibilities.txt");
    private FileWriter fileWriter;
    private static final List<String> pendingWrites = Lists.newArrayList();
    private static final int BULK_WRITE_THRESHOLD = 1000000;
    private static int totalWritten = 0;

    public void writeToFile(String line) throws IOException {
        if(pendingWrites.size() >= BULK_WRITE_THRESHOLD) {
            writePendingRecords();
        } else {
            pendingWrites.add(line);
        }
    }

    @Override
    public void saveCombination(CombinationRecord combinationRecord) throws IOException {
        Card[] cards = combinationRecord.getCards();
        writeToFile(Arrays.stream(cards)
                .map(card -> card.toString())
                .collect(Collectors.joining(",")) + "," + combinationRecord.getPokerHandRanking().toString()
        +","+combinationRecord.getSameHandRanking());
    }

    @Override
    public void init() throws IOException {
        log.info("Writing to file : {}", file.getAbsolutePath());
        pendingWrites.clear();
        totalWritten = 0;
        file.delete();
        file.createNewFile();
        fileWriter = new FileWriter(file);
        fileWriter.write("Generating All Possibilities : " + new Date() + System.lineSeparator());
    }

    @Override
    public void close() throws Exception {
        writePendingRecords();
        fileWriter.flush();
        fileWriter.close();
    }

    private void writePendingRecords() throws IOException {
        if(pendingWrites.size() > 0) {
            totalWritten += pendingWrites.size();
            log.info("Writing records : {}; totalWritten: {}", pendingWrites.size(), totalWritten);
            fileWriter.write(String.join(System.lineSeparator(), pendingWrites));
            pendingWrites.clear();
        }
    }

}
