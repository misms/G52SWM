package com.neet.DiamondHunter.TileMapEditor;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

// This is the Tile Map Editor in JavaFX
public class App_JavaFX extends Application{

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        primaryStage.setTitle("TME");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.setResizable(true);
        primaryStage.show();
    }

	/*
	*
	* Method to be added and converted to JavaFX:
	* 1. loadTileSet
	* 2. loadMap
	* 3. draw
	*
	* */
}