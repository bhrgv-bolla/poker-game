package org.bbolla.pokergame.fivecard.configurations;

import lombok.Data;
import org.bbolla.pokergame.fivecard.Card;

/**
 * POJO to hold a poker hand possibility
 */
@Data
public class CombinationRecord {
    private Card[] cards;
    private PokerHandRanking pokerHandRanking;
    private int sameHandRanking;
}