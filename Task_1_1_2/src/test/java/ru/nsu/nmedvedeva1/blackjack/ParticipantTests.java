package ru.nsu.nmedvedeva1.blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Тесты для абстрактного класса участника.
 * */
public class ParticipantTests {
    @Test
    void correctName() {
        Gamer gamer = new Gamer("Тестовый Игрок");
        assertEquals("Тестовый Игрок", gamer.getName());
    }

    @Test
    void resetHandShouldClearCards() {
        Gamer gamer = new Gamer("Игрок");
        gamer.receiveCard(new Card(Suit.Spades, Rank.King));
        gamer.receiveCard(new Card(Suit.Hearts, Rank.Five));
        gamer.resetHand();
        assertTrue(gamer.getHand().getCards().isEmpty());
    }
}
