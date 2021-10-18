package com.example.graphic.controllers;

import com.example.model.notification.Confirmation;
import com.example.model.notification.Information;
import com.example.model.sql.MySQLDatabaseConnector;
import com.example.model.word.Word;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Modify implements Initializable {
    public static Scene scene;
    public static Stage primaryStage;
    @FXML
    public TextField txtWordAdd;
    public TextField txtExplainAdd;
    public TextField txtWordEdit;
    public TextField txtExplainEdit;
    public TextField txtWordDelete;
    public Button btnSubmitAdd;
    public Button btnSubmitEdit;
    public Button btnSubmitDelete;
    MySQLDatabaseConnector databaseConnector;

    public Modify() {
    }

    public void addWord(ActionEvent event) {
        String word_target = this.txtWordAdd.getText();
        String word_explain = this.txtExplainAdd.getText();
        Word word = new Word(word_target, word_explain);
        Confirmation confirmation = new Confirmation("Add new word", "Are you sure to add this word");
        boolean addedSuccessful = false;
        if (confirmation.isConfirmed()) {
            addedSuccessful = this.databaseConnector.addWord(word);
            if (addedSuccessful) {
                new Information("Add status", "Successfully");
            } else {
                new Information("Add status", "Failed");
            }
        }

    }

    public void editWord(ActionEvent event) {
        String word_target = this.txtWordEdit.getText();
        String word_explain = this.txtExplainEdit.getText();
        Word word = new Word(word_target, word_explain);
        Confirmation confirmation = new Confirmation("Edit word", "Are you sure to edit this word");
        boolean editedSuccessful = false;
        if (confirmation.isConfirmed()) {
            editedSuccessful = this.databaseConnector.editWord(word);
            if (editedSuccessful) {
                new Information("Edit status", "Successfully");
            } else {
                new Information("Edit status", "Failed");
            }
        }

    }

    public void deleteWord(ActionEvent event) {
        String word = this.txtWordDelete.getText();
        Confirmation confirmation = new Confirmation("Add new word", "Are you sure to add this word");
        boolean deletedSuccessful = false;
        if (confirmation.isConfirmed()) {
            deletedSuccessful = this.databaseConnector.deleteWord(word);
            if (deletedSuccessful) {
                new Information("Delete status", "Successfully");
            } else {
                new Information("Delete status", "Failed");
            }
        }

    }

    public void initialize(URL location, ResourceBundle resources) {
        this.databaseConnector = new MySQLDatabaseConnector();
    }

    public void activeHome(ActionEvent event) {
        primaryStage.setScene(Home.scene);
    }

    public void activeSearch(ActionEvent event) {
        primaryStage.setScene(Search.scene);
    }

    public void activeFavorite(ActionEvent event) {
        primaryStage.setScene(Favorite.scene);
    }
}
