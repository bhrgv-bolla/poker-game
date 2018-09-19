package org.bbolla.pokergame.fivecard.configurations;

import java.util.function.Function;

import org.bbolla.pokergame.fivecard.Card;

public enum RegularPokerHandRanking implements PokerHandRanking {

	ROYAL_FLUSH(1, cards -> {
		return true;
	}),

	STRAIGHT_FLUSH(2, cards -> {
		return true;
	}),

	FOUR_OF_A_KIND(3, cards -> {
		return true;
	}),
	
	FULL_HOUSE(4, cards -> {
		return true;
	}),
	
	FLUSH(5, cards -> {
		return true;
	}),
	
	STRAIGHT(6, cards -> {
		return true;
	}),
	
	THREE_OF_A_KIND(7, cards -> {
		return true;
	}),
	
	TWO_PAIR(8, cards -> {
		return true;
	}),

	ONE_PAIR(9, cards -> {
		return true;
	}),

	HIGH_CARD(10, cards -> {
		return true;
	});

	private int rank;
	private Function<Card[], Boolean> methodToCheck;

	@Override
	public boolean check(Card[] cards) {
		if(cards == null || cards.length < 2) return false;
		return this.methodToCheck.apply(cards);
	}
	
	@Override
	public int rank() {
		return this.rank;
	}
	

	RegularPokerHandRanking(int rank, Function<Card[], Boolean> methodToCheck) {
		this.rank = rank;
		this.methodToCheck = methodToCheck;
	}

}
