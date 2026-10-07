package ru.nsu.nmedvedeva1.operations;

import java.util.Map;

/*
 * Класс для переменной.
 * */
public class Variable extends Expression{
    private final String name;

    /**
     * Создаёт переменную с заданным именем.
     *
     * @param name имя переменной
     */
    public Variable(String name) {
        this.name = name;
    }

    /**
     * Возвращает имя переменной.
     *
     * @return строка с именем переменной
     */
    @Override
    public String print() {
        return name;
    }

    /**
     * Производная переменной это 1, если дифференцируем по ней самой, иначе 0.
     *
     * @param variableName имя переменной, по которой дифференцируем
     * @return Number(1), если имя совпадает, иначе Number(0)
     */
    @Override
    public Expression derivative(String variableName) {
        if (name.equals(variableName)) {
            return new Number(1);
        }
        return new Number(0);
    }

    /**
     * Возвращает значение переменной из словаря.
     *
     * @param values словарь "имя переменной" и значение
     * @return значение переменной
     */
    @Override
    public int eval (Map<String, Integer> values) {
        if(values.containsKey(name)) {
            return values.get(name);
        }
        throw new IllegalStateException("Variable" + name + "unknown");
    }
}
