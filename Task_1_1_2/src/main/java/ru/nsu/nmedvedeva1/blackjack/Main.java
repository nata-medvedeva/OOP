package ru.nsu.nmedvedeva1.blackjack;

public class Main {
    /**
     * Метод, где создаем новую игру и запускаем ее
     *
     * @param args точка входа в программу
     * */
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}