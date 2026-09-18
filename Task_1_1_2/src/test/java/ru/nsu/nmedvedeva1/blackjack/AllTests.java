package ru.nsu.nmedvedeva1.blackjack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AllTests {
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
    void openAndHiddenCard() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.Spades, Rank.King));
        dealer.receiveCard(new Card(Suit.Hearts, Rank.Five));

        assertEquals(1, dealer.getHand().getCards().size());
        assertEquals(Rank.King, dealer.getHand().getCards().get(0).getRank());
    }

    @Test
    void gamerTest() {
        Gamer gamer = new Gamer("Игрок");
        gamer.receiveCard(new Card(Suit.Spades, Rank.King));
        gamer.receiveCard(new Card(Suit.Hearts, Rank.Five));

        assertEquals(2, gamer.getHand().getCards().size());
        assertEquals(15, gamer.getHand().getScore());
    }
}