package com.example.graphic.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;




public class Modify implements Initializable {

    //Add
    @FXML
    public static Stage primaryStage = null;

    @FXML
    public TextField AddNewWord;

    @FXML
    public TextField ExplainNewWord;

    @FXML
    public Label Message;

    @FXML
    public Button AddButton;

    //Edit
    @FXML
    public TextField EditOldWord;

    @FXML
    public TextField EditNewWord;

    @FXML
    public TextField EditNewExplain;

    @FXML
    public Label MessageEdit;

    @FXML
    public Button EditButton;

    @FXML
    public Button EditCheck;

    @FXML
    public TextField InputWord;

    @FXML
    public Button DeleteButton;







    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AddNewWord.setPromptText("Enter new target.");
        ExplainNewWord.setPromptText("Enter new explain.");
        EditOldWord.setPromptText(" ");
        EditNewWord.setPromptText(" ");
        EditNewExplain.setPromptText(" ");

    }


    public void AddButtonHandle(ActionEvent event) {
        System.out.println("demo add controller");
    }

    public void EditButtonHandle(ActionEvent event) {

    }

    public void EditCheckHandle(ActionEvent event) {

    }

    public void DeleteWordHandle(ActionEvent event) {

    }








    public void SearchButtonHandle(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("../FXML/search.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ModifyButtonHandle(ActionEvent event) {
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




}



