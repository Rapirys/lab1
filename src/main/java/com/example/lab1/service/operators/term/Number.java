package com.example.lab1.service.operators.term;

import com.example.lab1.service.exceptions.IllegalExpression;
import com.example.lab1.service.operators.Level;
import com.example.lab1.service.operators.Operator;

public class Number extends Operator {
    private Integer value;
    public Number (int i, String stringValue){
        super(i, stringValue);
        try {
            value = Integer.parseInt(stringValue);
        }catch (NumberFormatException e){
            throw new IllegalExpression("Помилка у позиції:" + position,position);
        }
    }

    @Override
    public Level level() {
        return Level.TERM;
    }

    public Double value() {
        return value.doubleValue();
    }
}
