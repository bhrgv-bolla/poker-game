package org.bbolla.pokergame.fivecard;

import org.bbolla.pokergame.fivecard.configurations.PokerHand;
import org.bbolla.pokergame.fivecard.configurations.PokerHandRanking;
import org.bbolla.pokergame.fivecard.configurations.FiveCardPokerHand;

public class Poker {


    public static void main(String[] args) {


        PokerHand pokerHand = new FiveCardPokerHand();

        PokerHandRanking pokerHandRank = pokerHand.findRank(null);

        System.out.println(pokerHandRank);
    }

}
