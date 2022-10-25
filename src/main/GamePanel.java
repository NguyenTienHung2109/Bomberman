package main;

import Entity.Player;
<<<<<<< Updated upstream
=======
import Entity.Kondoria;
import Entity.Balloom;
import Entity.Oneal;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
    TileManager tileM = new TileManager(this);
    Thread gameThread;
    KeyHolder keyR = new KeyHolder();
=======
    public TileManager tileM = new TileManager(this);
    public Entity balloom[] = new Balloom[10];
    public Entity kondoria[] = new Kondoria[10];
    public Entity oneal[] = new Oneal[10];
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int menuState = 0;
>>>>>>> Stashed changes

    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyR);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight ));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyR);
        this.setFocusable(true);
    }
<<<<<<< Updated upstream
=======
    public void setupGame() {
        aSetter.setBalloom();
<<<<<<< Updated upstream
=======
        for (int i = 0; i < 20; i++) {
            aSetter.setBomb();
        }

        gameState = menuState;
        try {
            playMusic();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
>>>>>>> Stashed changes
    }

>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        player.update();
=======
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
            for (int i = 0; i < kondoria.length; i++) {
                if (kondoria[i] != null) {
                    kondoria[i].update();
                }
            }
            for (int i = 0; i < oneal.length; i++) {
                if (oneal[i] != null) {
                    oneal[i].update();
                }
            }


        }
        if (gameState == pauseState) {
        }
>>>>>>> Stashed changes
    }
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g2 = (Graphics2D) gr;
        if(player.realX < screenWidth/2 - tileSize) {
            tileM.draw(g2);
<<<<<<< Updated upstream

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
=======
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
            for (int i = 0; i < kondoria.length; i++) {
                if (kondoria[i] != null) {
                    kondoria[i].draw(g2);
                }
            }
            for (int i = 0; i < oneal.length; i++) {
                if (oneal[i] != null) {
                    oneal[i].draw(g2);
                }
            }
            if(!player.message) {
                menu.getScore(g2);
            } else {
                if(currentLevel == 1) {
                    player.score = scoreLevel1;
                } else if(currentLevel == 2) {
                    player.score = scoreLevel2;
                } else if(currentLevel == 3) {
                    player.score = scoreLevel3;
                }
            }
            if(message == true)
            {
                menu.drawLevel(g2);
                counter++;
                if (counter > 100) {
                    counter = 0;
                    message = false;
                    gameState = playState;
                }
>>>>>>> Stashed changes
            }
        }
        g2.dispose();
    }
}
