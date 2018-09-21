package org.bbolla.pokergame.fivecard.configurations;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.bbolla.pokergame.fivecard.Card;
import org.bbolla.pokergame.fivecard.Deck;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
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

	@Override
	public List<CombinationRecord> betterHands(Card[] cards) {
		if(cards.length < 2) return null;
		if(cards.length > 7) throw new IllegalStateException("Max 7 cards including open and player cards");
		//Given x cards what all combinations are possible that are better.
		FiveCardPokerHandRanking currentRanking = findRank(cards);
		List<Card> allCards = Lists.newArrayList(Deck.getCards());

		for(int i=0; i<cards.length; i++) { //remove all cards you see on the table; because those combinations are taken / fixed.
			allCards.remove(cards[i]);
		}
		List<Card> cardList = Lists.newArrayList(cards);
		cardList.remove(0);
		cardList.remove(0);

		log.info("Total Cards in deck are : {}", allCards.size());

		Card[] combinations = Arrays.copyOf(cardList.toArray(new Card[]{}), cards.length);
		List<CombinationRecord> combinationList = Lists.newArrayList();
		generateCombinations(allCards, 0, cards.length, cards.length - 2, combinations, combinationList);
		return combinationList
				.stream()
				.filter(c -> c.getPokerHandRanking().ranksHigherThan(currentRanking))
				.collect(Collectors.toList());
	}


	private void generateCombinations(List<Card> cards, int deckIdx, int maxOpenCards, int currentCardIdx,
									  Card[] currentCards, List<CombinationRecord> combinationRecordList) {
		if (deckIdx >= cards.size()) {
			if (currentCardIdx == maxOpenCards) {
				combinationRecordList.add(getCombinationRecord(currentCards));
			}
			return;
		} else if (currentCardIdx == maxOpenCards) {
			combinationRecordList.add(getCombinationRecord(currentCards));
			return;
		}

		for (int i = deckIdx; i < cards.size(); i++) {
			currentCards[currentCardIdx] = cards.get(i);
			generateCombinations(cards, i + 1, maxOpenCards, currentCardIdx + 1, currentCards, combinationRecordList);
		}

	}


	/**
	 * Helper function to prepare a combination record.
	 *
	 * @param currentCards
	 * @return
	 */
	private CombinationRecord getCombinationRecord(Card[] currentCards) {
		PokerHandRanking handRanking = findRank(currentCards);
		return new CombinationRecord(Arrays.copyOf(currentCards, currentCards.length), handRanking, handRanking.subRank(currentCards));
	}

}
