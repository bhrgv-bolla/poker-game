package org.bbolla.pokergame.fivecard.configurations;

import com.walmart.odr.framework.Card;

public class RegularPoker implements PokerHand<RegularPokerHandRanking> {

	@Override
	public RegularPokerHandRanking findRank(Card[] cards) {
		RegularPokerHandRanking handRanking = null;

		for (RegularPokerHandRanking ranking : RegularPokerHandRanking.values()) {

			if (ranking.check(cards) &&
					(handRanking == null ||ranking.ranksHigherThan(handRanking))
					) {
				handRanking = ranking;
			}
		}
		
		return handRanking;
		
	}

}
