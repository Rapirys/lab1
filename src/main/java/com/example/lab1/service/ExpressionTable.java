package com.example.lab1.service;

import com.example.lab1.service.operators.BinaryFunction;
import com.example.lab1.service.operators.Level;
import com.example.lab1.service.operators.UnaryFunction;
import com.example.lab1.service.operators.term.Cell;
import com.example.lab1.service.operators.term.Number;
import com.example.lab1.service.operators.Operator;

import java.util.List;

import static java.lang.Math.*;

public class ExpressionTable {
    Expression[][] expressions;
    Integer startRow;
    Integer startColumn;
    Integer m;
    Integer n;

    public ExpressionTable(List<List<String>> table, Integer startRow, Integer startColumn) {
        this.m = table.size();
        this.n = table.get(0).size();///check logic
        this.expressions = new Expression[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                expressions[i][j] = Expression.parse(table.get(j).get(i));
            }
        }
        this.startRow = startRow;
        this.startColumn = startColumn;
    }

    public Double execute() {
        return execute(expressions[startRow][startColumn]);
    }

    public Double execute(int row, int column) {
        return execute(expressions[row][column]);
    }
    private Double execute(Expression expression) {
        if (expression.current != -1 && expression.getResult() == null)
            throw new StackOverflowError("endless recurrent formula");
        if (expression.getResult() != null)
            return expression.getResult();
        return expression.setResult(addition(expression));
    }

//    private Double binary(Expression expression, Level level) {
//        double result;
//        if (expression.hasNext() && !expression.getCurrent(1).level().equals(level))
//            result = binary(expression, Level.values()[level.ordinal()-1]);
//        while (expression.hasNext() && expression.getCurrent(1).level().equals(level)) {
//            Operator operator = expression.next();
//            ((BinaryOperator) operator).apply(result, binary(expression));
//
//            if (operator.stringValue.equals("+")) {
//                result += product(expression);
//            } else if (operator.stringValue.equals("-")) {
//                result -= product(expression);
//            } else throw (new IllegalArgumentException("in operand:" + expression.current));
//        }
//        return result;
//    }

    private Double addition(Expression expression) {
        double result = 0L;
        if (expression.hasNext() && !expression.getCurrent(1).level().equals(Level.ADDITION))
            result += product(expression);
        while (expression.hasNext() && expression.getCurrent(1).level().equals(Level.ADDITION)) {
            Operator operator = expression.next();
            if (operator.stringValue.equals("+")) {
                result += product(expression);
            } else if (operator.stringValue.equals("-")) {
                result -= product(expression);
            } else throw (new IllegalArgumentException("in operand:" + expression.current));
        }
        return result;
    }

    private Double product(Expression expression) {
        double result = 1L;
        if (expression.hasNext() && expression.getCurrent(1).level().compareTo(Level.PRODUCT) < 0)
            result *= power(expression);
        while (expression.hasNext() && expression.getCurrent(1).level().equals(Level.PRODUCT)) {
            Operator operator = expression.next();
            if (operator.stringValue.equals("*")) {
                result *= power(expression);
            } else if (operator.stringValue.equals("/")) {
                result /= power(expression);
            }
        }
        return result;
    }


    private Double power(Expression expression) {
        double result = 0L;
        if (expression.hasNext() && expression.getCurrent(1).level().compareTo(Level.POWER) < 0)
            result = term(expression);
        while (expression.hasNext() && expression.getCurrent(1).stringValue.equals("^")) {
            expression.next();
            if (expression.hasNext()) {
                result = exp(log(result) * term(expression));
            } else throw (new IllegalArgumentException("in operand:" + expression.current));
        }
        return result;
    }

    private Double term(Expression expression) {
        if (expression.hasNext()) {
            if (expression.getCurrent(1).level().compareTo(Level.TERM) != 0)
                throw (new IllegalArgumentException("in operand:" + expression.current));
            if (expression.getCurrent(1) instanceof UnaryFunction) {
                UnaryFunction function = (UnaryFunction) expression.next();
                return function.apply(execute(function.getExpression()));
            }
            if (expression.getCurrent(1) instanceof BinaryFunction) {
                BinaryFunction function = (BinaryFunction) expression.next();
                return function.apply(execute(function.getFirstExpression()), execute(function.getSecondExpression()));
            }
            if (expression.getCurrent(1) instanceof Cell) {
                Cell cell = (Cell) expression.next();
                return execute(cell.row,cell.column);
            }else return ((Number) expression.next()).value();
        } else return 0d;
    }


}
