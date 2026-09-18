package ru.nsu.nmedvedeva1.blackjack;

public abstract class Participant {
    protected final Hand hand = new Hand();
    protected String name;

    public Participant (String name) {
        this.name = name;
    }

    public void receiveCard(Card card) {
        hand.addCard(card);
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public void resetHand() {
        hand.getCards().clear();
    }
}