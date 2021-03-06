package org.bbolla.pokergame.fivecard.configurations;

import lombok.extern.slf4j.Slf4j;
import org.bbolla.pokergame.fivecard.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DBReaderImpl implements CombinationReader {

    @Autowired
    FiveCardPokerRepository fiveCardPokerRepository;

    @Override
    public CombinationRecord getCombination(Card[] cards) throws IOException {
        return null; //TODO do i need this.
    }

    @Override
    public CombinationRecord[] getTopCombinations(Card[] originalCards, int page, int pageSize) throws IOException {
        Card[] cards = Arrays.copyOf(originalCards, originalCards.length);
        Arrays.sort(cards); //make sure the cards are sorted.
        Collection<String> pokerHand = Arrays.asList(cards).stream().map(Card::toString).collect(Collectors.toList());
        FiveCardPokerDBRecord[] response = fiveCardPokerRepository.findAllByCard1InOrCard2InOrCard3InOrCard4InOrCard5InOrCard6InOrCard7InOrderByHandRankingAscSubRankDesc(
                pokerHand,
                pokerHand,
                pokerHand,
                pokerHand,
                pokerHand,
                pokerHand,
                pokerHand,
                PageRequest.of(page, pageSize)
        ).toArray(new FiveCardPokerDBRecord[]{});

        return HelperUtils.convertFromDBRecord(response);
    }

}
