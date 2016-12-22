package com.neet.DiamondHunter.TileMapEditor;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;


public class TMEController implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ////////////////////////////////////////////////////////////////////////////////////////////////////

        Alert saved = new Alert(Alert.AlertType.INFORMATION);
        saved.setTitle("Axe and Boat");
        saved.setHeaderText("Changes has been saved.");

        GraphicsContext g = canvas.getGraphicsContext2D();
        loadTiles("/Tilesets/testtileset.gif");
        loadItems("/Sprites/items.gif");
        loadMap("/Maps/testmap.map");

        // Draw Initial Map and Item Position
        draw(g);
        g.drawImage(
                itemss[0],
                save_boatY,save_boatX
        );
        g.drawImage(
                itemss[1],
                save_axeY,save_axeX
        );
        // Set TextField to default/initial value
        axey.setText(Integer.toString(save_axeX/16));
        axex.setText(Integer.toString(save_axeY/16));
        boaty.setText(Integer.toString(save_boatX/16));
        boatx.setText(Integer.toString(save_boatY/16));

        ////////////////////////////////////////////////////////////////////////////////////////////////////
        // Objects on GUI 

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
                GraphicsContext gg = canvas.getGraphicsContext2D();
                if(select==0){
                    draw(gg);
                    if(first_boat){
                        gg.drawImage(
                                itemss[0],
                                save_boatY,save_boatX
                        );
                    }
                    if(!first_boat){
                        gg.drawImage(
                                itemss[0],
                                boatX*16,boatY*16
                        );
                    }
                    axeX=(int)e.getX()/16;
                    axeY=(int)e.getY()/16;
                    axex.setText(Integer.toString(axeX));
                    axey.setText(Integer.toString(axeY));
                    first_axe=false;
                    gg.drawImage(
                            itemss[1],
                            axeX*16,axeY*16
                    );
                }
                else{
                    draw(gg);
                    if(first_axe){
                        gg.drawImage(
                                itemss[1],
                                save_axeY,save_axeX
                        );
                    }
                    if(!first_axe) {
                        gg.drawImage(
                                itemss[1],
                                axeX * 16, axeY * 16
                        );
                    }
                    boatX=(int)e.getX()/16;
                    boatY=(int)e.getY()/16;
                    boatx.setText(Integer.toString(boatX));
                    boaty.setText(Integer.toString(boatY));
                    first_boat=false;
                    gg.drawImage(
                            itemss[0],
                            boatX*16,boatY*16
                    );
                }
            }
        });

        save.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
            	//if(first_boat==false){
               try{
                   if(first_axe==true && first_boat==true){
                       checkInvalidPos(axeY,axeX,boatY,boatX);}
                   if(first_axe==false && first_boat==false){
                       checkInvalidPos(axeX,axeY,boatX,boatY);}
                   if(first_axe==true && first_boat==false){
                       checkInvalidPos(axeY,axeX,boatX,boatY);}
                   if(first_axe==false && first_boat==true){
                       checkInvalidPos(axeX,axeY,boatY,boatX);}

                   if(first_axe == false){
                   save_axeX=axeY*16;
                   save_axeY=axeX*16;
                   }
                   if(first_boat == false){
                   save_boatX=boatY*16;
                   save_boatY=boatX*16;
                   }
                   saved.setContentText("Position of Axe (x,y)  : "+save_axeY/16+" "+save_axeX/16+"\nPosition of Boat (x,y) : "+save_boatY/16+" "+save_boatX/16 +
                		   "\n\n * please note that there is possibility that you might not be able to complete the game due to improper positioning.");
                   saved.showAndWait();
               }catch(MyException e){
                Alerts.display("Alert",e.message);
               }

            }

            private void checkInvalidPos(int axeX, int axeY, int boatX, int boatY) throws MyException {
                // TODO Auto-generated method stub
            	if((axeX*16)==save_axeX && (axeY*16)==save_axeY && (boatX*16)==save_boatX && (boatY*16)==save_boatY){
            		throw new MyException("No changes are made. Please use the back button instead!");
            	}
                if(map[axeY][axeX] == 20 || map[axeY][axeX]==21){
                    throw new MyException("Axe cannot be placed onto a tree");
                }
                if(map[axeY][axeX]==22){
                    throw new MyException("Axe cannot be placed into water");
                }
                if(map[boatY][boatX] == 20 || map[boatY][boatX]==21){
                    throw new MyException("Boat cannot be placed onto a tree");
                }
                if(map[boatY][boatX]==22){
                    throw new MyException("Boat cannot be placed into water");
                }
            }
        });
    }
    
    @FXML
    public void back() throws Exception{
        Scene scene = back.getScene();
        Stage currentscene = (Stage)scene.getWindow();
        currentscene.hide();
    }

    //////////////////////////////////////////////////
    // Methods to extract images from resources
    // also to load tiles and map, and draw images in respective position according to map.map
    public void loadItems(String s) {
        Image setTile = new Image(s);
        itemss = new Image[2];
        for (int col = 0; col < 2; col++) {
            itemss[col] = new WritableImage(
                    setTile.getPixelReader(),
                    col * tileSize,
                    16,

                    tileSize,
                    tileSize);
        }
    }
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

    //////////////////////////////////////////////////

    // Variables Declaration
    private int axeX=26,axeY=37,boatX=12,boatY=4;
    public static int save_axeX=416,save_axeY=592,save_boatX=192,save_boatY=64;
    int select=0;
    boolean first_boat=true,first_axe=true;

    private int[][] map;
    private int tileSize=16;
    private int numRows;
    private int numCols;

    private Image[][] tiles;
    private Image[] itemss;
    public Image image;
    private int numTilesAcross;

    ///////////////////////////////////////////////////////
    @FXML
    public Canvas canvas;

    @FXML
    private TextField axex;

    @FXML
    private Button save;
    
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
