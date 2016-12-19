package com.neet.DiamondHunter.Main;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			System.out.println("Game progress :\nGame Started");
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("MapMenu.fxml"));
			Scene scene = new Scene(root,600,367);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
