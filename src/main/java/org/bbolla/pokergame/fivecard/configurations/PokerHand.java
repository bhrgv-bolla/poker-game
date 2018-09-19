package org.bbolla.pokergame.fivecard.configurations;

import org.bbolla.pokergame.fivecard.Card;

/**
 * For generic any card poker.
 *
 * @author bhargav
 */
public interface PokerHand<T extends PokerHandRanking> {

    T findRank(Card[] cards);

}
