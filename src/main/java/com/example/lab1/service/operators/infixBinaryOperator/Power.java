package com.example.lab1.service.operators.infixBinaryOperator;

import com.example.lab1.service.exceptions.IllegalExpression;
import com.example.lab1.service.operators.Level;

import static java.lang.Math.exp;
import static java.lang.Math.log;

public class Power extends BinaryOperator {
    public Power(int position, String stringValue){
        super(position, stringValue);
    }

    @Override
    public Double apply(Double a1, Double a2) {
        if (stringValue.equals("^"))
            return exp(log(a1) * a2);
        else throw new IllegalExpression("in position:" + position,position);
    }
    @Override
    public Level level() {
        return Level.POWER;
    }
}

