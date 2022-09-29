package com.example.lab1;

import com.example.lab1.controller.TableController;
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

import static java.lang.Double.doubleToLongBits;
import static java.lang.Math.exp;
import static java.lang.Math.log;


public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        List<List<String>> t = new ArrayList<>();
        List<String> r = new ArrayList<>();

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