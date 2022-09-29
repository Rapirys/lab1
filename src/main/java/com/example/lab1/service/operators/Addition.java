package com.example.lab1.service.operators;

public class Addition extends Operator{
    public Addition (int position,String stringValue){
        super(position, stringValue);
    }
    @Override
    public Level level() {
        return Level.TERM;
    }
}

