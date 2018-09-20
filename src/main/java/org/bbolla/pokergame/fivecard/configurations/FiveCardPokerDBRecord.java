package org.bbolla.pokergame.fivecard.configurations;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@Entity(name = "combinations")
public class FiveCardPokerDBRecord {

    String card1;
    String card2;
    String card3;
    String card4;
    String card5;
    String card6;
    String card7;
    Integer handRanking;
    Integer subRank;
}
