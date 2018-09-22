package org.bbolla.pokergame.fivecard.configurations;

import org.bbolla.pokergame.fivecard.Card;

import java.io.IOException;

public interface CombinationReader {
    CombinationRecord getCombination(Card[] cards) throws IOException;

    CombinationRecord[] getTopCombinations(Card[] cards, int page, int pageSize) throws IOException;

}