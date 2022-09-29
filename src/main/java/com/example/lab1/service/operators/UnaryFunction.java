package com.example.lab1.service.operators;

import com.example.lab1.service.Expression;

public abstract class UnaryFunction extends Operator {

    private Expression expression;

    public UnaryFunction(int position, String stringValue) {
        super(position, stringValue);
        expression = Expression.parse(stringValue);
    }

    public Expression getExpression() {
        return expression;
    }

    public abstract Double apply(Double a);
}
