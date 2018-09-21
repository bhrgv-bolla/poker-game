package org.bbolla.pokergame;

import org.bbolla.pokergame.fivecard.*;
import org.junit.Test;

public class TestWorkSpace {

    @Test
    public void testsSerializationDeserializationOfCard() {
        String message = JsonUtils.serialize(new Card(Suit.CLUB, CardValue.EIGHT));

        Card card = JsonUtils.deserialize(message, Card.class);

        System.out.println(card);
    }

    /**
     * Sample:
     *  CREATE TABLE `poker`.`combination_matrix` ( `DAIMOND_TWO` BOOLEAN NOT NULL DEFAULT FALSE , `hand_ranking` INT NOT NULL , `sub_rank` INT NOT NULL ) ENGINE = InnoDB;
     */
    @Test
    public void generateDDLForCombinationMatrix() {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE `poker`.`combination_matrix` ( ");
        StringBuilder idx = new StringBuilder();
        for(Card card: Deck.getCards()) {
            sb.append("`"+ card.toString() +"` BOOLEAN NOT NULL DEFAULT FALSE , ");
            idx.append(", INDEX (`"+card.toString()+"`) ");
        }
        sb.append(" `hand_ranking` INT NOT NULL , `sub_rank` INT NOT NULL, ");
        sb.append(idx.toString());
        sb.append(" ) ENGINE = InnoDB;");

        System.out.println(sb);
    }
}
