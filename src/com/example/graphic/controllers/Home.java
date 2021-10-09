package com.example.graphic.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

public class Home {

    public static Scene scene;

    @FXML
    public static Stage primaryStage;
    @FXML
    public MenuButton Menu;

    public void activeSearch(ActionEvent event) {
        try {
            primaryStage.setScene(Search.scene);


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
