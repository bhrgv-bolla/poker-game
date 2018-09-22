package org.bbolla.pokergame.fivecard;

import org.bbolla.pokergame.fivecard.configurations.CombinationRecord;
import org.bbolla.pokergame.fivecard.configurations.FiveCardPokerHand;
import org.bbolla.pokergame.fivecard.configurations.PokerHand;

public class Poker {


    public static void main(String[] args) {


        PokerHand pokerHand = new FiveCardPokerHand();

        CombinationRecord pokerHandRank = pokerHand.findRank(null);

        System.out.println(pokerHandRank);
    }

}
