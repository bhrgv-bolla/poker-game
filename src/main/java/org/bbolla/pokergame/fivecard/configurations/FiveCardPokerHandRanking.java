package org.bbolla.pokergame.fivecard.configurations;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.NonNull;
import org.bbolla.pokergame.fivecard.Card;
import org.bbolla.pokergame.fivecard.CardValue;
import org.bbolla.pokergame.fivecard.Suit;

import java.util.*;
import java.util.function.Function;

public enum FiveCardPokerHandRanking implements PokerHandRanking {

    ROYAL_FLUSH(1, cards -> {
        if (cards.length < 5) return false;
        Map<Suit, List<Card>> suitCount = Maps.newHashMap();

        for(Card card: cards){
            Suit suit = card.getType();
            if(suitCount.containsKey(suit)) {
                suitCount.get(suit).add(card);
            } else {
                suitCount.put(suit, Lists.newArrayList(card));
            }
        }

        for(Map.Entry<Suit, List<Card>> entry : suitCount.entrySet()) {
            if(entry.getValue().size() >= 5) {
                List<Card> sameSuit = entry.getValue();
                Collections.sort(sameSuit);
                Card highest = sameSuit.get(sameSuit.size() - 1);//0,1,2,3,4
                Card lowest = sameSuit.get(sameSuit.size() - 5);//

                if(lowest.getValue().equals(CardValue.TEN)
                && highest.getValue().equals(CardValue.A)) return true;
            }
        }

        return false;
    },
            cards -> {
                return -1;
            }),

    STRAIGHT_FLUSH(2, cards -> { //Same suit and straight.
        if (cards.length < 5) return false;
        Map<Suit, List<Card>> suitMap = Maps.newHashMap();
        for (Card card : cards) {
            if (suitMap.containsKey(card.getType())) {
                suitMap.get(card.getType()).add(card);
            } else {
                suitMap.put(card.getType(), Lists.newArrayList(card));
            }
        }

        for (Map.Entry<Suit, List<Card>> entry : suitMap.entrySet()) {
            if (entry.getValue().size() >= 5) {
                return checkStraight(entry.getValue().toArray(new Card[]{}));
            }
        }

        return false;
    },
            cards -> {
                return -1;
            }),

    FOUR_OF_A_KIND(3, cards -> {
        if (cards.length < 4) return false;
        Map<CardValue, Integer> typeCount = Maps.newHashMap();
        for (Card card : cards) {
            if (typeCount.containsKey(card.getValue())) {
                typeCount.put(card.getValue(), typeCount.get(card.getValue()) + 1);
            } else {
                typeCount.put(card.getValue(), 1);
            }
        }

        if (typeCount.containsValue(4)) return true;
        else return false;
    },
            cards -> {
                if (cards.length < 4) return -1;
                Map<CardValue, Integer> typeCount = Maps.newHashMap();
                for (Card card : cards) {
                    if (typeCount.containsKey(card.getValue())) {
                        typeCount.put(card.getValue(), typeCount.get(card.getValue()) + 1);
                    } else {
                        typeCount.put(card.getValue(), 1);
                    }
                }
                CardValue highFourOfAKind = null;

                for (Map.Entry<CardValue, Integer> entry : typeCount.entrySet()) {
                    if (entry.getValue() == 4) {
                        if (highFourOfAKind == null || !highFourOfAKind.isHigher(entry.getKey())) {
                            highFourOfAKind = entry.getKey();
                        }
                    }
                }
                if (highFourOfAKind == null) return -1;
                else return highFourOfAKind.getHighestVal();

            }),

