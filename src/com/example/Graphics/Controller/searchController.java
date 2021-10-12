package com.example.Graphics.Controller;

import com.example.main.Main;
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
    public Button removeButton;

    @FXML
    public Button voiceButton;

    @FXML
    public AnchorPane rightAnchorPane;

    public static Voice[] voices;

    @FXML
    public static Parent editPaneRoot = null;

    @FXML
    public static Parent historyPaneRoot = null;

    @FXML
    public static Parent addPaneRoot = null;

    @FXML
    public static Parent favoritePaneRoot = null;


    /** set up các button */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        System.out.println("initialize");
        rightAnchorPane.setVisible(false);
        inputText.setPromptText(" ");
        targetArea.setEditable(false);
        explainArea.setEditable(false);




    }
    public void setupbutton(ActionEvent event) {
        rightAnchorPane.setVisible(true);
        System.out.println("demo search Controller");
    }

    public void voiceButtonHandle(ActionEvent event) {
    }

    public void removeButtonHandle(ActionEvent event){
        targetArea.setText("");
        explainArea.setText("");
    }


    /** controller chuyển scene others Button */
    public void addButtonHandle(ActionEvent event) {
            Scene scene = new Scene(addPaneRoot);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

    }

    public void editButtonHandle(ActionEvent event){
            Scene scene = new Scene(editPaneRoot);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
    }

    public void favoriteButtonHandle(ActionEvent event){
            Scene scene = new Scene(favoritePaneRoot);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

    }

    public void historyButtonHandle(ActionEvent event) {
            Scene scene = new Scene(historyPaneRoot);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

    }

    public void quitButtonHandle(ActionEvent event){
        AlertNotification.AlertExit();
    }

}
