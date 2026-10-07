package ru.nsu.nmedvedeva1.operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
 * Тесты на деление, в том числе деление на 0.
 * */
public class DivTests {
    @Test
    void printDiv() {
        Expression expr = new Div(new Number(10), new Number(2));
        assertEquals("(10/2)", expr.print());
    }

    @Test
    void evalDiv() {
        Expression expr = new Div(new Number(10), new Number(2));
        assertEquals(5, expr.eval("x = 10"));
    }

    @Test
    void divByZeroThrowsException() {
        Expression expr = new Div(new Number(10), new Number(0));

        assertThrows(ArithmeticException.class, () -> {expr.eval("x = 10");});
    }

    @Test
    void derivativeOfDiv() {
        Expression expr = new Div(new Variable("x"), new Number(2));
        assertEquals("(((1*2)-(x*0))/(2*2))", expr.derivative("x").print());
    }

    @Test
    void derivativeOfDivByVariable() {
        Expression expr = new Div(new Number(10), new Variable("x"));
        assertEquals("(((0*x)-(10*1))/(x*x))", expr.derivative("x").print());
    }
}
