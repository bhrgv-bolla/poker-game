package org.bbolla.pokergame.fivecard;

/**
 * Card Value With Priority.
 * @author bhargav
 *
 */
public enum CardType {

	TWO(new int[]{2}),
	THREE(new int[]{3}),
	FOUR(new int[]{4}),
	FIVE(new int[]{5}),
	SIX(new int[]{6}),
	SEVEN(new int[]{7}),
	EIGHT(new int[]{8}),
	NINE(new int[]{9}),
	TEN(new int[]{10}),
	J(new int[]{11}),
	Q(new int[]{12}),
	K(new int[]{13}),
	A(new int[]{1, 14});

	private int[] values;
	
	public int[] getValues() {
		return this.values;
	}
	
	public int getHighestVal() {
		if(this.values.length == 1) return this.values[0];
		else return this.values[1];
	}

	public boolean isHigher(CardType other) {
		return this.ordinal() > other.ordinal();
	}

	
	CardType(int[] values) {
		this.values = values;
	}
}
