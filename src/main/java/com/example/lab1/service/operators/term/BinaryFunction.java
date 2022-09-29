package com.example.lab1.service.operators.term;

import com.example.lab1.service.Expression;
import com.example.lab1.service.operators.Operator;

public abstract class BinaryFunction extends Operator {
    private Expression expression1;
    private Expression expression2;
    public BinaryFunction(int position, String stringValue) {
        super(position, stringValue);
        String[] expressions = stringValue.split(", ?");
        expression1 = Expression.parse(expressions[0]);
        expression2 = Expression.parse(expressions[1]);
    }
    public Expression getFirstExpression(){
        return expression1;
    };
    public Expression getSecondExpression(){
        return expression2;
    };
    public abstract Double apply (Double a1, Double a2);
}
