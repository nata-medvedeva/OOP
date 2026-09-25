package ru.nsu.nmedvedeva1.blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Тесты для класса карты.
 * */
public class CardTests {
    @Test
    void toStringFormat() {
        Card card = new Card(Suit.Diamonds, Rank.Jack);
        assertEquals("Валет Бубны", card.toString());
    }

    @Test
    void getBaseValue() {
        Card king = new Card(Suit.Spades, Rank.King);
        Card ace = new Card(Suit.Hearts, Rank.Ace);
        Card seven = new Card(Suit.Clubs, Rank.Seven);

        assertEquals(10, king.getBaseValue());
        assertEquals(11, ace.getBaseValue());
        assertEquals(7, seven.getBaseValue());
    }
}
