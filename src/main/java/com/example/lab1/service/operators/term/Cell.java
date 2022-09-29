package com.example.lab1.service.operators.term;

import com.example.lab1.service.operators.Level;
import com.example.lab1.service.operators.Operator;

public class Cell extends Operator {
    public Integer column;
    public Integer row;

    @Override
    public Level level() {
        return Level.TERM;
    }

    public Cell (int i, String stringValue){
        super(i, stringValue);
        column = stringValue.charAt(0)-'A';
        row = Integer.parseInt(stringValue.substring(1)) -1;
    }
}
