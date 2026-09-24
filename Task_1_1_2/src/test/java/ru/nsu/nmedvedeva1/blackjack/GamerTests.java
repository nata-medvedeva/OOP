package ru.nsu.nmedvedeva1.blackjack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GamerTests {

    @Test
    void gamerTest() {
        Gamer gamer = new Gamer("Игрок");
        gamer.receiveCard(new Card(Suit.Spades, Rank.King));
        gamer.receiveCard(new Card(Suit.Hearts, Rank.Five));

        assertEquals(2, gamer.getHand().getCards().size());
        assertEquals(15, gamer.getHand().getScore());
    }
}
