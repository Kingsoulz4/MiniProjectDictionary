package com.example.main;

import com.example.graphic.controllers.Home;
import com.example.graphic.controllers.Search;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);

    }






















    @Override
    public void start(Stage primaryStage) {
        try {
            Parent home = FXMLLoader.load(getClass().getResource("../graphic/fxml/home.fxml"));
            Scene homeScene = new Scene(home, 600, 400);
            Parent search = FXMLLoader.load(getClass().getResource("../graphic/fxml/search.fxml"));
            Scene searchScene = new Scene(search, 600, 400);
            Home.primaryStage = primaryStage;
            Search.primaryStage = primaryStage;
            primaryStage.setScene(homeScene);
            Home.scene = homeScene;
            Search.scene = searchScene;

            primaryStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
