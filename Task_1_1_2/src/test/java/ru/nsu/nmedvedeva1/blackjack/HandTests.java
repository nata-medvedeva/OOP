package ru.nsu.nmedvedeva1.blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Тесты для класса руки.
 * */
public class HandTests {
    @Test
    void emptyHand() {
        Hand hand = new Hand();
        assertEquals(0, hand.getScore());
    }

    @Test
    void handWithoutAce() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.Hearts, Rank.Six));
        assertEquals(6, hand.getScore());
    }

    @Test
    void handWithAce() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.Hearts, Rank.Six));
        hand.addCard(new Card(Suit.Hearts, Rank.Ace));
        hand.addCard(new Card(Suit.Spades, Rank.Ace));
        assertEquals(18, hand.getScore());
    }

    @Test
    void handWithBust1() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.Hearts, Rank.King));
        hand.addCard(new Card(Suit.Spades, Rank.Queen));
        hand.addCard(new Card(Suit.Clubs, Rank.Three));
        assertTrue(hand.isBust());
    }

    @Test
    void handWithBust2() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.Hearts, Rank.King));
        hand.addCard(new Card(Suit.Spades, Rank.Queen));
        assertFalse(hand.isBust());
    }

    @Test
    void describeShouldFormatCardsCorrectly() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.Hearts, Rank.King));
        hand.addCard(new Card(Suit.Spades, Rank.Five));
        String description = hand.describe();
        assertTrue(description.contains("Король"));
        assertTrue(description.contains("Пятерка"));
        assertTrue(description.contains("15"));
    }

    @Test
    void describeShouldShowAceAsOneWhenBust() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.Hearts, Rank.Ace));
        hand.addCard(new Card(Suit.Spades, Rank.King));
        hand.addCard(new Card(Suit.Clubs, Rank.Five));
        String description = hand.describe();
        assertTrue(description.contains("(1)"));
        assertTrue(description.contains("16"));
    }
}
