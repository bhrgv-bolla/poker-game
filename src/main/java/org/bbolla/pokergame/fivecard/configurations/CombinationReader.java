package org.bbolla.pokergame.fivecard.configurations;

import org.bbolla.pokergame.fivecard.Card;

import java.io.IOException;

public interface CombinationReader<$ResultType> {

    $ResultType getCombination(Card[] cards) throws IOException;

    $ResultType[] getTopCombinations(Card[] cards, int page, int pageSize) throws IOException;

    ConfidenceStats<PokerHandRanking> getConfidenceStats(Card[] cards) throws IOException;

}
