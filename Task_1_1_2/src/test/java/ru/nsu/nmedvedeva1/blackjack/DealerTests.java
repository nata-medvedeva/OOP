package ru.nsu.nmedvedeva1.blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DealerTests {
    @Test
    void openAndHiddenCard() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.Spades, Rank.King));
        dealer.receiveCard(new Card(Suit.Hearts, Rank.Five));

        assertEquals(1, dealer.getHand().getCards().size());
        assertEquals(Rank.King, dealer.getHand().getCards().get(0).getRank());
    }

    @Test
    void openCards() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.Spades, Rank.King));
        dealer.receiveCard(new Card(Suit.Hearts, Rank.Five));

        assertEquals(1, dealer.getHand().getCards().size());
        assertEquals(Rank.King, dealer.getHand().getCards().get(0).getRank());
        assertEquals(10, dealer.getHand().getScore());
        dealer.revealHiddenCard();
        assertEquals(2, dealer.getHand().getCards().size());
        assertEquals(15, dealer.getHand().getScore());
    }

    @Test
    void toDrawCard() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.Spades, Rank.Ten));
        dealer.receiveCard(new Card(Suit.Hearts, Rank.Six));

        assertTrue(dealer.toDrawCard());
    }

    @Test
    void describeShouldShowOneCard() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.Spades, Rank.King));
        dealer.receiveCard(new Card(Suit.Hearts, Rank.Five));
        String description = dealer.describeOneCard();
        assertTrue(description.contains("Король"));
        assertTrue(description.contains("<закрытая карта>"));
        assertFalse(description.contains("Пятерка"));
    }
}
