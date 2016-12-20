package com.neet.DiamondHunter.TileMapEditor;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;


public class TMEController implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        boat.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                select=1;
            }
        });

        axe.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                select=0;
            }
        });

        canvas.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                //System.out.println(((int)e.getX())+" "+((int)e.getY()));
                //System.out.println(((int)e.getX()/16)+" "+((int)e.getY()/16)+"\n");
                if(select==0){
                axeX=(int)e.getX()/16;
                axeY=(int)e.getY()/16;
                axex.setText(Integer.toString(axeX));
                axey.setText(Integer.toString(axeY));
                } else{
                    boatX=(int)e.getX()/16;
                    boatY=(int)e.getY()/16;
                    boatx.setText(Integer.toString(boatX));
                    boaty.setText(Integer.toString(boatY));
                }

            }
        });

        GraphicsContext g = canvas.getGraphicsContext2D();
        loadTiles("/Tilesets/testtileset.gif");
        loadMap("/Maps/testmap.map");
        draw(g);


    }

    // Map Drawing: to canvas
    public void loadTiles(String s) {

        try {

            Image setTile=new Image(s);
            numTilesAcross = (int) setTile.getWidth() / tileSize;
            tiles = new Image[2][numTilesAcross];

            for(int col = 0; col < numTilesAcross; col++) {
                tiles[0][col] = new WritableImage(
                        setTile.getPixelReader(),
                        col * tileSize,
                        0,
                        tileSize,
                        tileSize);
                tiles[1][col] = new WritableImage(
                        setTile.getPixelReader(),
                        col * tileSize,
                        tileSize,
                        tileSize,
                        tileSize);
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
    public void loadMap(String s) {

        try {

            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(in)
            );

            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            map = new int[numRows][numCols];

            String delims = "\\s+";
            for(int row = 0; row < numRows; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delims);
                for(int col = 0; col < numCols; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
    public void draw(GraphicsContext g) {

        for(int row = 0; row < 40; row++) {

            if(row >= numRows) break;

            for(int col = 0; col < 40; col++) {

                if(col >= numCols) break;
                if(map[row][col] == 0) continue;

                int rc = map[row][col];
                int r = rc / numTilesAcross;
                int c = rc % numTilesAcross;

                g.drawImage(
                        tiles[r][c],
                        col * tileSize,
                        row * tileSize
                );

            }

        }

    }
    
    @FXML
	public void back() throws Exception{
		Scene scene = back.getScene();
		Stage currentscene = (Stage)scene.getWindow();
		currentscene.hide();
	}

    /////////////////////////////////////////////////////

    // Variables Declaration
    public Image image;
    public static int axeX,axeY,boatX,boatY;
    int select=0;

    // map
    private int[][] map;
    private int tileSize=16;
    private int numRows;
    private int numCols;

    private Image[][] tiles;
    private int numTilesAcross;

    public static final int WIDTH = 640;
    public static final int HEIGHT = 670;
    public static final int SCALE = 1;
    public static int TILESIZE = 30;
    ///////////////////////////////////////////////////////
    @FXML
    private Canvas canvas;

    @FXML
    private TextField axex;

    @FXML
    private Button launch;
    
    @FXML
    private Button back;

    @FXML
    private TextField axey;

    @FXML
    private TextField boatx;

    @FXML
    private TextField boaty;

    @FXML
    private Button boat;

    @FXML
    private Button axe;
    
    

}
