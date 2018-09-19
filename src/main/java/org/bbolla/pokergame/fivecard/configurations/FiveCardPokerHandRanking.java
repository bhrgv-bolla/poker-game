package org.bbolla.pokergame.fivecard.configurations;

import com.google.common.collect.Maps;
import lombok.NonNull;
import org.bbolla.pokergame.fivecard.Card;
import org.bbolla.pokergame.fivecard.CardType;
import org.bbolla.pokergame.fivecard.Suit;

import java.util.*;
import java.util.function.Function;

public enum FiveCardPokerHandRanking implements PokerHandRanking {

    ROYAL_FLUSH(1, cards -> {
        if (cards.length < 5) return false;
        Card lowest = null, highest = null;
        int total = 0;
        for (Card card : cards) {
            if (card.getType().equals(Suit.HEART)) {
                total++;

                if (lowest == null || lowest.getValue().isHigher(card.getValue())) {
                    lowest = card;
                }

                if (highest == null || card.getValue().isHigher(highest.getValue())) {
                    highest = card;
                }
            }
        }

        if (total < 5
                || !lowest.getValue().equals(CardType.TEN)
                || !highest.getValue().equals(CardType.A)) return false;
        else return true;
    },
            cards -> {
                return -1;
            }),

    STRAIGHT_FLUSH(2, cards -> {
        return true;
    },
            cards -> {
                return 0;
            }),

    FOUR_OF_A_KIND(3, cards -> {
        return true;
    },
            cards -> {
                return 0;
            }),

    FULL_HOUSE(4, cards -> {
        return true;
    },
            cards -> {
                return 0;
            }),

    FLUSH(5, cards -> {
        return true;
    },
            cards -> {
                return 0;
            }),

    STRAIGHT(6, cards -> {
        return true;
    },
            cards -> {
                return 0;
            }),

    THREE_OF_A_KIND(7, cards -> {
        if (cards.length < 3) return false;
        Map<CardType, Integer> typeCount = Maps.newHashMap();
        for (Card card : cards) {
            if (typeCount.containsKey(card.getValue())) {
                typeCount.put(card.getValue(), typeCount.get(card.getValue()) + 1);
            } else {
                typeCount.put(card.getValue(), 1);
            }
        }

        if (typeCount.containsValue(3)) return true;
        else return true;
    },
            cards -> {
                if (cards.length < 3) return -1;
                Map<CardType, Integer> typeCount = Maps.newHashMap();
                for (Card card : cards) {
                    if (typeCount.containsKey(card.getValue())) {
                        typeCount.put(card.getValue(), typeCount.get(card.getValue()) + 1);
                    } else {
                        typeCount.put(card.getValue(), 1);
                    }
                }
                CardType highThreeOfAKind = null;

                for (Map.Entry<CardType, Integer> entry : typeCount.entrySet()) {
                    if (entry.getValue() == 3) {
                        if (highThreeOfAKind == null || !highThreeOfAKind.isHigher(entry.getKey())) {
                            highThreeOfAKind = entry.getKey();
                        }
                    }
                }
                if (highThreeOfAKind == null) return -1;
                else return highThreeOfAKind.getHighestVal();
            }),

    TWO_PAIR(8, cards -> {
        if (cards.length < 4) return false;
        Card previous = null;
        Set<Card> pairs = new HashSet<>();
        for (Card card : cards) {
            if (previous != null && card.hasSameValue(previous)) {
                pairs.add(card);
            }
            previous = card;
        }

        if (pairs.size() == 2) return true;
        else return false;
    },
            cards -> {
                if (cards.length < 4) return -1;
                Card previous = null;
                TreeSet<Card> pairs = new TreeSet<>();
                for (Card card : cards) {
                    if (previous != null && card.hasSameValue(previous)) {
                        pairs.add(card);
                    }
                    previous = card;
                }

                if (pairs.size() == 2) {
                    return pairs.last().getValue().getHighestVal() * 100
                            + pairs.first().getValue().getHighestVal() * 100;
                }
                return -1;
            }),

    ONE_PAIR(9, cards -> {
        if (cards.length < 2) return false;
        Card previous = null;
        for (Card card : cards) {
            if (previous != null && card.hasSameValue(previous)) {
                return true;
            }
            previous = card;
        }
        return false;
    },
            cards -> { //High pair wins
                Card previous = null;
                for (Card card : cards) {
                    if (previous != null && card.getValue().equals(previous.getValue())) {
                        return previous.getValue().getHighestVal();
                    }
                    previous = card;
                }
                return -1;
            }),

    HIGH_CARD(10, cards -> {
        if (cards.length < 1) return false;
        else return true;
    },
            cards -> {
                Card highestCard = cards[cards.length - 1];
                return highestCard.getValue().getHighestVal();
            });

    private int rank;
    private Function<Card[], Boolean> methodToCheck;
    private Function<Card[], Integer> subRank;

    FiveCardPokerHandRanking(int rank,
                             Function<Card[], Boolean> methodToCheck,
                             Function<Card[], Integer> subRank) {
        this.rank = rank;
        this.methodToCheck = methodToCheck;
        this.subRank = subRank;
    }

    @Override
    public boolean check(Card[] cards) {
        if (cards == null) return false;
        return this.methodToCheck.apply(cards);
    }

    @Override
    public int subRank(@NonNull Card[] cards) {
        Card[] newCopy = Arrays.copyOf(cards, cards.length);
        Arrays.sort(newCopy);
        return this.subRank(newCopy);
    }

    @Override
    public int rank() {
        return this.rank;
    }

}
