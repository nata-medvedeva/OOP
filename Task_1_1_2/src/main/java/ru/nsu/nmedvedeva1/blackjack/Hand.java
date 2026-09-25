package ru.nsu.nmedvedeva1.blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс руки для игрока либо дилера, тут описываются такие методы как
 * добавление карты, получение карты или общего счета, описание карт,
 * находящихся в руке, проверки на блэкджек и переполнения.
 * */
public class Hand {
    private final List<Card> cards = new ArrayList<>();
    private int score = 0;
    
    /**
     * Метод для добавления одной определенной карты к массиву карт.
     *
     * @param card карта
     * */
    public void addCard(Card card) {
        cards.add(card);
        recalculateScore();
    }

    /**
     * Пересчитывает сумму очков с учётом правила тузов.
     * Вызывается только при изменении руки (добавлении карты или сброс).
     */
    private void recalculateScore() {
        int sum = 0;
        int acesCount = 0;
        for (Card card : cards) {
            sum += card.getBaseValue();
            if (card.getRank() == Rank.Ace) {
                acesCount++;
            }
        }
        while (sum > 21 && acesCount > 0) {
            sum -= 10;
            acesCount--;
        }
        this.score = sum;
    }

    /**
     * Метод, где возвращаем все карты, которые накопили.
     *
     * @return список всех карт, с которыми можем играть
     * */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Метод для возварата счета.
     *
     * @return счет
     * */
    public int getScore() {
        return score;
    }

    /**
     * Метод для определения перебора.
     *
     * @return правда, если перебор (очков больше 21), иначе ложь
     * */
    public boolean isBust() {
        return getScore() > 21;
    }

    /**
     * Метод для определения, блэкджек сейчас в картах или нет.
     *
     * @return правда или ложь, что есть блэкджек
     * */
    public boolean isBlackjack() {
        return cards.size() == 2 && getScore() == 21;
    }

    /**
     * Метод для описания карт из списка кардс,
     * выводим карты через запятую.
     *
     * @return result полученная строка
     * */
    public String describe() {
        int finalScore = getScore();
        int curSum = 0;
        for (Card card : cards) {
            curSum += card.getBaseValue();
        }

        int acesToMin = (curSum - finalScore) / 10;
        int acesDidMinCounter = 0;

        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < cards.size(); i++) {
            Card c = cards.get(i);
            int displayValue = c.getBaseValue();

            if (c.getRank() == Rank.Ace && acesDidMinCounter < acesToMin) {
                displayValue = 1;
                acesDidMinCounter++;
            }

            result.append(c.toStringWithValue(displayValue));

            if (i < cards.size() - 1) {
                result.append(", ");
            }
        }

        result.append("] > ").append(finalScore);
        return result.toString();
    }

    /**
     * Сбрасывает руку и обнуляет кэшированный счёт.
     */
    public void clear() {
        cards.clear();
        score = 0;
    }
}
