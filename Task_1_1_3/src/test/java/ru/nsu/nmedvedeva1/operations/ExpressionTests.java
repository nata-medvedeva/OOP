package ru.nsu.nmedvedeva1.operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
* Тест для комплексного выражения.
* */
public class ExpressionTests {
    @Test
    void derivativeOfComplexExpression() {
        Expression expr = new Add(new Number(3),
                new Mul(new Number(2), new Variable("x")));
        Expression derivative = expr.derivative("x");
        // (3+(2*x))' = (0+((0*x)+(2*1)))
        assertEquals("(0+((0*x)+(2*1)))", derivative.print());
    }
}
