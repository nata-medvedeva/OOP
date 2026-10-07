package ru.nsu.nmedvedeva1.operations;

import java.util.Map;

/**
 * Класс для деления.
 * */
public class Div extends Expression {
    private final Expression left;
    private final Expression right;

    /**
     * Создаёт выражение частного.
     *
     * @param left  левый операнд (числитель)
     * @param right правый операнд (знаменатель)
     */
    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Возвращает строковое представление частного в скобках.
     *
     * @return строка вида "(left/right)"
     */
    @Override
    public String print() {
        return "(" + left.print() + "/" + right.print() + ")";
    }

    /**
     * Производная частного по формуле раскладываем.
     *
     * @param var имя переменной
     * @return новое выражение — дробь с разностью в числителе и квадратом в знаменателе
     */
    @Override
    public Expression derivative(String var) {
        return new Div(new Sub(new Mul(left.derivative(var), right),
                new Mul(left, right.derivative(var))), new Mul(right, right));
    }

    /**
     * Вычисляет частное значений левого и правого операндов.
     *
     * @param values словарь значений переменных
     * @return частное значений left / right (целочисленное деление)
     */
    @Override
    public int eval(Map<String, Integer> values) {
        int leftValue = left.eval(values);
        int rightValue = right.eval(values);
        if (rightValue != 0) {
            return leftValue / rightValue;
        }
        throw new ArithmeticException("Division by zero");
    }
}
