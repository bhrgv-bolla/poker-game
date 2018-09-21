package org.bbolla.pokergame.fivecard.configurations;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bbolla.pokergame.fivecard.Card;

/**
 * POJO to hold a poker hand possibility
 */
@Data
@AllArgsConstructor
public class CombinationRecord implements Comparable<CombinationRecord> {
    private Card[] cards;
    private PokerHandRanking pokerHandRanking;
    private int sameHandRanking;

    @Override
    public int compareTo(CombinationRecord o) {
        if(this.pokerHandRanking.rank() == o.pokerHandRanking.rank()) {
            return this.sameHandRanking - o.sameHandRanking;
        } else {
            return this.pokerHandRanking.rank() - o.pokerHandRanking.rank();
        }
    }
}
