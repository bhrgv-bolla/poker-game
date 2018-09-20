package org.bbolla.pokergame;

import org.bbolla.pokergame.fivecard.Card;
import org.bbolla.pokergame.fivecard.CardValue;
import org.bbolla.pokergame.fivecard.JsonUtils;
import org.bbolla.pokergame.fivecard.Suit;
import org.junit.Test;

public class TestWorkSpace {

    @Test
    public void testsSerializationDeserializationOfCard() {
        String message = JsonUtils.serialize(new Card(Suit.CLUB, CardValue.EIGHT));

        Card card = JsonUtils.deserialize(message, Card.class);

        System.out.println(card);
    }
}
