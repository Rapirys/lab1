//package com.example.lab1.controller;
//
//import com.example.lab1.service.Statement;
//import javafx.fxml.FXML;
//import javafx.scene.control.TableView;
//
//public class TableController {
//    @FXML
//    private TableView table;
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
//
//    @Override
//    public void initialize(URL arg0, ResourceBundle arg1) {
//        i
//        id.setCellValueFactory(new PropertyValueFactory<Students, Integer>("id"));
//        name.setCellValueFactory(new PropertyValueFactory<Students, String>("name"));
//        surname.setCellValueFactory(new PropertyValueFactory<Students, String>("surname"));
//        age.setCellValueFactory(new PropertyValueFactory<Students, Integer>("age"));
//        table.setItems(list);
//    }
//}
