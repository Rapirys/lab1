package com.example.lab1.service.operators.infixBinaryOperator;

import com.example.lab1.service.exceptions.IllegalExpression;
import com.example.lab1.service.operators.Level;

public class Addition extends BinaryOperator {
    public Addition (int position,String stringValue){
        super(position, stringValue);
    }

    @Override
    public Double apply(Double a1, Double a2) {
        if (stringValue.equals("+"))
            return a1+a2;
        if (stringValue.equals("-"))
            return a1-a2;
        else throw new IllegalExpression("Помилка у позиції:" + position,position);
    }

    @Override
    public Level level() {
        return Level.ADDITION;
    }
}

