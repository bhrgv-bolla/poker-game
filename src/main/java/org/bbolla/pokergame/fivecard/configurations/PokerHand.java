package org.bbolla.pokergame.fivecard.configurations;

import org.bbolla.pokergame.fivecard.Card;

import java.util.List;

/**
 * For generic any card poker.
 *
 * @author bhargav
 */
public interface PokerHand<T extends PokerHandRanking> {

    T findRank(Card[] cards);

    List<CombinationRecord> betterHands(Card[] cards);

}
