package com.example.lab1;

import com.example.lab1.service.Expression;
import com.example.lab1.service.ExpressionTable;
import com.example.lab1.service.operators.Level;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        List<List<String>> t = new ArrayList<>();
        List<String> r = new ArrayList<>();
        r.add("2*A1+A1");
        r.add("2*3^3+2");
        t.add(r);

        ExpressionTable table= new ExpressionTable(t,0,0);
        System.out.println(table.execute());

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}