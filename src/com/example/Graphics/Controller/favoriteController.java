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

import javafx.scene.control.ListView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class favoriteController implements Initializable{
    @FXML
    public static Stage primaryStage = null;

    @FXML
    public ListView favoriteListView;

    @FXML
    public TextField targetArea;

    @FXML
    public TextArea explainArea;

    @FXML
    public TextField inputText;

    @FXML
    public AnchorPane favoriteAnchorPane ;

    @FXML
    public Button voiceButton;

    @FXML
    public Button dislikeButton;

    @FXML
    public Button favoriteSearch;

    /** set up button */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inputText.setPromptText(" ");
        targetArea.setPromptText(" ");
        explainArea.setPromptText(" ");
        targetArea.setEditable(false);
        explainArea.setEditable(false);



    }

    public void controlAnchorpane(ActionEvent event) {

        favoriteAnchorPane.setVisible(true);
    }





    public void voiceButtonHandle(ActionEvent event){
        System.out.println("demo favorite controller");
    }



    public void dislikeButtonHandle(ActionEvent event){
        //
        targetArea.setText("");
        explainArea.setText("");

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
