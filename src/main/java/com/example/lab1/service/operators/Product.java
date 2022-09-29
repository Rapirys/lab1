package com.example.lab1.service.operators;

public class Product extends Operator{
    public Product(int position, String stringValue){
        super(position, stringValue);
    }
    @Override
    public Level level() {
        return Level.PRODUCT;
    }
}

