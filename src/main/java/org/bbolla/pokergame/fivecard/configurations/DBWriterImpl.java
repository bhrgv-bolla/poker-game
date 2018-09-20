package org.bbolla.pokergame.fivecard.configurations;

import lombok.extern.slf4j.Slf4j;
import org.bbolla.pokergame.fivecard.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Write to a Database
 */
//@Component
@Slf4j
public class DBWriterImpl implements CombinationWriter {

    @Autowired
    private FiveCardPokerRepository fiveCardPokerRepository;


    @Override
    public void saveCombination(CombinationRecord combinationRecord) throws IOException {
        Card[] cards = combinationRecord.getCards();
        fiveCardPokerRepository.save(
                new FiveCardPokerDBRecord(
                        cards[0].toString(),
                        cards[1].toString(),
                        cards[2].toString(),
                        cards[3].toString(),
                        cards[4].toString(),
                        cards[5].toString(),
                        cards[6].toString(),
                        combinationRecord.getPokerHandRanking().rank(),
                        combinationRecord.getSameHandRanking())
        );
    }

    @Override
    public void init() throws IOException {
        long count = fiveCardPokerRepository.count();
        log.warn("Deleting {} database records from combinations.", count);
        fiveCardPokerRepository.deleteAll();
    }

    @Override
    public void close() throws IOException { //Ignore; once done.
        log.warn("Un-implemented close function");
    }
}
