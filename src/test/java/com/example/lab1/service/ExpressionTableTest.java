package com.example.lab1.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTableTest {

    @Test
    void executeAll() {
        String[][] table = new String[1][1];
        table[0][0] = ("2+6^2-max(4/2,1)");
        ExpressionTable expressionTable = new ExpressionTable(table, 1, 1);
        String[][] r = expressionTable.executeAll();
        assertEquals(Double.valueOf(36), Double.valueOf(r[0][0]));
    }
}