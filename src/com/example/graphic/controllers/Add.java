package com.example.graphic.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Add {
    public static Stage primaryStage;
    public static Scene scene;

    @FXML
    public Button HomePage;

    public void returnHomePage(ActionEvent event) {
        primaryStage.setScene(Home.scene);
    }
}
