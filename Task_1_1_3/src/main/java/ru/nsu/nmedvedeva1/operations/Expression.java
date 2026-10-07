package ru.nsu.nmedvedeva1.operations;

import java.util.HashMap;
import java.util.Map;

/*
 * Абстрактный класс выражения.
 * */
public abstract class Expression {
    /**
     * Возвращает строковое представление выражения со скобками.
     */
    public abstract String print();

    /**
     * Символьное дифференцирование по заданной переменной.
     *
     * @param name имя переменной
     * @return новое выражение — производная
     */
    public abstract Expression derivative(String name);

    /**
     * Вычисляет значение выражения при заданных переменных.
     * Парсит строку в Map и вызывает eval своего класса.
     *
     * @param variables строка вида "x = 10"
     * @return числовое значение выражения
     */
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

    /**
     * Вычисляет значение выражения по готовому словарю переменных.
     *
     * @param values словарь "имя переменной" и значение
     * @return числовое значение выражения
     */
    public abstract int eval(Map<String, Integer> values);
}
