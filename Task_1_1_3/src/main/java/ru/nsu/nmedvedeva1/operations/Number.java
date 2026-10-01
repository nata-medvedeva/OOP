package ru.nsu.nmedvedeva1.operations;

import java.util.Map;

public class Number extends Expression {
    private final int value;
    public Number(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    @Override
    public String print() {
        String val = "" + value;
        return val;
    }

    @Override
    public Expression derivative(String variableName) {
        return new Number(0);
    }

    @Override
    public int eval(Map<String, Integer> values) {
        return value;
    }
}
