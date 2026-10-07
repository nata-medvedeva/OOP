package ru.nsu.nmedvedeva1.operations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Тесты на вычитание.
 * */
public class SubTests {
    @Test
    void printSub() {
        Expression expr = new Sub(new Number(10), new Number(3));
        assertEquals("(10-3)", expr.print());
    }

    @Test
    void derivativeOfSub() {
        Expression expr = new Sub(new Variable("x"), new Number(5));
        assertEquals("(1-0)", expr.derivative("x").print());
    }

    @Test
    void evalSub() {
        Expression expr = new Sub(new Number(10), new Number(3));
        assertEquals(7, expr.eval("x = 100"));
    }
}
