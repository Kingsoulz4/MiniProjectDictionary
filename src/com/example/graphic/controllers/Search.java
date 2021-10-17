package com.example.graphic.controllers;

import com.sun.speech.freetts.Voice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.*;

import javafx.scene.control.ListView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Search implements Initializable {


    //search
    @FXML
    public static Stage primaryStage = null;

    @FXML
    public ListView listView;

    @FXML
    public TextField inputText;

    @FXML
    public TextField target;

    @FXML
    public TextArea explain;

    @FXML
    public Button searchWord;

    @FXML
    public Button transone;

    @FXML
    public Button transtwo;

    @FXML
    public Button voice;

    public static Voice[] voices;


   //history
    @FXML
    public ListView listViewHistory;

    @FXML
    public Button history;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize");
        inputText.setPromptText(" ");
        target.setEditable(false);
        explain.setEditable(false);
    }

    public void searchWordHandle(ActionEvent event) {

    }

    public void voiceHandle(ActionEvent event) {
        System.out.println("demo search and history");
    }

    public void transoneHandle(ActionEvent event) {

    }

    public void transtwoHandle(ActionEvent event) {

    }

    public void HistoryHandle(ActionEvent event) {

    }






    /**
     * controller chuyển scene others Button
     */
    public void ModifyButtonHandle(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("../FXML/modify.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void FavoriteButtonHandle(ActionEvent event) {
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

    public void SearchButtonHandle(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("../FXML/search.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}







