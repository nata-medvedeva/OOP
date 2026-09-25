package ru.nsu.nmedvedeva1.blackjack;

enum Rank {
    Ace("Туз", 11),
    King("Король", 10),
    Queen("Дама", 10),
    Jack("Валет", 10),
    Ten("Десятка", 10),
    Nine("Девятка", 9),
    Eight("Восьмерка", 8),
    Seven("Семерка", 7),
    Six("Шестерка", 6),
    Five("Пятерка", 5),
    Four("Четверка", 4),
    Three("Тройка", 3),
    Two("Двойка", 2);

    private final int baseValue;
    private final String displayName;

    Rank(String displayName, int baseValue) {
        this.displayName = displayName;
        this.baseValue = baseValue;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getBaseValue() {
        return baseValue;
    }
}
