package com.example.lab1.service.operators.term;

import com.example.lab1.service.operators.Level;


public class Max extends BinaryFunction {
    public Max (int i, String stringValue){
        super(i, stringValue);
    }

    @Override
    public Double apply(Double a1, Double a2) {
        return Math.max(a1, a2);
    }

    @Override
    public Level level() {
        return Level.TERM;
    }

}

