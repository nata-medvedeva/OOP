package ru.nsu.nmedvedeva1.operations;

import java.util.Map;

public class Mul extends Expression{
    private final Expression left;
    private final Expression right;
    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public String print() {
        return "(" + left.print() + "*" + right.print() + ")";
    }

    @Override
    public Expression derivative(String var) {
        return new Add(new Mul(left.derivative(var), right), new Mul(left, right.derivative(var)));
    }

    @Override
    public int eval(Map<String, Integer> values) {
        int leftValue = left.eval(values);
        int rightValue = right.eval(values);
        return leftValue * rightValue;
    }
}
