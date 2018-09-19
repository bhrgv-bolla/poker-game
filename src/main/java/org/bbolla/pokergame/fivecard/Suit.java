package org.bbolla.pokergame.fivecard;

public enum Suit {
	SPADE(1), HEART(2), CLUB(3), DIAMOND(4);
	
	private int rank;
	
	Suit(int rank) {
		this.rank = rank;
	}
	
	int compare (Suit other) {
		return this.rank - other.rank;
	}
	
	public int ranksHigherThan(Suit other) {
		return this.rank - other.rank;
	}
}
