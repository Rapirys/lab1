package com.example.lab1.service.exceptions;

public class IllegalExpression extends IllegalArgumentException{
    int position;
    int row;
    int column;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public IllegalExpression(IllegalExpression e, int row, int column) {
        super(e.getMessage(), e);
        this.position = e.position;
        this.row = row;
        this.column = column;
    }

    public IllegalExpression(String message,  int position) {
        super(message);
        this.position = position;
    }
}
