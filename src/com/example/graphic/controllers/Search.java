//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.graphic.controllers;

import com.example.model.notification.Information;
import com.example.model.online.API;
import com.example.model.sql.MySQLDatabaseConnector;
import com.example.model.text.TextToSpeech;
import com.example.model.word.Meaning;
import com.example.model.word.Word;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Map.Entry;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Search implements Initializable {
    public static Stage primaryStage;
    public static Scene scene;
    public static int status = 0;
    @FXML
    public Button HomePage;
    public TextField txtSearch;
    public TextArea txtWordExplain;
    public Button btnSpeak;
    public CheckBox onlineCheck;
    public CheckBox offlineCheck;
    public ListView<String> listviewSuggest;
    public ListView<String> listviewHistory;
    public TextArea txtExplainHistory;
    public Button btnSpeakInHistory;
    public Button btnAddFavorite;
    Word word;
    Set<String> listHistory;
    MySQLDatabaseConnector databaseConnector;

    public Search() {
    }

    public void returnHomePage(ActionEvent event) {
        primaryStage.setScene(Home.scene);
    }

    public void changeStatus(ActionEvent event) {
        if (this.onlineCheck.isSelected()) {
            this.offlineCheck.setSelected(false);
            status = 1;
        }

        if (this.offlineCheck.isSelected()) {
            this.onlineCheck.setSelected(false);
            status = 0;
        }

    }

    public void doSearch(ActionEvent event) {
        this.txtWordExplain.clear();
        this.listHistory.add(this.txtSearch.getText());
        this.listviewHistory.getItems().addAll(this.listHistory);
        this.btnAddFavorite.setVisible(true);
        this.btnSpeak.setVisible(true);
        if (this.onlineCheck.isSelected()) {
            this.searchOnline();
        } else if (this.offlineCheck.isSelected()) {
            this.searchOffline();
        }

    }

    public void searchOffline() {
        try {
            String s = this.txtSearch.getText();
            MySQLDatabaseConnector mySQLDatabaseConnector = new MySQLDatabaseConnector();
            this.word = mySQLDatabaseConnector.findWord(s);
            if (this.word == null) {
                return;
            }

            System.out.println(this.word);
            String word_explain = this.word.getWord_explain();
            String res = this.databaseConnector.parseDataFromDatabase(word_explain);
            this.txtWordExplain.setText(res);
            this.txtWordExplain.setWrapText(true);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public void searchOnline() {
        API API = new API();
        this.word = API.getWordData(this.txtSearch.getText());
        String textExplain = "";
        textExplain = textExplain + "/" + this.word.getPhonetic() + "/\n";

        for(Iterator var3 = this.word.getPartOfSpeech_Meaning().entrySet().iterator(); var3.hasNext(); textExplain = textExplain + "\n") {
            Entry<String, ArrayList<Meaning>> entry = (Entry)var3.next();
            textExplain = textExplain + "(" + (String)entry.getKey() + ")\n";

            for(Iterator var5 = ((ArrayList)entry.getValue()).iterator(); var5.hasNext(); textExplain = textExplain + "\n") {
                Meaning m = (Meaning)var5.next();
                textExplain = textExplain + m.getDefinition() + "\n";
                if (m.getExample() != null) {
                    textExplain = textExplain + "Example: " + m.getExample() + "\n";
                }

                Iterator var7;
                String antonym;
                if (m.getSynonyms().size() != 0) {
                    textExplain = textExplain + "Synonyms: ";

                    for(var7 = m.getSynonyms().iterator(); var7.hasNext(); textExplain = textExplain + antonym + ", ") {
                        antonym = (String)var7.next();
                    }

                    textExplain = textExplain + "\n";
                }

                if (m.getAntonyms().size() != 0) {
                    textExplain = textExplain + "Antonyms: ";

                    for(var7 = m.getAntonyms().iterator(); var7.hasNext(); textExplain = textExplain + antonym + ", ") {
                        antonym = (String)var7.next();
                    }

                    textExplain = textExplain + "\n";
                }
            }
        }

        this.txtWordExplain.setText(textExplain);
        this.word.setWord_explain(textExplain);
        this.txtWordExplain.setWrapText(true);
    }

    public void speak(ActionEvent event) {
        if (status == 1) {
            MediaPlayer mediaPlayer = new MediaPlayer(this.word.getPhoneticAudio());
            System.out.println(this.word.getPhoneticAudio().getSource());
            System.out.println("Speak");
            mediaPlayer.play();
        } else {
            TextToSpeech textToSpeech = new TextToSpeech();
            textToSpeech.speak(this.word.getWord_target());
        }

    }

    public void suggest(KeyEvent event) {
        this.listviewSuggest.getItems().clear();
        ArrayList<String> listWordSuggest = this.databaseConnector.findWordPattern(this.txtSearch.getText());
        if (listWordSuggest != null) {
            this.listviewSuggest.getItems().addAll(listWordSuggest);
        }

    }

    public void initialize(URL location, ResourceBundle resources) {
        this.listviewSuggest.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Search.this.txtSearch.setText((String)Search.this.listviewSuggest.getSelectionModel().getSelectedItem());
            }
        });
        this.databaseConnector = new MySQLDatabaseConnector();
        this.listHistory = new HashSet();
        this.listviewHistory.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String s = (String)Search.this.listviewHistory.getSelectionModel().getSelectedItem();
                Word word1 = Search.this.databaseConnector.findWord(s);
                Search.this.txtExplainHistory.setText(Search.this.databaseConnector.parseDataFromDatabase(word1.getWord_explain()));
            }
        });
        this.btnSpeak.setVisible(false);
        this.btnAddFavorite.setVisible(false);
    }

    public void activeHome(ActionEvent event) {
        primaryStage.setScene(Home.scene);
    }

    public void activeModify(ActionEvent event) {
        primaryStage.setScene(Modify.scene);
    }

    public void activeFavorite(ActionEvent event) {
        primaryStage.setScene(Favorite.scene);
    }

    public void addWordToFavoriteList(ActionEvent event) {
        boolean status = this.databaseConnector.addWordToFavoriteList(this.txtSearch.getText());
        if (status) {
            new Information("Add to favorite list", "Successfully");
        } else {
            new Information("Add to favorite list", "Failed");
        }

    }
}
