package com.example.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.Graphics.Controller.*;
import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../Graphics/FXML/searchPane.fxml"));
        Scene scene = new Scene(root, 520, 400);
        stage.setTitle("Dictionary");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        searchController.editPaneRoot = FXMLLoader.load(getClass().getResource("../Graphics/FXML/editPane.fxml"));
        searchController.historyPaneRoot = FXMLLoader.load(getClass().getResource("../Graphics/FXML/historyPane.fxml"));
        searchController.addPaneRoot = FXMLLoader.load(getClass().getResource("../Graphics/FXML/addPane.fxml"));
        searchController.favoritePaneRoot = FXMLLoader.load(getClass().getResource("../Graphics/FXML/favoritePane.fxml"));

        searchController.primaryStage = stage;
        addController.primaryStage = stage;
        editController.primaryStage = stage;
        favoriteController.primaryStage = stage;
        historyController.primaryStage = stage;



    }

    @Override
    public void stop() throws IOException {
        System.out.println("STOP!!!");
        //
        //
        //
    }


}