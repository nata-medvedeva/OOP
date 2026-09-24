package ru.nsu.nmedvedeva1.blackjack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTests {
    @Test
    void game1() {
        assertEquals("GAMER", Game.winnerForTests(20, 22, false, true));
    }

    @Test
    void game2() {
        assertEquals("DEALER", Game.winnerForTests(22, 20, true, false));
    }

    @Test
    void game3() {
        assertEquals("GAMER", Game.winnerForTests(20, 18, false, false));
    }

    @Test
    void game4() {
        assertEquals("DEALER", Game.winnerForTests(18, 20, false, false));
    }

    @Test
    void game5() {
        assertEquals("DRAW", Game.winnerForTests(19, 19, false, false));
    }
}
