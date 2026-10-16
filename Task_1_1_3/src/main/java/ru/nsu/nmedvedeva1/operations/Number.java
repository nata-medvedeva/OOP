package ru.nsu.nmedvedeva1.operations;

import java.util.Map;

/**
* Класс для числа.
* */
public class Number extends Expression {
    private final int value;

    /**
     * Создаёт числовую константу.
     *
     * @param value целое значение
     */
    public Number(int value) {
        this.value = value;
    }

    /**
     * Возвращает строковое представление числа.
     *
     * @return строка в кавычках
     */
    @Override
    public String print() {
        String val = "" + value;
        return val;
    }

    /**
     * Производная константы всегда равна нулю.
     *
     * @param variableName имя переменной
     * @return выражение Number(0)
     */
    @Override
    public Expression derivative(String variableName) {
        return new Number(0);
    }

    /**
     * Возвращает значение константы.
     *
     * @param values словарь переменных (игнорируется тут)
     * @return значение числа
     */
    @Override
    public int eval(Map<String, Integer> values) {
        return value;
    }
}
