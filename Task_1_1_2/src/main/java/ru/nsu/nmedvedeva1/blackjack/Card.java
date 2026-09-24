package ru.nsu.nmedvedeva1.blackjack;

/**
 * Класс карты, где описаны методы преобразлвание карты в строку,
 * то есть вывод ее ранга, масти и стоимости и так же
 * этих составляющих по отдельности.
 * */
class Card {
    private final Suit suit;
    private final Rank rank;

    /**
     * Метод, обозначаем ранг и масть.
     *
     * @param suit масть
     * @param rank ранг
     * */
    public Card(Suit suit, Rank rank) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Метод, где получаем ранг.
     *
     * @return ранг
     * */
    public Rank getRank() {
        return rank;
    }

    /**
     * Метод, где получаем стоимость карты.
     *
     * @return стоимость карты
     * */
    public int getBaseValue() {
        return rank.getBaseValue();
    }

    /**
     * Метод, где выводим как строку название и масть карты.
     *
     * @return ранг и масть карты, ее строковое описание
     * */
    public String toString() {
        return rank.getDisplayName() + " " + suit.getDisplaySuit();
    }

    /**
     * Метод, где выводим строку название, масть и стоимость карты.
     *
     * @param realValue реальное значение (где туз уже может быть 1)
     * @return строка с полным описанием карты
     * */
    public String toStringWithValue(int realValue) {
        return toString() + " (" + realValue + ")";
    }
}
