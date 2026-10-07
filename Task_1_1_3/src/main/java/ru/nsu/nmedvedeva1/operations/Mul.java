package ru.nsu.nmedvedeva1.operations;

import java.util.Map;

/**
 * Класс для умножения.
 * */
public class Mul extends Expression {
    private final Expression left;
    private final Expression right;
    
    /**
     * Создаёт выражение произведения.
     *
     * @param left  левый операнд
     * @param right правый операнд
     */
    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Возвращает строковое представление произведения в скобках.
     *
     * @return строка вида "(left*right)"
     */
    @Override
    public String print() {
        return "(" + left.print() + "*" + right.print() + ")";
    }

    /**
     * Производная произведения по формуле раскладываем.
     *
     * @param var имя переменной
     * @return новое выражение — сумма двух произведений
     */
    @Override
    public Expression derivative(String var) {
        return new Add(new Mul(left.derivative(var), right), new Mul(left, right.derivative(var)));
    }

    /**
     * Вычисляет произведение значений левого и правого операндов.
     *
     * @param values словарь значений переменных
     * @return произведение значений left * right
     */
    @Override
    public int eval(Map<String, Integer> values) {
        int leftValue = left.eval(values);
        int rightValue = right.eval(values);
        return leftValue * rightValue;
    }
}
