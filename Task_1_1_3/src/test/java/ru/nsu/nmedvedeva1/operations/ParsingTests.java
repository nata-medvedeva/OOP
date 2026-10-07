package ru.nsu.nmedvedeva1.operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Тесты для парсера, как он считывает число, переменную и выражение.
 * */
public class ParsingTests {
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
    void parseOfComplexExpression() {
        Expression expr = Parsing.toParse("((2+3)*x)");
        assertEquals("((2+3)*x)", expr.print());
    }
}
