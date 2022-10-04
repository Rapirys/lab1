package com.example.lab1.service;

import com.example.lab1.service.exceptions.IllegalExpression;
import com.example.lab1.service.operators.*;
import com.example.lab1.service.operators.infixBinaryOperator.BinaryOperator;
import com.example.lab1.service.operators.term.BinaryFunction;
import com.example.lab1.service.operators.term.Cell;
import com.example.lab1.service.operators.term.Number;
import com.example.lab1.service.operators.term.UnaryFunction;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.List;

import static java.lang.Math.round;

public class ExpressionTable {
    Expression[][] expressions;
    Integer m;
    Integer n;

    public ExpressionTable(TableView<ObservableList<String>> table) {
        this.m = table.getColumns().size();
        this.n = table.getItems().size();
        this.expressions = new Expression[n][m];
        for (int i = 0; i < n; i++) {
            ObservableList<String> cells = table.getItems().get(i);
            for (int j = 0; j < m; j++) {
                expressions[i][j] = Expression.parse(cells.get(j));
            }
        }
    }

    public ExpressionTable(String[][] table, int columns, int rows) {
        this.m = columns;
        this.n = rows;
        this.expressions = new Expression[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                expressions[i][j] = Expression.parse(table[i][j]);
            }
        }
    }

    public String[][] executeAll(){
        String[][] result = new String[n][m];
        for (int i = 0; i<n; i++)
            for (int j = 0; j<m; j++) {
                try{
                    Double a = execute(i, j);
                    if (a == null)
                        result[i][j] = "";
                    else result[i][j] = String.valueOf(round(a));
                }catch (IllegalExpression e){
                    throw new IllegalExpression(e, i, j);
                }
            }
        return result;
    }
    public String[][] getExpressionsAsStrings() {
        String[][] result = new String[n][m];
        for (int i = 0; i<n; i++)
            for (int j = 0; j<m; j++) {
                result[i][j] = expressions[i][j].getString();
            }
        return result;
    }

    private Double execute(int row, int column) {
        return execute(expressions[row][column]);
    }
    private Double execute(Expression expression) {
        if (expression.current != -1 && expression.getResult() == null)
            throw new IllegalExpression("Обчислення клітинок рекурентно зацикелне саме на себе", 0);
        if (expression.getResult() != null)
            return expression.getResult();
        return expression.setResult(infixBinary(expression,Level.PREDICATE));
    }

    private Double infixBinary(Expression expression, Level level) {
        Double result = null;
        if (expression.hasNext()) {
            if (level.ordinal() == 0)
                return term(expression);
            else if (expression.getCurrent(1).level().compareTo(level)<0)
                result = infixBinary(expression, Level.values()[level.ordinal() - 1]);
            else throw new IllegalExpression(
                    "Помилка у позиції: " + expression.getCurrent(0).position,expression.getCurrent(0).position );
        }
        while (expression.hasNext() && expression.getCurrent(1).level().compareTo(level)<=0) {
            if (expression.getCurrent(1).level().compareTo(level)==0) {
                Operator operator = expression.next();
                if (expression.hasNext())
                    result = ((BinaryOperator) operator).apply(result, infixBinary(expression, Level.values()[level.ordinal() - 1]));
                else throw new IllegalExpression(
                        "Помилка у позиції: " + expression.getCurrent(0).position,expression.getCurrent(0).position );
            } else throw new IllegalExpression(
                    "Помилка у позиції: " + expression.getCurrent(0).position,expression.getCurrent(0).position );
        }
        return result;
    }
    private Double term(Expression expression) {
        if (expression.hasNext()) {
            if (expression.getCurrent(1).level().compareTo(Level.TERM) != 0)
                throw new IllegalExpression(
                        "Помилка у позиції: " + expression.getCurrent(0).position,expression.getCurrent(0).position );
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
                if (cell.row<0 || cell.row>=n || cell.column<0 ||
                        cell.column>=m || expressions[cell.row][cell.column].getString().equals(""))
                    throw new IllegalExpression(
                            "Посилланя на неіснуючу клітинку у позиції: " +
                                    expression.getCurrent(0).position,expression.getCurrent(0).position );
                return execute(cell.row,cell.column);
            }else return ((Number) expression.next()).value();
        } else return 0d;
    }


}
