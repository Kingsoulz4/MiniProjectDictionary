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

//import com.example.Base.*;
//import com.example.*;


public class addController implements Initializable {
    @FXML
    public static Stage primaryStage = null;

    @FXML
    public TextField targetTextField;

    @FXML
    public TextArea explainTextField;

    @FXML
    public Label messageLabel;

    /**
     * set up các Button
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        targetTextField.setPromptText("Enter new target.");
        explainTextField.setPromptText("Enter new explain.");
    }

    /**
     * controller action addWord
     */
    public void submitButtonHandle(ActionEvent event) {
        String target = targetTextField.getText().trim();
        String explain = explainTextField.getText().trim() + "\n";

        if (!isEmpty()) {
            // add word
            done();
        } else {
            messageLabel.setText("Word exist");
        }
    }


    /**
     * điều kiện rỗng
     */
    public boolean isEmpty() {
        if (targetTextField.getText().isEmpty()) {
            messageLabel.setText("'New target' is empty");
            return true;
        } else if (explainTextField.getText().isEmpty()) {
            messageLabel.setText("'New explain' is empty");
            return true;
        }
        return false;
    }

    /**
     * set up new event
     */
    public void done() {
        messageLabel.setText("Add word complete");
        targetTextField.setText("");
        explainTextField.setText("");
    }

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

    /**
     * controller chuyển scene others Button
     */
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



