package org.bbolla.pokergame.fivecard;

import java.util.ArrayList;
import java.util.List;

public class Deck {

	private final static List<Card>  cards = new ArrayList<>();
	
	static {
		for(Suit cardType : Suit.values()) {
			for(CardType cardValue: CardType.values()) {
				cards.add(new Card(cardType, cardValue));
			}
		}
	}
	
	public static List<Card> getCards() {
		return cards;
	}
	
	public static void main(String[] args) {
		System.out.println(cards);
	}
	
}
