package ru.nsu.nmedvedeva1.blackjack;

class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.rank = rank;
        this.suit = suit;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public int getBaseValue() {
        return rank.getBaseValue();
    }

    public String toString() {
        return rank.getDisplayName() + " " + suit.getDisplaySuit();
    }

    public String toStringWithValue(int realValue) {
        return toString() + " (" + realValue + ")";
    }
}
