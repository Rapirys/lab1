package com.example.lab1.service.operators.term;

import com.example.lab1.service.operators.Level;

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
        if (d == null)
            return (double)0;
        return d;
    }

}
