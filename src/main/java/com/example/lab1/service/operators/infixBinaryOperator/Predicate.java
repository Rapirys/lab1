package com.example.lab1.service.operators.infixBinaryOperator;

import com.example.lab1.service.exceptions.IllegalExpression;
import com.example.lab1.service.operators.Level;


public class Predicate extends BinaryOperator {
    private final static double epsilon = 0.000001d;

    public Predicate (int position,String stringValue){
        super(position, stringValue);
    }

    @Override
    public Double apply(Double a1, Double a2) {
        if (invoke(a1, a2))
            return 1d;
        else return 0d;
    }

    public Boolean invoke(Double a1, Double a2) {
        if (stringValue.equals("="))
            return Math.abs(a1 - a2) < epsilon;
        if (stringValue.equals(">"))
            return Math.abs(a1 - a2) > epsilon && a1>a2;
        if (stringValue.equals("<"))
            return Math.abs(a1 - a2) > epsilon && a1<a2;
        if (stringValue.equals(">="))
            return Math.abs(a1 - a2) < epsilon || a1>a2;
        if (stringValue.equals("<="))
            return Math.abs(a1 - a2) < epsilon || a1<a2;
        if (stringValue.equals("<>"))
            return Math.abs(a1 - a2) > epsilon;
        else throw new IllegalExpression("Помилка у позиції:" + position,position);
    }

    @Override
    public Level level() {
        return Level.ADDITION;
    }
}
