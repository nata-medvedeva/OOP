package ru.nsu.nmedvedeva1.operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
 * Тесты на переменные, их печать и сравнение, также тест на невозможное выражение.
 * */
public class VariableTests {
    @Test
    void printVariable() {
        Expression expr = new Variable("x");
        assertEquals("x", expr.print());
    }

    @Test
    void evalVariable() {
        Expression expr = new Variable("x");
        assertEquals(10, expr.eval("x = 10"));
    }

    @Test
    void derivativeOfVariable() {
        Expression expr = new Variable("x");
        Expression derivative = expr.derivative("x");
        assertEquals("1", derivative.print());
    }

    @Test
    void derivativeByOtherVariable() {
        Expression expr = new Variable("x");
        Expression derivative = expr.derivative("y");
        assertEquals("0", derivative.print());
    }

    @Test
    void evalVariableWithMultipleAssignments() {
        Expression expr = new Variable("y");
        assertEquals(13, expr.eval("x = 10; y = 13"));
    }

    @Test
    void evalVariableNotDefined() {
        Expression expr = new Variable("z");
        assertThrows(IllegalStateException.class, () -> {
            expr.eval("x = 10; y = 13");
        });
    }
}