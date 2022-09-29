package com.example.lab1.service.operators.infixBinaryOperator;

import com.example.lab1.service.operators.Operator;

public abstract class BinaryOperator extends Operator {

    public BinaryOperator(int position, String stringValue) {
        super(position, stringValue);
    }

    public abstract Double apply (Double a1, Double a2);
}
