package ru.nsu.nmedvedeva1.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    /**
     * Метод где мы заполняем нашу колоду всеми возможными картами из
     * некоторого количество колод
     *
     * @param emountOfDecks количество колод
     * */
    public Deck(int emountOfDecks) {
        List<Card> generated = new ArrayList<>();

        for (int i = 0; i < emountOfDecks; i++) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    generated.add(new Card(suit, rank));
                }
            }
        }
        Collections.shuffle(generated);
        cards.addAll(generated);
    }

    /**
     * Метод, где вытягиваем карту
     * */
    public Card drawCard() {
        if (cards.isEmpty()) {
            System.err.println("Колода пуста");
            return null;
        }
        return cards.remove(cards.size() - 1);
    } 

    /**
     * Метод, который выводит количество карт, сколько осталось в колоде
     * */
    public int remaining() {
        return cards.size();
    }
}