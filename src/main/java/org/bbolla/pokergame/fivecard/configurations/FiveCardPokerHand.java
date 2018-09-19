package org.bbolla.pokergame.fivecard.configurations;

import org.bbolla.pokergame.fivecard.Card;

public class FiveCardPokerHand implements PokerHand<FiveCardPokerHandRanking> {

	@Override
	public FiveCardPokerHandRanking findRank(Card[] cards) {
		FiveCardPokerHandRanking handRanking = null;

		for (FiveCardPokerHandRanking ranking : FiveCardPokerHandRanking.values()) {

			if (ranking.check(cards) &&
					(handRanking == null ||ranking.ranksHigherThan(handRanking))
					) {
				handRanking = ranking;
			}
		}
		
		return handRanking;
	}

}
