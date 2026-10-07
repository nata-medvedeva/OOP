package ru.nsu.nmedvedeva1.operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Тесты на представление числа, его печать и сравнение.
 * */
public class NumberTests {
    @Test
    void derivativeOfNumber() {
        Expression expr = new Number(5);
        Expression derivative = expr.derivative("x");
        assertEquals("0", derivative.print());
    }

    @Test
    void printNegativeNumber() {
        Expression expr = new Number(-5);
        assertEquals("-5", expr.print());
    }

    @Test
    void printZero() {
        Expression expr = new Number(0);
        assertEquals("0", expr.print());
    }

    @Test
    void evalNumber() {
        Expression expr = new Number(42);
        assertEquals(42, expr.eval("x = 10"));
    }
}
