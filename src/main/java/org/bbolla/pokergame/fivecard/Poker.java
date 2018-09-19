package org.bbolla.pokergame.fivecard;

import com.walmart.odr.framework.configurations.PokerHand;
import com.walmart.odr.framework.configurations.PokerHandRanking;
import com.walmart.odr.framework.configurations.RegularPoker;

public class Poker {

	
	public static void main(String[] args) {
		
		
		PokerHand pokerHand = new RegularPoker();
		
		PokerHandRanking pokerHandRank = pokerHand.findRank(null);

		System.out.println(pokerHandRank);
	}

}
