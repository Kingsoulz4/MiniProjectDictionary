package com.example.Graphics.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class editController implements Initializable{
    @FXML
    public static Stage primaryStage = null;

    @FXML
    public TextField oldTargetTextField;

    @FXML
    public TextField newTargetTextField;

    @FXML
    public TextArea newExplainTextField;

    @FXML
    public Label messageLabel;

    /** set up các button */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        oldTargetTextField.setPromptText("Enter old target.");
        newTargetTextField.setPromptText("Enter new target.");
        newExplainTextField.setPromptText("Enter new explain.");
    }

    /** controller action Edit Word */
    public void submitButtonHandle(ActionEvent event){

    }

    /** Lệnh Check Word */
    public void fillButtonHandle(ActionEvent event) {
        System.out.println("demo edit controller");
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
