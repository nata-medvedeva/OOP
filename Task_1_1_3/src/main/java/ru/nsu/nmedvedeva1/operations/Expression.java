package ru.nsu.nmedvedeva1.operations;

import java.util.HashMap;
import java.util.Map;

public abstract class Expression {
    public abstract String print();

    public abstract Expression derivative(String name);

    public int eval(String variables) {
        Map<String, Integer> values = new HashMap<>();
        String[] parts = variables.split(";");
        for (String part : parts) {
            String p = Parsing.hasWhitespace(part);
            String[] keyValue = p.split("=");
            String name = keyValue[0];
            int number = Integer.parseInt(keyValue[1]);
            values.put(name, number);
        }
        return eval(values);
    }

    public abstract int eval(Map<String, Integer> values);
}
