package com.neet.DiamondHunter.TileMapEditor;

import javax.swing.*;

// This is the TileMapEditor in Swing
public class App{
	
	public static void main(String[] args) {
		JFrame window = new JFrame("Tile Map Editor");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(new MyPanel());
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}
}