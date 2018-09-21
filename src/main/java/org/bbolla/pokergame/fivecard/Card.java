package org.bbolla.pokergame.fivecard;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Card implements Comparable<Card>{

	public Card(@NonNull Suit cardType,@NonNull CardValue cardValue) {
		this.type = cardType;
		this.value = cardValue;
	}


	private Card(@NonNull String cardType, @NonNull String cardValue) {
		this.type = Suit.valueOf(cardType);
		this.value = CardValue.valueOf(cardValue);
	}

	Suit type;
	CardValue value;

	@Override
	public String toString() {
		return this.type.name() + "_" + this.value.name();
	}

	/**
	 * Returns a Card Object from a string representation of the same.
	 * @param card
	 * @return
	 */
	public static final Card fromString(String card) {
		String[] details = card.split("_");
		return new Card(details[0], details[1]);
	}

	@Override
	public int compareTo(Card other) {
		if(this.value.equals(other.value)) {
			return this.type.compareTo(other.type);
		} else {
			return this.value.compareTo(other.value);
		}
	}


	public boolean hasSameValue(Card previous) {
		return this.getValue().equals(previous.getValue());
	}
}
