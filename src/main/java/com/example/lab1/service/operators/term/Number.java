package com.example.lab1.service.operators.term;

import com.example.lab1.service.operators.Level;
import com.example.lab1.service.operators.Operator;

public class Number extends Operator {
    private Integer value;
    public Number (int i, String stringValue){
        super(i, stringValue);
        value = Integer.parseInt(stringValue);
    }

    @Override
    public Level level() {
        return Level.TERM;
    }

    public Double value() {
        return value.doubleValue();
    }
}
