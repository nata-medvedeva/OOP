package ru.nsu.nmedvedeva1.operations;

import java.util.Map;

public class Variable extends Expression{
    private final String name;
    public Variable(String name) {
        this.name = name;
    }

    public String getVariable() {
        return name;
    }

    @Override
    public String print() {
        return name;
    }

    @Override
    public Expression derivative(String variableName) {
        if (name.equals(variableName)) {
            return new Number(1);
        }
        return new Number(0);
    }

    @Override
    public int eval (Map<String, Integer> values) {
        if(values.containsKey(name)) {
            return values.get(name);
        }
        throw new IllegalStateException("Variable" + name + "unknown");
    }
}
