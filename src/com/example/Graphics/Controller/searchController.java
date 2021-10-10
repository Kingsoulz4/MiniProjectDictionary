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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.*;

//import com.example.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class searchController  implements Initializable{

    @FXML
    public static Stage primaryStage = null;

    @FXML
    public TextField inputText;

    @FXML
    public ListView listView;

    @FXML
    public TextField targetArea;

    @FXML
    public TextArea explainArea;

    @FXML
    public Button submitButton;

    @FXML
    public Button editWordButton;

    @FXML
    public Button removeWordButton;

    @FXML
    public Button favoriteWordButton;

    @FXML
    public Button voiceButton;

    @FXML
    public AnchorPane rightAnchorPane;

    public static Voice[] voices;



    /** set up các button */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        System.out.println("initialize");
        rightAnchorPane.setVisible(false);
        inputText.setPromptText("Enter something...");
        targetArea.setEditable(false);
        explainArea.setEditable(false);

/** controller inputText */
        inputText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.isEmpty()){
                    rightAnchorPane.setVisible(false);
                    setListViewRecent();
                }
                else{
                    handlesearch(newValue);
                }
            }
        });

/** Dịch từ bằng lệnh ENTER */
        inputText.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                targetArea.setText("");
                explainArea.setText("");

                if(keyEvent.getCode() == KeyCode.ENTER){
                    String target = inputText.getText();
                    //
                    //
                    targetArea.setText(target);
                    //explainArea.setText();
                }
                else{
                    targetArea.setText("Not found");
                    explainArea.setText("Not found");
                }
            }
        });

    setListViewRecent();
}


    /** hiện bảng nghĩa từ nếu có action tra từ */
    public void handlesearch (String target){
        rightAnchorPane.setVisible(true);
    }

    /** dịch từ nếu click vào 1 từ trong listView + add từ vừa dịch */
    public void setListViewRecent(){
        // listView.setItems();

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rightAnchorPane.setVisible(true);

                // Word selected = listView.getSelectionModel().getSelectedItem();

                //targetArea.setText(selected.getWord_target());
               // String target = selected.getWord_target();
                //
                //
                //addRecent(selected);
            }
        });
    }
    /** thêm từ vào recent */
    public void addRecent(String word){
        System.out.println("-addRecent");
        removeRecent(word);
        // Main.recent.addWord(0, word);
    }

    /** chuyển từng kí tự từ vào recent */
    public void removeRecent(String word){
        for ( ) {

        }
    }

    /** controller voiceButton */
    public void voice(String text){
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



    /** đưa từ + giải nghĩa về ban đầu nếu dùng lệnh này */
    public void removeWordButtonHandle(ActionEvent event){
        String explain = explainArea.getText();
        if( ){
            explain = explain.substring(0, explain.indexOf("Google translate"));
            explain = explain.trim() + "\n";
        }
        else{
            explain = explain.trim() + "\n";
        }
        targetArea.setText("");
        explainArea.setText("");
    }


    /** controller chuyển scene others Button */
    public void searchButtonHandle(ActionEvent event){
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
        try{
            Parent root = FXMLLoader.load(this.getClass().getResource("../FXML/addPane.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void editButtonHandle(ActionEvent event){
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

    public void favoriteButtonHandle(ActionEvent event){
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

    public void quitButtonHandle(ActionEvent event){
        AlertNotification.AlertExit();
    }

}
