package com.example.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.Graphics.Controller.*;

//import com.example.Base.*;

import java.io.IOException;

public class Main extends Application{
  //
    //
    //


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