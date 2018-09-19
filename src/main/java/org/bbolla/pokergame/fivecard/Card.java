package org.bbolla.pokergame.fivecard;

public class Card implements Comparable<Card>{
	
	@Override
	public String toString() {
		return "Card [type=" + type + ", value=" + value + "]";
	}


	public Card(Suit cardType, CardType cardValue) {
		this.type = cardType;
		this.value = cardValue;
	}
	Suit type;
	CardType value;


	@Override
	public int compareTo(Card other) {
		if(this.value.equals(other.value)) {
			return this.type.compare(other.type);
		} else {
			return this.value.compareTo(other.value);
		}
	}


}
