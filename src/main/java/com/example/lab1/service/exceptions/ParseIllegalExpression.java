package com.example.lab1.service.exceptions;

public class ParseIllegalExpression extends IllegalArgumentException{
    int position;


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ParseIllegalExpression(String message, int position) {
        super(message);
        this.position = position;
    }
}
