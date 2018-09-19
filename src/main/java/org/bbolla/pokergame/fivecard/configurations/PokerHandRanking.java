package org.bbolla.pokergame.fivecard.configurations;


import org.bbolla.pokergame.fivecard.Card;

public interface PokerHandRanking {

	public boolean check(Card[] cards);

	public int rank();
	

	/**
	 * Helper function to compare highest of two rankings.
	 * @param otherHandRanking
	 * @return
	 */
	public default boolean ranksHigherThan(PokerHandRanking otherHandRanking) {
		return this.rank() < otherHandRanking.rank();
	}
}
