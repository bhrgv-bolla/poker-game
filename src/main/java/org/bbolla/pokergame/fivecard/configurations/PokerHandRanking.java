package org.bbolla.pokergame.fivecard.configurations;


import org.bbolla.pokergame.fivecard.Card;

public interface PokerHandRanking {

	boolean check(Card[] cards);

	int subRank(Card[] cards);

	int rank();

	String toString();

	PokerHandRanking fromRank(int rank);
	

	/**
	 * Helper function to compare highest of two rankings.
	 * @param otherHandRanking
	 * @return
	 */
	default boolean ranksHigherThan(PokerHandRanking otherHandRanking) {
		return this.rank() < otherHandRanking.rank();
	}
}
