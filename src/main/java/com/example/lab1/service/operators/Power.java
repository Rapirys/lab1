package com.example.lab1.service.operators;

public class Power extends Operator{
    public Power(int position, String stringValue){
        super(position, stringValue);
    }
    @Override
    public Level level() {
        return Level.POWER;
    }
}

