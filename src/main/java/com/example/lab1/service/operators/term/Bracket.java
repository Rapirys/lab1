package com.example.lab1.service.operators.term;

import com.example.lab1.service.Expression;
import com.example.lab1.service.operators.Level;
import com.example.lab1.service.operators.Operator;
import com.example.lab1.service.operators.UnaryFunction;

import java.util.function.Function;

public class Bracket extends UnaryFunction {
    public Bracket (int i, String stringValue){
        super(i, stringValue);
    }
    @Override
    public Level level() {
        return Level.TERM;
    }

    @Override
    public Double apply(Double d) {
        return d;
    }

}
