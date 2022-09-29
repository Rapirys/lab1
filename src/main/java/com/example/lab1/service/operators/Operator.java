package com.example.lab1.service.operators;

public abstract class Operator {
    public String stringValue;

    public abstract Level level();
    public int position;
    public Operator(int position, String stringValue){
        this.stringValue = stringValue;
        this.position = position;
    }

}
