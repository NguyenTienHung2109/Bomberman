package main;

import Entity.Entity;
import Entity.Bomb;
import Entity.Player;
import Entity.Balloom;
import map.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GamePanel extends JPanel implements Runnable{
    public final int tileSize = 48;
    public final int maxScreenCol = 31;
    public final int maxScreenRow = 14;
    public final int defaultScreenRow = 14;
    public final int defaultScreenCol = 31;
    public int bombCount = 0;
    public int bombMax = 3;

    public boolean playerExploded = true;
    KeyHolder keyR = new KeyHolder();
    public AssertsSetter aSetter = new AssertsSetter(this, keyR);
    public final int screenWidth = tileSize * defaultScreenCol;
    public final int screenHeight = tileSize * defaultScreenRow;

    public List<Bomb> bomb = new ArrayList<Bomb>();
    int FPS = 60;
    public TileManager tileM = new TileManager(this);
    Thread gameThread;
    public Balloom balloom[] = new Balloom[10];

    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyR);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight ));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyR);
        this.setFocusable(true);
    }
    public void setupGame() {
        aSetter.setBalloom();
        for(int i = 0; i < 20; i++) {
            aSetter.setBomb();
        }
    }
    boolean moreBomb = true;

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /*public void setListBalloom() {
        balloom[0].
    }*/
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }
    public void update() {
        if(keyR.bombPresent && bombCount < bombMax && this.tileM.newBombMap[(player.worldX + bomb.get(bombCount).solidArea.x)/tileSize][(player.worldY + bomb.get(bombCount).solidArea.y)/tileSize - 1] != 'b') {
            bomb.get(bombCount).updateBombPosition((int)((player.worldX + bomb.get(bombCount).solidArea.x)/tileSize)*tileSize, (int)((player.worldY + bomb.get(bombCount).solidArea.y)/tileSize)*tileSize);
            this.tileM.setNewBombMap((player.worldX + bomb.get(bombCount).solidArea.x)/tileSize,(player.worldY + bomb.get(bombCount).solidArea.y)/tileSize - 1, 'b');
            bombCount++;
            keyR.bombPresent = false;
        }
        for(int i = 0; i < bombCount; i++) {
            if(bomb.get(i) != null) {
                bomb.get(i).update(player);
                if(!bomb.get(i).bombUnExploded) {
                    for(int j = 0; j < 6; j++) {
                        if(balloom[j] != null) {
                            bomb.get(i).update(balloom[j]);
                            if(balloom[j].playerOnBomb) {
                                System.out.println("alo");
                            }
                        }
                    }
                }
            }
        }
         player.update();

        for(int i=0; i<balloom.length; i++) {
            if(balloom[i]!=null) {
                balloom[i].update();
            }
        }
    }
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g2 = (Graphics2D) gr;
        tileM.draw(g2);
        for(int i = 0; i < bombCount; i++) {
            if(bomb.get(i) != null && bomb.get(i).placed == true && bomb.get(i).bombUnExploded == true){
                bomb.get(i).draw(g2);
            }
            if(!bomb.get(i).done) {
                Bomb newB = new Bomb(this, keyR);
                bomb.set(i, newB);
                if(i == bombCount - 1) {
                    bombCount = 0;
                }
            }
        }
        player.draw(g2);
        for(int i=0; i<balloom.length; i++) {
            if(balloom[i] != null) {
                balloom[i].draw(g2);
            }
        }
        g2.dispose();
    }
}
