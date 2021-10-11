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
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class favoriteController implements Initializable{
    @FXML
    public static Stage primaryStage = null;

    @FXML
    public ListView listView;

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

    /** set up button */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inputText.setPromptText(" ");
        targetArea.setPromptText(" ");
        explainArea.setPromptText(" ");
        targetArea.setEditable(false);
        explainArea.setEditable(false);

        inputText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.isEmpty()){
                    favoriteAnchorPane.setVisible(false);
                    setListViewRecent();
                }
                else{
                   controlAnchorPane(newValue);
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
                    // lệnh dịch
                    //
                    targetArea.setText(target);
                    //explainArea.setText( );
                }
                else{
                    targetArea.setText("Not found");
                    explainArea.setText("Not found");
                }
            }
        });

        setListViewRecent();
    }

    public void controlAnchorPane(String target) {
        favoriteAnchorPane.setVisible(true);
    }

    /** dịch từ nếu click vào 1 từ trong listView + add từ vừa dịch */
    public void setListViewRecent(){
           listView.setItems();
        // lệnh hiện danh sách từ gần đây
        //
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               favoriteAnchorPane.setVisible(true);
                // câu lệnh dịch
                //

                //addRecent(selected);
            }
        });
    }
    /** thêm từ vào recent */
    public void addRecent(String word){
        System.out.println("-addRecent");
        // câu lệnh add từ vừa dịch vào listView
        //
    }


    public void voice(String text){
        VoiceManager vm;

        vm = VoiceManager.getInstance();

        Voice[] voices = vm.getVoices();

        Voice voice = vm.getVoice("kevin16");

        voice.allocate();

        voice.speak(text);

        voice.deallocate();
    }
    public void voiceButtonHandle(ActionEvent event){
        voice(targetArea.getText());
    }


    /** xóa target và explain nếu không thích từ đó */
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
