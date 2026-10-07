package ru.nsu.nmedvedeva1.operations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Тесты для умножения.
 * */
public class MulTests {
    @Test
    void evalMul() {
        Expression expr = new Mul(new Number(2), new Variable("x"));
        assertEquals(20, expr.eval("x = 10"));
    }

    @Test
    void printMul() {
        Expression expr = new Mul(new Number(2), new Number(3));
        assertEquals("(2*3)", expr.print());
    }

    @Test
    void evalMulTwoVariables() {
        Expression expr = new Mul(new Variable("x"), new Variable("y"));
        assertEquals(130, expr.eval("x = 10; y = 13"));
    }
}
