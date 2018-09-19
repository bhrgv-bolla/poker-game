package org.bbolla.pokergame.fivecard.configurations;


import lombok.Data;
import java.util.Map;

@Data
public class ConfidenceStats<$RankingType> {
    private Map<$RankingType, Integer> rankingCount;
    private double confidence;
}
