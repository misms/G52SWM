package com.neet.DiamondHunter.Main;

import javax.swing.JFrame;


import javafx.fxml.FXML;

public class MapmenuController {

	// Event Listener on Button.onAction
	@FXML
	public void rungame() {
		
		System.out.println("Run game clicked");
		JFrame window = new JFrame("Diamond Hunter");	
		
		window.add(new GamePanel());
		
		window.setResizable(false);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@FXML
	public void mapviewer(){
		JFrame window = new JFrame("Tile Map Editor");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(new MyPanel());
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		
	}
}
