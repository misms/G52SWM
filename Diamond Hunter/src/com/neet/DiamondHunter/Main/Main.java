package com.neet.DiamondHunter.Main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			System.out.println("Game progress :\nGame Started");
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("MapMenu.fxml"));
			Scene scene = new Scene(root,600,357);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent> (){
				public void handle(WindowEvent t){
					Platform.exit();
					System.exit(0);
				}
			});
			primaryStage.setResizable(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
