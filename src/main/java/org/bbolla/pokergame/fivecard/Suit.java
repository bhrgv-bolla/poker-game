package org.bbolla.pokergame.fivecard;

/**
 * Increasing order of poker suit
 */
public enum Suit {
	CLUB, DIAMOND, HEART, SPADE;

	public int ranksHigherThan(Suit other) {
		return this.ordinal() - other.ordinal();
	}
}
