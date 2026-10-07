package ru.nsu.nmedvedeva1.operations;

import java.util.Map;

/**
 * Класс для суммы.
 * */
public class Add extends Expression {
    private final Expression left;
    private final Expression right;

    /**
     * Создаёт выражение суммы.
     *
     * @param left  левый операнд
     * @param right правый операнд
     */
    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Производная суммы это сумма производных.
     *
     * @param var имя переменной
     * @return новое выражение — сумма производных операндов
     */
    @Override
    public Expression derivative(String var) {
        return new Add(left.derivative(var), right.derivative(var));
    }

    /**
     * Возвращает строковое представление суммы в скобках.
     *
     * @return строка вида "(left+right)"
     */
    @Override
    public String print() {
        return "(" + left.print() + "+" + right.print() + ")";
    }

    /**
     * Вычисляет сумму значений левого и правого операндов.
     *
     * @param values словарь значений переменных
     * @return сумма значений left + right
     */
    @Override
    public int eval(Map<String, Integer> values) {
        int leftValue = left.eval(values);
        int rightValue = right.eval(values);
        return leftValue + rightValue;
    }
}
