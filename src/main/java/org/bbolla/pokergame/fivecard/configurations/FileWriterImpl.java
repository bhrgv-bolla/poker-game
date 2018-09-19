package org.bbolla.pokergame.fivecard.configurations;

import lombok.extern.slf4j.Slf4j;
import org.bbolla.pokergame.fivecard.Card;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@Component("fileWriter")
@Slf4j
public class FileWriterImpl implements CombinationWriter {


    private static final File file = new File("five_card_possibilities.txt");
    private FileWriter fileWriter;

    public void writeToFile(String line) throws IOException {
        fileWriter.append(line + System.lineSeparator());
    }

    @Override
    public void saveCombination(Card[] cards) throws IOException {
        writeToFile(Arrays.stream(cards)
                .map(card -> card.toString())
                .collect(Collectors.joining(",")));
    }

    @Override
    public void init() throws IOException {
        log.info("Writing to file : {}", file.getAbsolutePath());
        file.delete();
        file.createNewFile();
        fileWriter = new FileWriter(file);
        fileWriter.write("Generating All Possibilities : " + new Date() + System.lineSeparator());
    }

    @Override
    public void close() throws IOException {
        fileWriter.close();
    }

}
