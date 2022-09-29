package main;

import Entity.Entity;
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
    public final int defaultScreenCol = 15;

    public AssertsSetter aSetter = new AssertsSetter(this);
    public final int screenWidth = tileSize * defaultScreenCol;
    public final int screenHeight = tileSize * defaultScreenRow;
    int FPS = 60;
    TileManager tileM = new TileManager(this);
    Thread gameThread;
    KeyHolder keyR = new KeyHolder();
    public Entity balloom[] = new Entity[10];

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
        if(player.realX < screenWidth/2 - tileSize) {
            tileM.draw(g2);

            //System.out.println(player.realX + " " + (maxScreenCol * tileSize - screenWidth/2 - tileSize));
        } else if(player.realX >= player.endMapX) {
            tileM.drawEnd(g2);
        } else {
            tileM.drawMidMap(g2);
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
