package com.example.lab1.controller;

import com.example.lab1.service.ExpressionTable;
import com.example.lab1.service.exceptions.IllegalExpression;
import com.example.lab1.service.exceptions.ParseIllegalExpression;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Math.max;


public class TableController implements Initializable {
    final int n = 30;
    final int m = 10;
    boolean executed;
    ExpressionTable modelTable;

    @FXML
    private Text errorMessage;
    @FXML
    private Button executionButton;
    @FXML
    private TableView<ObservableList<String>> table;

    @FXML
    protected void info(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ілля Ротань Лабороаторна робота 1 \n" +
                "Введіть значенння виразу та натисніть enter,\nнатисніть на " +
                "кнопку Обчислити/До редагування для переключення між режимами \n");
        alert.setHeaderText("Інформація");
        alert.setHeaderText("Інформація");
        alert.showAndWait();
    }
    @FXML
    protected void execute() {
        if (!executed) {
            try {
                modelTable = new ExpressionTable(table);
                fillTable(modelTable.executeAll());
            }catch (IllegalExpression e){
                String [][] dataSet = modelTable.getExpressionsAsStrings();
                int position = max(e.getPosition(),dataSet[e.getRow()][e.getColumn()].length());
                dataSet[e.getRow()][e.getColumn()] = dataSet[e.getRow()][e.getColumn()].substring(0,position) +
                        "{проблема тут--->}" + dataSet[e.getRow()][e.getColumn()].substring(position);
                fillTable(dataSet);
                errorMessage.setText(e.getMessage());
                errorMessage.setVisible(true);
                e.printStackTrace();
            }catch (Exception e){
                errorMessage.setText("Сталася помилка: " + e.getMessage());
                errorMessage.setVisible(true);
                e.printStackTrace();
            }
            executionButton.setText("До редагування");
            executed = true;
        }else {
            fillTable(modelTable.getExpressionsAsStrings());
            executionButton.setText("Обчислити");
            errorMessage.setVisible(false);
            executed = false;
        }

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        fillTable(generateStartTable());
        executed = false;
        modelTable = new ExpressionTable(table);
    }
    private void fillTable(String[][] source) {
        String[][] dataSource= new String[n][m];
        for (int j=0; j<n; j++)
            dataSource[j][0] = String.valueOf(j);
        for (int i = 0; i< n; i++)
            for (int j= 1; j<m; j++)
                dataSource[i][j] = source[i][j-1];

        table.getColumns().clear();

        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        for (String[] row : dataSource)
            data.add(FXCollections.observableArrayList(row));
        table.setItems(data);

        for (int i = 0; i < dataSource[0].length; i++) {
            final int currentColumn = i;
            TableColumn<ObservableList<String>, String> column;
            if(i != 0) {
                column = new TableColumn<>(Character.valueOf((char) (i + 'A' - 1)).toString());
                column.setEditable(true);
            }else{
                column = new TableColumn<>(Character.valueOf(' ').toString());
                column.setEditable(false);
            }
            column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(currentColumn)));
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
        String[][] dataSource = new String[n][m];
        for (int i = 0; i< n; i++)
            for (int j= 0; j<m; j++)
                dataSource[i][j] = "";
        dataSource[0][0]="2+2";
        return dataSource;
    }
}
