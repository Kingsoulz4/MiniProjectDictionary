package com.example.main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.graphic.controllers.*;
import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../graphic/fxml/home.fxml"));
        Scene scene = new Scene(root, 520, 400);
        stage.setTitle("Dictionary");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

         Home.searchPaneRoot = FXMLLoader.load(getClass().getResource("../graphic/FXML/search.fxml"));
         Home.modifyPaneRoot = FXMLLoader.load(getClass().getResource("../graphic/FXML/modify.fxml"));
         Home.favoritePaneRoot = FXMLLoader.load(getClass().getResource("../graphic/fxml/favorite.fxml"));

        Home.primaryStage = stage;
        Modify.primaryStage = stage;
        Favorite.primaryStage = stage;
        Search.primaryStage = stage;



    }

    @Override
    public void stop() throws IOException {
        System.out.println("STOP!!!");
        //
        //
        //
    }


}