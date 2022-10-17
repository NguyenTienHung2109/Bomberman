package main;

import Entity.Entity;
import Entity.Bomb;
import Entity.Player;
import Entity.Balloom;
import map.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GamePanel extends JPanel implements Runnable {
    public final int tileSize = 48;
    public final int maxScreenCol = 31;
    public final int maxScreenRow = 14;
    public final int defaultScreenRow = 14;
    public final int defaultScreenCol = 31;
    public int bombCount = 0;
    public int bombMax = 1;
    public int bombLength = 1;

    public boolean playerExploded = true;

    public boolean message = true;
    int counter = 0;
    KeyHolder keyR = new KeyHolder(this);
    public AssertsSetter aSetter = new AssertsSetter(this, keyR);
    public Menu menu = new Menu(this);
    public final int screenWidth = tileSize * defaultScreenCol;
    public final int screenHeight = tileSize * defaultScreenRow;

    public List<Bomb> bomb = new ArrayList<Bomb>();
    int FPS = 60;
    public TileManager tileM = new TileManager(this);
    Thread gameThread;
    public Entity balloom[] = new Balloom[10];

    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int menuState = 0;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyR);
    Font fip = null;
    Sound sound = new Sound();

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyR);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setBalloom();
        for (int i = 0; i < 20; i++) {
            aSetter.setBomb();
        }
        gameState = menuState;
        //playMusic(0);
    }

    boolean moreBomb = true;

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            if (keyR.bombPresent && bombCount < bombMax && this.tileM.newBombMap[(player.worldX + bomb.get(bombCount).solidArea.x) / tileSize][(player.worldY + bomb.get(bombCount).solidArea.y) / tileSize - 1] != 'b') {
                bomb.get(bombCount).updateBombPosition((int) ((player.worldX + bomb.get(bombCount).solidArea.x) / tileSize) * tileSize, (int) ((player.worldY + bomb.get(bombCount).solidArea.y) / tileSize) * tileSize);
                this.tileM.setNewBombMap((player.worldX + bomb.get(bombCount).solidArea.x) / tileSize, (player.worldY + bomb.get(bombCount).solidArea.y) / tileSize - 1, 'b');
                bombCount++;
                keyR.bombPresent = false;
            }
            for (int i = 0; i < bombCount; i++) {
                if (bomb.get(i) != null) {
                    bomb.get(i).update(player, balloom);
                }
            }
            player.update();

            for (int i = 0; i < balloom.length; i++) {
                if (balloom[i] != null) {
                    balloom[i].update();
                }
            }
        }
        if (gameState == pauseState) {
        }
    }
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g2 = (Graphics2D) gr;
        if(gameState == menuState) {
            menu.draw(g2);
        }
        else {
            tileM.draw(g2);
            for (int i = 0; i < bombCount; i++) {
                if (bomb.get(i) != null && bomb.get(i).placed == true && bomb.get(i).bombUnExploded == true) {
                    bomb.get(i).draw(g2);
                }
                if (!bomb.get(i).done) {
                    Bomb newB = new Bomb(this, keyR);
                    bomb.set(i, newB);
                    if (i == bombCount - 1) {
                        bombCount = 0;
                    }
                }
            }
            player.draw(g2);
            for (int i = 0; i < balloom.length; i++) {
                if (balloom[i] != null) {
                    balloom[i].draw(g2);
                }
            }
            menu.getScore(g2);
            if(message == true)
            {
                menu.drawLevel(g2);
                counter++;
                if (counter > 100) {
                    counter = 0;
                    message = false;
                }
            }
        }
        g2.dispose();
    }

    /*public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic() {
        sound.stop();
    }
    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }*/

}
