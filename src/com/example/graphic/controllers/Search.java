package com.example.graphic.controllers;

import com.example.model.word.Meaning;
import com.example.model.word.Word;
import com.example.model.online.API;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Map;

public class Search {

    public static Stage primaryStage;
    public static Scene scene;
    Word word;

    @FXML
    public Button HomePage;

    @FXML
    public TextField txtSearch;

    @FXML
    public TextArea txtWordExplain;

    @FXML
    public Button btnSpeak;

    public void returnHomePage(ActionEvent event) {
        primaryStage.setScene(Home.scene);
    }

    public void doSearch(ActionEvent event) {
        API API = new API();
        word = API.getWordData(txtSearch.getText());
        String textExplain = "";
        textExplain += "/" + word.getPhonetic() +"/"+"\n";
        for (Map.Entry<String, ArrayList<Meaning>> entry: word.getPartOfSpeech_Meaning().entrySet()) {
            textExplain +="(" +entry.getKey() +")" + "\n";
            for (Meaning m:entry.getValue()) {
                //Add definitions
                textExplain += m.getDefinition() + "\n";

                //Add example
                if (m.getExample()!=null) {
                    textExplain += "Example: " + m.getExample() + "\n";
                }

                //Add synonyms to text explain
                if (m.getSynonyms().size()!=0) {
                    textExplain += "Synonyms: ";
                    for (String synonym : m.getSynonyms()) {
                        textExplain += synonym + ", ";
                    }
                    textExplain +="\n";
                }

                //Add antonyms to text explain
                if (m.getAntonyms().size()!=0) {
                    textExplain += "Antonyms: ";
                    for (String antonym : m.getAntonyms()) {
                        textExplain += antonym + ", ";
                    }
                    textExplain += "\n";
                }
                textExplain += "\n";
            }
            textExplain +="\n";
        }
        txtWordExplain.setText(textExplain);
        txtWordExplain.setWrapText(true);
    }

    public void speak(ActionEvent event) {
        MediaPlayer mediaPlayer = new MediaPlayer(word.getPhoneticAudio());
        System.out.println(word.getPhoneticAudio().getSource());
        System.out.println("Speak");
        mediaPlayer.play();
    }
}
