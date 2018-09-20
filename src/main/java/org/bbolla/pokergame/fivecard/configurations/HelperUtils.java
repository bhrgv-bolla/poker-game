package org.bbolla.pokergame.fivecard.configurations;

import org.bbolla.pokergame.fivecard.Card;

import java.util.Arrays;
import java.util.stream.Collectors;

public class HelperUtils {


    public static CombinationRecord[] convertFromDBRecord(FiveCardPokerDBRecord[] fiveCardPokerDBRecords) {
        return Arrays.stream(fiveCardPokerDBRecords).map(rec -> convertFromDBRecord(rec)).collect(Collectors.toList()).toArray(new CombinationRecord[]{});
    }

    public static CombinationRecord convertFromDBRecord(FiveCardPokerDBRecord fiveCardPokerDBRecord) {
        return new CombinationRecord(
                fromCardStrings(new String[]{
                        fiveCardPokerDBRecord.getCard1(),
                        fiveCardPokerDBRecord.getCard2(),
                        fiveCardPokerDBRecord.getCard3(),
                        fiveCardPokerDBRecord.getCard4(),
                        fiveCardPokerDBRecord.getCard5(),
                        fiveCardPokerDBRecord.getCard6(),
                        fiveCardPokerDBRecord.getCard7()}),
                FiveCardPokerHandRanking.FLUSH.fromRank(fiveCardPokerDBRecord.getHandRanking()),
                fiveCardPokerDBRecord.getSubRank()
        );
    }


    public static Card[] fromCardStrings(String[] cards) {
        return Arrays.stream(cards).map(str -> Card.fromString(str)).collect(Collectors.toList()).toArray(new Card[]{});
    }
}
