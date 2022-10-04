package com.example.lab1.service.operators.infixBinaryOperator;

import com.example.lab1.service.exceptions.IllegalExpression;
import com.example.lab1.service.operators.Level;

public class Product extends BinaryOperator {
    private final static double epsilon = 0.00000001d;
    public Product(int position, String stringValue){
        super(position, stringValue);
    }

    @Override
    public Double apply(Double a1, Double a2) {
        if (stringValue.equals("*"))
            return a1*a2;
        if (stringValue.equals("/")) {
            if (a2 < epsilon)
                throw new IllegalExpression("Ділленя на 0 " + position, position);
            return a1 / a2;
        }
        else throw (new IllegalExpression("Помилка у позиції: " + position, position));
    }
    @Override
    public Level level() {
        return Level.PRODUCT;
    }
}