    FULL_HOUSE(4, cards -> {
        // 3 of a kind + A pair.
        if(cards.length < 5) return false;
        Map<CardValue, Integer> valueCount = Maps.newHashMap();
        for(Card card: cards) {
            if(valueCount.containsKey(card.getValue())) {
                valueCount.put(card.getValue(), valueCount.get(card.getValue()) + 1);
            } else {
                valueCount.put(card.getValue(), 1);
            }
        }

        List<Integer> values = Lists.newArrayList(valueCount.values());
        Collections.sort(values);
        if(values.size() >= 2) { //at min these values should be different.
            return values.get(values.size() - 1) >= 3 &&
                    values.get(values.size() - 2) >= 2;
        } else {
            return false;
        }
    },
            cards -> {
                return -1;
            }),

    //Five of same suit
    FLUSH(5, cards -> {
        if(cards.length < 5) return false;

        Map<Suit, Integer> suitCount = Maps.newHashMap();
        for(Card card: cards) {
            if(suitCount.containsKey(card.getType())) {
                suitCount.put(card.getType(), suitCount.get(card.getType()) + 1);
            } else {
                suitCount.put(card.getType(), 1);
            }
        }

        for(int count : suitCount.values()) {
            if(count >= 5) return true;
        }
        return false;
    },
            cards -> {
                return -1;
            }),

    STRAIGHT(6, cards -> {
        return checkStraight(cards);
    },
            cards -> {
                return -1;
            }),

    THREE_OF_A_KIND(7, cards -> {
        if (cards.length < 3) return false;
        Map<CardValue, Integer> typeCount = Maps.newHashMap();
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
                Map<CardValue, Integer> typeCount = Maps.newHashMap();
                for (Card card : cards) {
                    if (typeCount.containsKey(card.getValue())) {
                        typeCount.put(card.getValue(), typeCount.get(card.getValue()) + 1);
                    } else {
                        typeCount.put(card.getValue(), 1);
                    }
                }
                CardValue highThreeOfAKind = null;

                for (Map.Entry<CardValue, Integer> entry : typeCount.entrySet()) {
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
    private static Map<Integer, FiveCardPokerHandRanking> rankingMap = new HashMap<>();

    static {
        for(FiveCardPokerHandRanking ranking: FiveCardPokerHandRanking.values()) {
            rankingMap.put(ranking.rank, ranking);
        }
    }
    FiveCardPokerHandRanking(int rank,
                             Function<Card[], Boolean> methodToCheck,
                             Function<Card[], Integer> subRank) {
        this.rank = rank;
        this.methodToCheck = methodToCheck;
        this.subRank = subRank;
    }

    private static Boolean checkStraight(Card[] cards) {
        if (cards.length < 5) return false;
        TreeSet<Integer> allCardValues = Sets.newTreeSet();
        for (Card card : cards) {
            for (int val : card.getValue().getValues()) {
                allCardValues.add(val);
            }
        }

        List<Integer> distinctCardValues = Lists.newArrayList(allCardValues);

        if (distinctCardValues.size() < 5) return false;

        for (int i = 0, nextIdx = i + 4; nextIdx < distinctCardValues.size() && i < distinctCardValues.size(); i++) {
            if (distinctCardValues.get(nextIdx) == distinctCardValues.get(i) + 4) {
                return true;
            }
        }

        return false;
    }

    public static PokerHandRanking fromHandRank(Integer handRanking) {
        return rankingMap.get(handRanking);
    }

    @Override
    public boolean check(Card[] cards) {
        if (cards == null) return false;
        if (cards.length > 7) throw new IllegalStateException("Cards cannot be more than 7");
        return this.methodToCheck.apply(cards);
    }

    @Override
    public int subRank(@NonNull Card[] cards) {
        if (cards.length > 7) throw new IllegalStateException("Cards cannot be more than 7");
        Card[] newCopy = Arrays.copyOf(cards, cards.length);
        Arrays.sort(newCopy);
        return this.subRank.apply(newCopy);
    }

    @Override
    public int rank() {
        return this.rank;
    }

    @Override
    public String toString() {
        return this.name();
    }

    @Override
    public PokerHandRanking fromRank(int rank) {
        return fromHandRank(rank);
    }

}
