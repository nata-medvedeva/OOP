package ru.nsu.nmedvedeva1.operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpressionTests {
    @Test
    void parseNumber() {
        Expression expr = Parsing.toParse("3");
        assertEquals("3", expr.print());
    }

    @Test
    void parseVariable() {
        Expression expr = Parsing.toParse("x");
        assertEquals("x", expr.print());
    }

    @Test
    void parseVariable2() {
        Expression expr = Parsing.toParse("var");
        assertEquals("var", expr.print());
    }

    @Test
    void derivativeOfNumber() {
        Expression expr = new Number(5);
        Expression derivative = expr.derivative("x");
        assertEquals("0", derivative.print());
    }

    @Test
    void derivativeOfVariable() {
        Expression expr = new Variable("x");
        Expression derivative = expr.derivative("x");
        assertEquals("1", derivative.print());
    }

    @Test
    void derivativeOfComplexExpression() {
        Expression expr = new Add(new Number(3),
                new Mul(new Number(2), new Variable("x")));
        Expression derivative = expr.derivative("x");
        // (3+(2*x))' = (0+((0*x)+(2*1)))
        assertEquals("(0+((0*x)+(2*1)))", derivative.print());
    }

    @Test
    void evalVariable() {
        Expression expr = new Variable("x");
        assertEquals(10, expr.eval("x = 10"));
    }

    @Test
    void evalAddition() {
        Expression expr = new Add(new Number(3), new Number(5));
        assertEquals(8, expr.eval("x = 10"));
    }

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
    void evalMul() {
        Expression expr = new Mul(new Number(2), new Variable("x"));
        assertEquals(20, expr.eval("x = 10"));
    }

    @Test
    void parseOfComplexExpression() {
        Expression expr = Parsing.toParse("((2+3)*x)");
        assertEquals("((2+3)*x)", expr.print());
    }

    @Test
    void divByZeroThrowsException() {
        Expression expr = new Div(new Number(10), new Number(0));

        assertThrows(ArithmeticException.class, () -> {expr.eval("x = 10");});
    }
}
