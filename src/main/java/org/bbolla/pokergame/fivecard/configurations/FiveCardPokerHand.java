package org.bbolla.pokergame.fivecard.configurations;

import org.bbolla.pokergame.fivecard.Card;
import org.springframework.stereotype.Component;

@Component
public class FiveCardPokerHand implements PokerHand<FiveCardPokerHandRanking> {

	@Override
	public FiveCardPokerHandRanking findRank(Card[] cards) {
		for (FiveCardPokerHandRanking ranking : FiveCardPokerHandRanking.values()) {
			if (ranking.check(cards)) {
				return ranking;
			}
		}
		return FiveCardPokerHandRanking.HIGH_CARD;
	}

}
