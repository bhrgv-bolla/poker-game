package org.bbolla.pokergame.fivecard;

import lombok.extern.slf4j.Slf4j;
import org.bbolla.pokergame.fivecard.configurations.CombinationWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Generates and stores all possibilities along with their rank into a text
 * file.
 *
 * @author bhargav
 */
@Component
@Slf4j
public class PokerPossibilitiesGenerator {

    static final AtomicBoolean inProgress = new AtomicBoolean(false);
    static int possibilities = 0;

    @Autowired
    CombinationWriter writer;

    public static void main(String[] args) throws IOException {
        PokerPossibilitiesGenerator generator = new PokerPossibilitiesGenerator();
        generator.generate();
    }

    public boolean isFree() {
        return !inProgress.get();
    }

    public void generate() throws IOException {
        if (inProgress.get()) throw new RuntimeException("Processing Previous Request, Try again later");
        inProgress.set(true);
        possibilities = 0;

        writer.init();
        List<Card> cards = Deck.getCards();
        generateCombinations(cards, 0, 7, 0, new Card[7]);
        writer.close();

        inProgress.set(false);
    }

    /**
     * Recursive method to generate card combinations given a deck of cards and max number of visible cards
     *
     * @param cards
     * @param deckIdx
     * @param maxOpenCards
     * @param currentCardIdx
     * @param currentCards
     */
    private void generateCombinations(List<Card> cards, int deckIdx, int maxOpenCards, int currentCardIdx,
                                      Card[] currentCards) throws IOException {

        if (deckIdx >= cards.size()) {
            if (currentCardIdx == maxOpenCards)
                possibilities++;
            log.info("Possiblities {}", possibilities);
            writer.saveCombination(currentCards);
            return;
        } else if (currentCardIdx == maxOpenCards) {
            possibilities++;
            log.info("Possiblities {}", possibilities);

            writer.saveCombination(currentCards);
            return;
        }

        for (int i = deckIdx; i < cards.size(); i++) {
            currentCards[currentCardIdx] = cards.get(i);
            generateCombinations(cards, i + 1, maxOpenCards, currentCardIdx + 1, currentCards);
        }

    }

}
