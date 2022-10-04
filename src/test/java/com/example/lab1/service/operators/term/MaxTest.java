package com.example.lab1.service.operators.term;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxTest {

    @Test
    void apply() {
        Max operator = new Max(1,"1,2+2");
        assertEquals(Double.valueOf(operator.apply(1d, 4d)),Double.valueOf(4));
    }
}