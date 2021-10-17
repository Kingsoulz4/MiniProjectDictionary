//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.main;

import com.example.graphic.controllers.Favorite;
import com.example.graphic.controllers.Home;
import com.example.graphic.controllers.Modify;
import com.example.graphic.controllers.Search;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public Main() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Parent home = (Parent)FXMLLoader.load(this.getClass().getResource("../graphic/fxml/home.fxml"));
        Scene homeScene = new Scene(home, 600.0D, 400.0D);
        Parent search = (Parent)FXMLLoader.load(this.getClass().getResource("../graphic/fxml/search.fxml"));
        Scene searchScene = new Scene(search, 600.0D, 400.0D);
        Parent modify = (Parent)FXMLLoader.load(this.getClass().getResource("../graphic/fxml/modify.fxml"));
        Scene modifyScene = new Scene(modify, 600.0D, 400.0D);
        Parent favorite = (Parent)FXMLLoader.load(this.getClass().getResource("../graphic/fxml/favorite.fxml"));
        Scene favoriteScene = new Scene(favorite, 600.0D, 400.0D);
        Home.primaryStage = primaryStage;
        Search.primaryStage = primaryStage;
        Modify.primaryStage = primaryStage;
        Favorite.primaryStage = primaryStage;
        primaryStage.setScene(homeScene);
        Home.scene = homeScene;
        Search.scene = searchScene;
        Modify.scene = modifyScene;
        Favorite.scene = favoriteScene;
        primaryStage.show();
    }

    public void stop() throws IOException {
        System.out.println("STOP!!!");
    }
}
