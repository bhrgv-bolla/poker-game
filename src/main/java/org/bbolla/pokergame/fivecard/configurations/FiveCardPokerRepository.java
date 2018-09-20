package org.bbolla.pokergame.fivecard.configurations;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiveCardPokerRepository extends CrudRepository<FiveCardPokerDBRecord, Integer> {

}
