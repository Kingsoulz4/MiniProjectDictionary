package com.example.Graphics.Controller;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.*;

//import com.example.*;

import javax.swing.text.html.ListView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class historyController implements Initializable {
    @FXML
    public static Stage primaryStage = null;

    @FXML
    public ListView listViewRecent;

    @FXML
    public TextField inputText;

    @FXML
    public Button checkButton;

    @FXML
    public TextField targetArea;

    @FXML
    public TextArea explainArea;

    @FXML
    public Button voiceButton;

    @FXML
    public AnchorPane HistoryAnchorPane;

    public static Voice[] voices;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize");
        HistoryAnchorPane.setVisible(false);
        inputText.setPromptText("Enter something...");
        targetArea.setEditable(false);
        explainArea.setEditable(false);

        inputText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.isEmpty()) {
                    HistoryAnchorPane.setVisible(false);
                    setListViewRecent();
                }
            }
        });

        setListViewRecent();
    }

    /**
     * tra từ trong ListViewRecent + add từ vừa ra vào đó
     */
    public void setListViewRecent() {
        //listViewRecent.setItems(word);

        listViewRecent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                HistoryAnchorPane.setVisible(true);

                //Word selected = listView.getSelectionModel().getSelectedItem();

               // targetArea.setText(selected.getWord_target());
                //String target = selected.getWord_target();
                //
                //
                //addRecent(selected);
            }
        });
    }

    /**
     * thêm từ vào recent
     */
    public void addRecent(String word) {
        System.out.println("-addRecent");
        removeRecent(word);
        // Main.recent.addWord(0, word);
    }

    /**
     * chuyển từng kí tự từ vào recent
     */
    public void removeRecent(String word) {
        for ( ) {

        }
    }

    /**
     * controller voiceButton
     */
    public void voice(String text) {
        VoiceManager vm;

        vm = VoiceManager.getInstance();

        voices = vm.getVoices();

        Voice voice = vm.getVoice("kevin16");

        voice.allocate();

        voice.speak(text);

        voice.deallocate();
    }

    public void voiceButtonHandle(ActionEvent event) {
        voice(targetArea.getText());
    }

    /**
     * controller chuyển scene others Button
     */
    public void searchButtonHandle(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("../FXML/searchPane.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addButtonHandle(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("../FXML/addPane.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void editButtonHandle(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("../FXML/editPane.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void favoriteButtonHandle(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("../FXML/favoritePane.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void historyButtonHandle(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("../FXML/historyPane.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void quitButtonHandle(ActionEvent event) {
        AlertNotification.AlertExit();
    }

}







