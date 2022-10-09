package main;

import Entity.Entity;
import Entity.Bomb;
import Entity.Player;
import map.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable{
    public final int tileSize = 48;
    public final int maxScreenCol = 31;
    public final int maxScreenRow = 14;
    public final int defaultScreenRow = 14;
    public final int defaultScreenCol = 31;

    public final int screenWidth = tileSize * defaultScreenCol;
    public final int screenHeight = tileSize * defaultScreenRow;
    int FPS = 60;
    public TileManager tileM = new TileManager(this);
    Thread gameThread;
    KeyHolder keyR = new KeyHolder();
    public Entity balloom[] = new Entity[10];

    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyR);
    public Bomb bomb = new Bomb(this, keyR);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight ));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyR);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
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
        if(bomb.placed == false)bomb.updateBombPosition((int)((player.worldX + bomb.solidArea.x)/tileSize)*tileSize, (int)((player.worldY + bomb.solidArea.y)/tileSize)*tileSize);
        bomb.update(player);
        player.getPlayerOnBomb(bomb.playerOnBomb);
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
        if(bomb.placed == true && bomb.bombUnExploded == true){bomb.draw(g2);}
        player.draw(g2);
        g2.dispose();
    }
}
