package com.neet.DiamondHunter.Main;

import javax.swing.JFrame;


import com.neet.DiamondHunter.TileMapEditor.*;
import com.neet.DiamondHunter.Main.GamePanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MapmenuController {
	
	Stage primaryStage= new Stage();
	private static JFrame window;

	
	// Event Listener on Button.onAction
	@FXML
	public void rungame() {
		
		System.out.println("Run game clicked");
		window = new JFrame("Diamond Hunter");	
		
		window.add(new GamePanel());
		
		window.setResizable(false);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	public static JFrame getWindow(){
		return window;
	}
	
	@FXML
	public void mapviewer() throws Exception{
		
		Parent root = FXMLLoader.load(getClass().getResource("TME.fxml"));
        primaryStage.setTitle("Edit Map");        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
	
}
}
