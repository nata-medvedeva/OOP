package ru.nsu.nmedvedeva1.blackjack;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

/**
 * Тесты для реализации самой игры.
 * */
public class GameTests {
    /**
     * Перехватываем входную и выходную строку, потом обратно их вставляем.
     *
     * @param input входные данные
     * */
    private String runGame(String input) {
        final InputStream originalIn = System.in;
        final PrintStream originalOut = System.out;

        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        Prints.resetScanner();

        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));

        try {
            Game game = new Game();
            game.start();
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
            Prints.resetScanner();
        }

        return testOut.toString();
    }

    /**
     * Проверка на вывод приветствия.
     * */
    @Test
    void gameShouldPrintWelcome() {
        String output = runGame("0\n");
        assertTrue(output.contains("Добро пожаловать в Блэкджек!"));
    }

    /**
     * Ввод нуля для остановки набора карт и еще одного для остановки игры в общем.
     * */
    @Test
    void gameShouldStartRound1() {
        String output = runGame("0\n0\n");
        assertTrue(output.contains("Раунд 1"));
        assertTrue(output.contains("Ваши карты:"));
        assertTrue(output.contains("Карты дилера:"));
    }

    /**
     * Ввод 1-берем карту, 0-не продолжаем брать и 0- останавливаем игру.
     * */
    @Test
    void gameToDrawCard() {
        String output = runGame("1\n0\n0\n");
        assertTrue(output.contains("Ваш ход"));
        assertTrue(output.contains("Ход дилера") || output.contains("Перебор!"));
    }

    /**
     * Проверка, что изначально все значения счета нули.
     * */
    @Test
    void gameShouldNullScores() {
        Game game = new Game();
        assertEquals(0, game.getDealerScore());
        assertEquals(0, game.getGamerScore());
        assertEquals(0, game.getRound());
    }

    /**
     * Проверяем, что есть оба участника.
     * */
    @Test
    void gameShouldHaveGamerAndDealer() {
        Game game = new Game();
        assertNotNull(game.getGamer());
        assertNotNull(game.getDealer());
        assertEquals("Игрок", game.getGamer().getName());
        assertEquals("Дилер", game.getDealer().getName());
    }

    /**
     * Проверка цикла игры: завершение раунда и начало нового.
     * Не берем новую карту, играем новый раунд, снова не берем карту и
     * выходим из игры, заканчиваем ее не начиная следующий раунд.
     */
    @Test
    void gameShouldPlayMultipleRounds() {
        String output = runGame("0\n1\n0\n0\n");

        assertTrue(output.contains("Раунд 1"));
        assertTrue(output.contains("Раунд 2"));
        assertTrue(output.contains("Хотите сыграть еще раз?"));
    }

    /**
     * Умышленно делаем перебор у игрока, после этого заканчиваем игру.
     * */
    @Test
    void gameShouldHandleGamerBust() {
        StringBuilder input = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            input.append("1\n");
        }
        input.append("0\n");
        String output = runGame(input.toString());

        assertTrue(output.contains("Перебор!"));
    }

    /**
     * Прямая проверка обновления счета при победе игрока.
     */
    @Test
    void updateScore() {
        Game game = new Game();
        game.updateScore(RoundResult.gamerWin);
        assertEquals(1, game.getGamerScore());
        assertEquals(0, game.getDealerScore());
    }

    /**
     * Прямая проверка обновления счета при ничьей.
     */
    @Test
    void updateScoreDraw() {
        Game game = new Game();
        game.updateScore(RoundResult.draw);
        assertEquals(0, game.getGamerScore());
        assertEquals(0, game.getDealerScore());
    }
}
