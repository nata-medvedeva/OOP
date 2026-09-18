package ru.nsu.nmedvedeva1.blackjack;

enum Suit {
    Spades("Пики"),
    Hearts("Черви"),
    Diamonds("Бубны"),
    Clubs("Трефи");

    private final String displaySuit;

    Suit(String displaySuit) {
        this.displaySuit = displaySuit;
    }

    public String getDisplaySuit() {
        return displaySuit;
    }
}
