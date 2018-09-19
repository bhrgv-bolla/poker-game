package org.bbolla.pokergame.fivecard.configurations;


import lombok.Data;
import java.util.Map;

@Data
public class ConfidenceStats<$HandRankingType> {
    private Map<$HandRankingType, Integer> rankingCount;
    private double confidence;
}
