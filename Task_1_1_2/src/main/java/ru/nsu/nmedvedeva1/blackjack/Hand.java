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

    /**
     * Метод для добавления одной определенной карты к массиву карт.
     *
     * @param card карта
     * */
    public void addCard(Card card) {
        cards.add(card);
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
     * Метод для подсчета очков,
     * сразу обрабатываем тузы правильно,
     * при привышении 21 и когда у нас есть хотя бы 1 туз,
     * меняем значение туза с 11 на 1.
     *
     * @return счет
     * */
    public int getScore() {
        int sum = 0;
        int amountOfAces = 0;
        for (Card card : cards) {
            sum += card.getBaseValue();
            if (card.getRank() == Rank.Ace) {
                amountOfAces++;
            }
        }
        while (sum > 21 && amountOfAces > 0) {
            sum -= 10;
            amountOfAces--;
        }
        return sum;
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

        String result = "[";
        for (int i = 0; i < cards.size(); i++) {
            Card c = cards.get(i);
            int displayValue = c.getBaseValue();

            if (c.getRank() == Rank.Ace && acesDidMinCounter < acesToMin) {
                displayValue = 1;
                acesDidMinCounter++;
            }

            result += c.toStringWithValue(displayValue);

            if (i < cards.size() - 1) {
                result += ", ";
            }
        }

        result += "] > " + finalScore;
        return result;
    }
}
