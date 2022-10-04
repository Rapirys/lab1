package com.example.lab1.service;

import com.example.lab1.service.exceptions.IllegalExpression;
import com.example.lab1.service.exceptions.ParseIllegalExpression;
import com.example.lab1.service.operators.*;
import com.example.lab1.service.operators.infixBinaryOperator.Addition;
import com.example.lab1.service.operators.infixBinaryOperator.Power;
import com.example.lab1.service.operators.infixBinaryOperator.Product;
import com.example.lab1.service.operators.infixBinaryOperator.Predicate;
import com.example.lab1.service.operators.term.Bracket;
import com.example.lab1.service.operators.term.Cell;
import com.example.lab1.service.operators.term.Min;
import com.example.lab1.service.operators.term.Number;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {
    private String string;
    public Integer current;
    private Double result = null;
    private List<Operator> operators;

    private Expression() {};

    public static Expression parse(String string){
        Expression expression = new Expression();
        expression.string = string;
        expression.operators = new ArrayList<>();
        expression.current = -1;
        int i = 0;
        while (i<string.length()){
            if (string.charAt(i) == ' ') {
                i++;
                continue;
            }
            if (string.charAt(i) == '+' || string.charAt(i) == '-') {
                expression.operators.add(new Addition(i,String.valueOf(string.charAt(i))));
                i++;
                continue;
            }
            if (string.charAt(i) == '/' || string.charAt(i) == '*') {
                expression.operators.add(new Product(i,String.valueOf(string.charAt(i))));
                i++;
                continue;
            }
            if (string.charAt(i) == '^') {
                expression.operators.add(new Power(i,String.valueOf(string.charAt(i))));
                i++;
                continue;
            }
            if (string.substring(i,i+1).matches("[0-9]")) {
                Matcher mathcer = Pattern.compile("[0-9]+").matcher(string.substring(i));
                mathcer.find();
                String number =mathcer.group();
                expression.operators.add(new Number(i,number));
                i+=number.length();
                continue;
            }
            if(string.charAt(i) == '('){
                int j = findCloseBrackets(i,string);
                expression.operators.add(new Bracket(i,string.substring(i+1,j)));
                i+=j-i+1;
                continue;
            }
            if (string.startsWith(">=", i) || string.startsWith("<=", i) || string.startsWith("<>", i)) {
                expression.operators.add(new Predicate(i,string.substring(i,i+2)));
                i+=2;
                continue;
            }
            if (string.startsWith(">", i) || string.startsWith("<", i) || string.startsWith("=", i) ) {
                expression.operators.add(new Predicate(i,string.substring(i,i+1)));
                i++;
                continue;
            }
            if (string.startsWith("div", i) || string.startsWith("mod", i)) {
                expression.operators.add(new Product(i,string.substring(i,i+3)));
                i+=3;
                continue;
            }
            if(string.startsWith("min(", i)){
                int j = findCloseBrackets(i+3,string);
                expression.operators.add(new Min(i,string.substring(i+4,j)));
                i+=j-i+1;
                continue;
            }
            if(string.startsWith("max(", i)){
                int j = findCloseBrackets(i+3,string);
                expression.operators.add(new Min(i,string.substring(i+4,j)));
                i+=j-i+1;
                continue;
            }
            if (i+1<string.length() && string.substring(i,i+2).matches("[A-Z][0-9]")){
                Matcher mathcer = Pattern.compile("[A-Z][0-9]+").matcher(string.substring(i));
                mathcer.find();
                String cell =mathcer.group();
                expression.operators.add(new Cell(i,cell));
                i+=cell.length();
                continue;
            }
            throw new ParseIllegalExpression( "Неможливо распарсити "+ string+ " Помилка у позиції:" + i, i);
        }
        return expression;
    }

    private static int findCloseBrackets(int i, String string) {
        int k = 1;
        int start = i;
        while  (i<string.length()){
            i++;
            if (string.charAt(i) == '(')
                k++;
            if (string.charAt(i) == ')')
                k--;
            if (k==0)
                return i;
        }
        throw new IllegalExpression("Не закрита дужка", start);
    }

    public boolean hasNext(){
        return current+1<operators.size();
    }
    public Operator next(){
        current++;
        return operators.get(current);

    }
    public Operator getCurrent(int shift){
        return operators.get(current+shift);
    }

    @Override
    public String toString() {
        return "Expression{" +
                 string +
                '}';
    }

    public String getString() {
        return string;
    }
    public Double setResult(Double d) {
        return result = d;
    }

    public Double getResult() {
        return result;
    }
}
