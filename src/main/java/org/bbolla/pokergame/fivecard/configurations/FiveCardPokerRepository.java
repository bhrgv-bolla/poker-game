package org.bbolla.pokergame.fivecard.configurations;


import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface FiveCardPokerRepository extends PagingAndSortingRepository<FiveCardPokerDBRecord, Integer> {

    List<FiveCardPokerDBRecord> findAllByCard1InOrCard2InOrCard3InOrCard4InOrCard5InOrCard6InOrCard7InOrderByHandRankingAscSubRankDesc(
            Collection<String> cards1,
            Collection<String> cards2,
            Collection<String> cards3,
            Collection<String> cards4,
            Collection<String> cards5,
            Collection<String> cards6,
            Collection<String> cards7,
            Pageable pageable
    );
}
