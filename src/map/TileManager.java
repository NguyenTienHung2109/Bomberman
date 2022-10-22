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
    public char newBombMap[][];
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileChar = new char[gp.maxScreenCol][gp.maxScreenRow];
        alterMap = new char[gp.maxScreenCol][gp.maxScreenRow];
        newBombMap = new char[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap(gp.currentLevel);
    }

    public void loadMap(int level) {
        try {
            String curLevel = " ";
            if(level == 1) {
                curLevel = "/levels/level1.txt";
            } else if(level == 2) {
                curLevel = "/levels/level2.txt";
            } else if(level == 3) {
                curLevel = "/levels/level3.txt";
            }
            InputStream is = getClass().getResourceAsStream(curLevel);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();
                while(col < gp.maxScreenCol) {
                    mapTileChar[col][row] = line.charAt(col);
                    alterMap[col][row] = line.charAt(col);
                    newBombMap[col][row] = line.charAt(col);
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
    public void setNewBombMap(int maxScreenCol, int maxScreenRow, char x) {
        newBombMap[maxScreenCol][maxScreenRow] = x;
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

            tile[3] = new Tile();
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/map/powerup_flames.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/map/portal.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/map/powerup_bombs.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/map/powerup_speed.png"));
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
            } else if(obj == ' ' || obj == 'b' || obj == '1' || obj == '2' || obj == 'p' || obj == '3') {
                g2.drawImage(tile[0].image,x,y,gp.tileSize, gp.tileSize, null);
            } else if(obj == 'x' || obj == 'f' || obj == 'n' || obj == 's') {
                g2.drawImage(tile[2].image,x,y,gp.tileSize, gp.tileSize, null);
            } else if(obj == '*') {
                g2.drawImage(tile[2].image,x,y,gp.tileSize, gp.tileSize, null);
            } else if(obj == 'l') {
                g2.drawImage(tile[4].image,x,y,gp.tileSize, gp.tileSize, null);
            } else if(obj == 'X') {
                g2.drawImage(tile[5].image,x,y,gp.tileSize, gp.tileSize, null);
            } else if(obj == 'N') {
                g2.drawImage(tile[6].image,x,y,gp.tileSize, gp.tileSize, null);
            } else if(obj == 'S') {
                g2.drawImage(tile[7].image, x, y, gp.tileSize, gp.tileSize, null);
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
