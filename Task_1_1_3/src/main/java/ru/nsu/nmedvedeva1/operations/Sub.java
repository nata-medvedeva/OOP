package ru.nsu.nmedvedeva1.operations;

import java.util.Map;

/**
 * Класс для вычитания.
 * */
public class Sub extends Expression {
    private final Expression left;
    private final Expression right;

    /**
     * Создаёт выражение разности.
     *
     * @param left  левый операнд (уменьшаемое)
     * @param right правый операнд (вычитаемое)
     */
    public Sub(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Производная разности это разность производных.
     *
     * @param var имя переменной
     * @return новое выражение — разность производных операндов
     */
    @Override
    public Expression derivative(String var) {
        return new Sub(left.derivative(var), right.derivative(var));
    }

    /**
     * Возвращает строковое представление разности в скобках.
     *
     * @return строка вида "(left-right)"
     */
    @Override
    public String print() {
        return "(" + left.print() + "-" + right.print() + ")";
    }

    /**
     * Вычисляет разность значений левого и правого операндов.
     *
     * @param values словарь значений переменных
     * @return разность значений left - right
     */
    @Override
    public int eval(Map<String, Integer> values) {
        int leftValue = left.eval(values);
        int rightValue = right.eval(values);
        return leftValue - rightValue;
    }
}
