package com.example.graphic.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class Home implements Initializable{

    @FXML
    public static Stage primaryStage = null;

    @FXML
    public static Parent modifyPaneRoot = null;

    @FXML
    public static Parent favoritePaneRoot = null;

    @FXML
    public static Parent searchPaneRoot = null;



    @Override
    public void initialize(URL location, ResourceBundle resources){

    }

    public void SearchButtonHandle(ActionEvent event) {
            Scene scene = new Scene(searchPaneRoot);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

    }

    public void ModifyButtonHandle(ActionEvent event){
            Scene scene = new Scene(modifyPaneRoot);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
    }

    public void FavoriteButtonHandle(ActionEvent event) {
        Scene scene = new Scene(favoritePaneRoot);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }




}
