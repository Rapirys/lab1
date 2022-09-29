package com.example.lab1.service.operators.term;

import com.example.lab1.service.Expression;
import com.example.lab1.service.operators.Operator;

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
