//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.graphic.controllers;

import com.example.model.notification.Information;
import com.example.model.sql.MySQLDatabaseConnector;
import com.example.model.text.TextToSpeech;
import com.example.model.word.Word;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Favorite implements Initializable {
    public static Scene scene;
    public static Stage primaryStage;
    @FXML
    public ListView<String> listView;
    public TextArea FavoriteExplain;
    public Button Dislike;
    public Button FavoriteVoice;
    MySQLDatabaseConnector databaseConnector;
    String word_target_selected = "";
    ArrayList<Word> words;

    public Favorite() {
    }

    public void initialize(URL location, ResourceBundle resources) {
        this.FavoriteExplain.setPromptText(" ");
        this.listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Favorite.this.word_target_selected = (String)Favorite.this.listView.getSelectionModel().getSelectedItem();
                Favorite.this.FavoriteExplain.setText(Favorite.this.databaseConnector.parseDataFromDatabase(Favorite.this.databaseConnector.findWord(Favorite.this.word_target_selected).getWord_explain()));
            }
        });
        this.loadFavoriteList();
    }

    public void DislikeHandle(ActionEvent event) {
        MySQLDatabaseConnector databaseConnector = new MySQLDatabaseConnector();
        boolean status = databaseConnector.removeWordFromFavoriteList(this.word_target_selected);
        if (status) {
            new Information("Remove from favorite list", "Successfully");
        } else {
            new Information("Remove from favorite list", "Failed");
        }

        this.loadFavoriteList();
    }

    public void FavoriteVoiceHandle(ActionEvent event) {
        TextToSpeech textToSpeech = new TextToSpeech();
        textToSpeech.speak(this.word_target_selected);
    }

    public void activeSearch(ActionEvent event) {
        primaryStage.setScene(Search.scene);
    }

    public void activeModify(ActionEvent event) {
        primaryStage.setScene(Modify.scene);
    }

    public void activeHome(ActionEvent event) {
        primaryStage.setScene(Home.scene);
    }

    public void loadFavoriteList() {
        this.databaseConnector = new MySQLDatabaseConnector();
        this.words = this.databaseConnector.getListFavoriteWord();
        Set<String> word_targets = new HashSet();
        Iterator var2 = this.words.iterator();

        while(var2.hasNext()) {
            Word w = (Word)var2.next();
            word_targets.add(w.getWord_target());
            System.out.println(w.getWord_target());
        }

        this.listView.getItems().clear();
        this.listView.getItems().addAll(word_targets);
    }
}
