package ru.nsu.nmedvedeva1.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        int sum = cards.stream().mapToInt(Card::getBaseValue).sum();
        long amountOfAces = cards.stream().filter(c -> c.getRank() == Rank.Ace).count();
        while (sum > 21 && amountOfAces > 0) {
            sum -= 10;
            amountOfAces--;
        }
        return sum;
    }

    public boolean isBust() {
        return getScore() > 21;
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && getScore() == 21;
    }
}