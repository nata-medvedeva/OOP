package ru.nsu.nmedvedeva1.blackjack;

class Card {
    private final Suit suit;
    private final Rank rank;

    /**
     * Метод, обозначаем ранг и масть
     *
     * @param suit масть
     * @param rank ранг
     * */
    public Card(Suit suit, Rank rank) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Метод, где получаем масть
     * */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Метод, где получаем ранг
     * */
    public Rank getRank() {
        return rank;
    }

    /**
     * Метод, где получаем стоимость карты
     * */
    public int getBaseValue() {
        return rank.getBaseValue();
    }

    /**
     * Метод, где выводим как строку название и масть карты
     * */
    public String toString() {
        return rank.getDisplayName() + " " + suit.getDisplaySuit();
    }

    /**
     * Метод, где выводим строку название, масть и стоимость карты
     * */
    public String toStringWithValue(int realValue) {
        return toString() + " (" + realValue + ")";
    }
}
