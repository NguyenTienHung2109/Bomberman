package map;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[]  tile;
    public static char mapTileChar[][];
    public static char alterMap[][];
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileChar = new char[gp.maxScreenCol][gp.maxScreenRow];
        alterMap = new char[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap();
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/levels/level1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();
                while(col < gp.maxScreenCol) {
                    mapTileChar[col][row] = line.charAt(col);
                    alterMap[col][row] = line.charAt(col);
                    col++;
                }
                if(col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }

            }
            br.close();

        }catch (Exception e) {

        }
    }
    public void setMaxTileChar(int maxScreenCol, int maxScreenRow, char x) {
        mapTileChar[maxScreenCol][maxScreenRow] = x;
    }
    public void setAlterMap(int maxScreenCol, int maxScreenRow, char x) {
        alterMap[maxScreenCol][maxScreenRow] = x;
    }
    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/map/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/map/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/map/brick.png"));
            tile[2].collision = true;


        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = gp.tileSize;
        while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
            char obj = mapTileChar[col][row];
            if(obj == '#') {
                g2.drawImage(tile[1].image,x,y,gp.tileSize, gp.tileSize, null);
            } else if(obj == ' ') {
                g2.drawImage(tile[0].image,x,y,gp.tileSize, gp.tileSize, null);
            } else if(obj == 'x') {
                g2.drawImage(tile[2].image,x,y,gp.tileSize, gp.tileSize, null);
            } else if(obj == '*') {
                g2.drawImage(tile[2].image,x,y,gp.tileSize, gp.tileSize, null);
            }
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol) {
                col = 0;
                x = 1;
                row++;
                y += gp.tileSize;
            }

        }
    }
}
