package org.bbolla.pokergame.fivecard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Generates and stores all possibilities along with their rank into a text
 * file.
 * 
 * @author bhargav
 *
 */
public class PokerPossibilitiesGenerator<PokerHandRanking> {

	static FileWriter fileWriter;
	static long possibilities = 0L;

	static {
		List<Card> cards = Deck.getCards();

		System.out.println(cards.size());
		try {
			File file = new File("fiveCardPoker/all_possibilities");
			file.delete();
			file.createNewFile();
			fileWriter = new FileWriter(file);
			fileWriter.write("Generating All Possibilities" + new Date() + System.lineSeparator());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Cannot open the file", e);
		}

		generateCombinations(cards, 0, 7, 0, new Integer[7]);

		try {
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error closing file", e);
		}

	}

	public static void writeToFile(String line) {
		try {
			fileWriter.append(line + System.lineSeparator());
			System.out.println(possibilities++);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Writing to file failed", e);
		}
	}

	private static void generateCombinations(List<Card> cards, int deckIdx, int maxOpenCards, int currentCardIdx,
			Integer[] currentCards) {

		if (deckIdx >= cards.size()) {
			if (currentCardIdx == maxOpenCards)
				writeToFile(Arrays.deepToString(currentCards));
			return;
		} else if (currentCardIdx == maxOpenCards) {
			writeToFile(Arrays.deepToString(currentCards));
			return;
		}

		for (int i = deckIdx; i < cards.size(); i++) {
			// System.out.println(i);
			currentCards[currentCardIdx] = i;
			generateCombinations(cards, i + 1, maxOpenCards, currentCardIdx + 1, currentCards);
		}

	}

	public static void main(String[] args) {

	}

}
