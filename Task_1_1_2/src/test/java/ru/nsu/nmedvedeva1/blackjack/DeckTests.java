package ru.nsu.nmedvedeva1.blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * Тесты для класса колоды.
 * */
public class DeckTests {
    @Test
    void decksThreeCardCount() {
        Deck deck = new Deck(3);
        assertEquals(156, deck.remaining());
    }

    @Test
    void decksOneCardCount() {
        Deck deck = new Deck(1);
        assertEquals(52, deck.remaining());
    }

    @Test
    void drawCardDeckEmpty() {
        Deck deck = new Deck(1);
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }
        assertNull(deck.drawCard());
    }
}
