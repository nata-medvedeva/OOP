package ru.nsu.nmedvedeva1.operations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
* Тесты на суммирование.
* */
public class AddTests {
    @Test
    void evalAddition() {
        Expression expr = new Add(new Number(3), new Number(5));
        assertEquals(8, expr.eval("x = 10"));
    }

    @Test
    void printAdd() {
        Expression expr = new Add(new Number(3), new Number(5));
        assertEquals("(3+5)", expr.print());
    }
}
