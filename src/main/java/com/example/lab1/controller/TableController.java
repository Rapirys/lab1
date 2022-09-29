package com.example.lab1.controller;

import com.example.lab1.service.ExpressionTable;
import com.example.lab1.service.exceptions.IllegalExpression;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class TableController implements Initializable {
    boolean executed;
    ExpressionTable modelTable;
    @FXML
    private TableView<ObservableList<String>> table;

//    @FXML private TableColumn<Statement, Integer> id;
//    @FXML private TableColumn<Students, String> name;
//    @FXML private TableColumn<Students, String> surname;
//    @FXML private TableColumn<Students, Integer> age;
//
//    public ObservableList<Students> list = FXCollections.observableArrayList(
//            new Students(1, "Nava", "nava", 2),
//            new Students(2, "Fahim", "fahim", 9),
//            new Students(3, "Shariful", "Islam", 25)
//    );

    @FXML
    protected void execute() {
        if (!executed) {
            try {
                modelTable = new ExpressionTable(table);
                fillTable(modelTable.executeAll());
            }catch (IllegalExpression e){
                String [][] dataSet = modelTable.getExpressionsAsStrings();
                dataSet[e.getRow()][e.getColumn()] = dataSet[e.getRow()][e.getColumn()].substring(0,e.getPosition()) +
                        "{проблема тут--->}" + dataSet[e.getRow()][e.getColumn()].substring(e.getPosition());
                fillTable(dataSet);
                System.err.println(Arrays.toString(e.getStackTrace()));
            }
            executed = true;
        }else {
            fillTable(modelTable.getExpressionsAsStrings());
            executed = false;
        }

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        fillTable(generateStartTable());
        executed = false;
        modelTable = new ExpressionTable(table);
    }
    private void fillTable(String[][] dataSource) {
        table.getColumns().clear();

        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        for (String[] row : dataSource)
            data.add(FXCollections.observableArrayList(row));
        table.setItems(data);

        for (int i = 0; i < dataSource[0].length; i++) {
            final int currentColumn = i;
            TableColumn<ObservableList<String>, String> column =
                    new TableColumn<>(Character.valueOf((char) (i+'A')).toString());
            column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(currentColumn)));
            column.setEditable(true);
            column.setCellFactory(TextFieldTableCell.forTableColumn());
            column.setPrefWidth(100);
            column.setOnEditCommit(
                    (TableColumn.CellEditEvent<ObservableList<String>, String> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).set(t.getTablePosition().getColumn(), t.getNewValue());
                    });
            table.getColumns().add(column);
        }
    }

    private  String[][] generateStartTable(){
        final int n = 30;
        final int m = 10;
        String[][] dataSource = new String[n][m];
        for (int i = 0; i< n; i++)
            for (int j= 0; j<m; j++)
                dataSource[i][j] = "";
        dataSource[0][0]="2+2";
        return dataSource;
    }
}
