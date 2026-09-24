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
        int sum = 0;
        int amountOfAces = 0;
        for (Card card : cards) {
            sum += card.getBaseValue();
            if(card.getRank() == Rank.Ace) {
                amountOfAces++;
            }
        }
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

    public String describe() {
        int finalScore = getScore();
        int curSum = 0;
        for (Card card : cards) {
            curSum += card.getBaseValue();
        }

        int acesToMin = (curSum - finalScore) / 10;
        int acesDidMinCounter = 0;

        String result = "[";
        for (int i = 0; i < cards.size(); i++) {
            Card c = cards.get(i);
            int displayValue = c.getBaseValue();

            if (c.getRank() == Rank.Ace && acesDidMinCounter < acesToMin) {
                displayValue = 1;
                acesDidMinCounter++;
            }

            result += c.toStringWithValue(displayValue);

            // Добавляем запятую и пробел, если это не последняя карта в списке
            if (i < cards.size() - 1) {
                result += ", ";
            }
        }

        result += "] > " + finalScore;
        return result;
    }
}
