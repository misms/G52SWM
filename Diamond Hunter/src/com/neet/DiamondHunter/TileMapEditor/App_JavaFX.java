package com.neet.DiamondHunter.TileMapEditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// This is the Tile Map Editor in JavaFX
public class App_JavaFX extends Application{

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TME.fxml"));
        primaryStage.setTitle("TME");
        primaryStage.setScene(new Scene(root, 940, 700));
        primaryStage.setResizable(true);
        primaryStage.show();
    }

}