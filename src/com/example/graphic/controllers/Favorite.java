package com.example.graphic.controllers;


import com.sun.speech.freetts.Voice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;

import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Favorite implements Initializable{
    @FXML
    public static Stage primaryStage = null;

    @FXML
    public ListView listView;

    @FXML
    public TextArea FavoriteExplain;

    @FXML
    public Button Dislike;

    @FXML
    public Button FavoriteVoice;

    public static Voice[] voices;





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FavoriteExplain.setPromptText(" ");
    }

    public void DislikeHandle(ActionEvent event) {

    }

    public void FavoriteVoiceHandle(ActionEvent event) {

    }





    public void SearchButtonHandle(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(this.getClass().getResource("../FXML/search.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ModifyButtonHandle(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("../FXML/modify.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void FavoriteButtonHandle(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("../fxml/favorite.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}
