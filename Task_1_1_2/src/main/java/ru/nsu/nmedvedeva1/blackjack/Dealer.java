package ru.nsu.nmedvedeva1.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {
    private Card hiddenCard;

    public Dealer() {
        super("Дилер");
    }

    public void receiveCard(Card card) {
        if (hand.getCards().isEmpty()) {
            super.receiveCard(card);
        } else if (hiddenCard == null) {
            hiddenCard = card;
        } else {
            super.receiveCard(card);
        }
    }

    public void revealHiddenCard() {
        if (hiddenCard != null) {
            hand.addCard(hiddenCard);
            hiddenCard = null;
        }
    }

    public boolean toDrawCard() {
        return hand.getScore() < 17;
    }

    public void resetHand() {
        super.resetHand();
        hiddenCard = null;
    }
}