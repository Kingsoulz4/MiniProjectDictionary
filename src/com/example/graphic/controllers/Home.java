package com.example.graphic.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

public class Home implements Initializable {
    public static Scene scene;
    public static Stage primaryStage;
    @FXML
    public MenuButton Menu;

    public Home() {
    }

    public void activeSearch(ActionEvent event) {
        try {
            primaryStage.setScene(Search.scene);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void activeModify(ActionEvent event) {
        try {
            primaryStage.setScene(Modify.scene);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void activeFavorite(ActionEvent event) {
        try {
            primaryStage.setScene(Favorite.scene);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void initialize(URL location, ResourceBundle resources) {
    }
}
