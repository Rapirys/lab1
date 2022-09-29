package com.example.lab1.service.operators.infixBinaryOperator;

import com.example.lab1.service.exceptions.IllegalExpression;
import com.example.lab1.service.operators.Level;

public class Product extends BinaryOperator {
    public Product(int position, String stringValue){
        super(position, stringValue);
    }

    @Override
    public Double apply(Double a1, Double a2) {
        if (stringValue.equals("*"))
            return a1*a2;
        if (stringValue.equals("/"))
            return a1/a2;
        else throw (new IllegalExpression("in position: " + position, position));
    }
    @Override
    public Level level() {
        return Level.PRODUCT;
    }
}

