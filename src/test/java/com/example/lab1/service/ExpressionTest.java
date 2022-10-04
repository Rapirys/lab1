package com.example.lab1.service;

import com.example.lab1.service.exceptions.ParseIllegalExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTest {

    @Test
    void parseNormal() {
        Expression expression = Expression.parse("2+2");
        assertEquals(expression.next().stringValue, "2");
        assertEquals(expression.next().stringValue, "+");
        assertEquals(expression.next().stringValue, "2");
    }

    @Test
    void parseWrong() {
        boolean f = false;
        try{
            Expression expression = Expression.parse("2+2ds");
        }catch (ParseIllegalExpression e){
            f = true;
        }
        assertTrue(f);
    }
}