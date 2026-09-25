package ru.nsu.nmedvedeva1.blackjack;

/**
 * Класс игрок, где создается игрок,
 * он наследует поведение учатника.
 * */
public class Gamer extends Participant {
    /**
     * Метод, где создаем игрока с некоторым именем.
     *
     * @param name имя
     * */
    public Gamer(String name) {
        super(name);
    }
}