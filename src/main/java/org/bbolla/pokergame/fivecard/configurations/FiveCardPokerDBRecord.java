package org.bbolla.pokergame.fivecard.configurations;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "combinations")
public class FiveCardPokerDBRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String card1;
    String card2;
    String card3;
    String card4;
    String card5;
    String card6;
    String card7;
    Integer handRanking;
    Integer subRank;


    public FiveCardPokerDBRecord(String card1, String card2, String card3, String card4, String card5, String card6, String card7, Integer handRanking, Integer subRank) {
        this.card1 = card1;
        this.card2 = card2;
        this.card3 = card3;
        this.card4 = card4;
        this.card5 = card5;
        this.card6 = card6;
        this.card7 = card7;
        this.handRanking = handRanking;
        this.subRank = subRank;
    }
}
