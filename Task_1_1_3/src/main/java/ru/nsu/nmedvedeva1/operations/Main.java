package ru.nsu.nmedvedeva1.operations;

public class Main {
    public static void main(String[] args) {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        System.out.println(e.print());
        System.out.println(e.derivative("x").print());
        System.out.println(e.eval("x = 10; y = 13"));
    }
}
