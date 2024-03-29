package main;

import Entity.Entity;
import Entity.Bomb;
import Entity.Player;
import Entity.Kondoria;
import Entity.Balloom;
import Entity.Oneal;
import map.TileManager;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
    public int scoreLevel1 = 0, scoreLevel2, scoreLevel3;
    public int bombCount = 0;
    public int bombMax = 1;
    public int bombLength = 1;
    public int currentLevel = 2;
    public int demMonsterKilled = 0;
    public boolean message = true;
    public boolean start = false;
    int counter = 0;
    public boolean isWin = false;
    KeyHolder keyR = new KeyHolder(this);
    Time time = new Time();
    public AssertsSetter aSetter = new AssertsSetter(this, keyR);
    public Menu menu = new Menu(this);
    public final int screenWidth = tileSize * defaultScreenCol;
    public final int screenHeight = tileSize * defaultScreenRow;

    public List<Bomb> bomb = new ArrayList<Bomb>();
    int FPS = 60;
    public TileManager tileM = new TileManager(this);
    public Entity balloom[] = new Balloom[10];
    public Entity kondoria[] = new Kondoria[10];
    public Entity oneal[] = new Oneal[10];
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int menuState = 0;
    public Sound[] sounds = new Sound[10];
    public static int MUSIC = 0;
    public static int PLAYER_DIE = 1;
    public static int POWER_UP = 2;
    public static int NEXT_LEVEL = 3;
    public static int PLACE_BOMB = 4;
    public static int ENDING = 5;
    public static int EXPLOSION = 6;
    public static int STAGE_START = 7;
    public boolean restart = false;

    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyR);
    Font fip = null;
    Sound sound = new Sound();
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyR);
        this.setFocusable(true);
    }

    public void setupGame() {
        for (int i = 0; i < 20; i++) {
            aSetter.setBomb();
        }

        gameState = menuState;
        try {
            playMusic(MUSIC);
            loopMusic(MUSIC);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            time.runTime();
    }

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
                try {
                    update();
                } catch (UnsupportedAudioFileException e) {
                    throw new RuntimeException(e);
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                repaint();
                delta--;
            }
        }
    }

    public void update() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (gameState == playState) {
            time.timeOn = true;
        }
     
        if(!isWin) {
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
                menu.drawPauseScreen();
            }
        } else {
            time.stopTime();
        }
    }
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g2 = (Graphics2D) gr;
        if(gameState == menuState || gameState == pauseState) {
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
                menu.getTime(g2);
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
            }
        }
        if (isWin) {
            menu.drawWinScreen();
        }
        g2.dispose();
    }
    public void playMusic(int i) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        for (int j = 0; j < 10; j++) {
            sounds[j] = new Sound();
        }
        sounds[0].setMusic("D:/Bomberman/res/sound/music_game2.wav");
        sounds[1].setMusic("D:/Bomberman/res/sound/player_die.wav");
        sounds[2].setMusic("D:/Bomberman/res/sound/up.wav");
        //sounds[3].setMusic("D:/Bomberman/res/sound/next_level.wav");
        sounds[4].setMusic("D:/Bomberman/res/sound/place_bomb.wav");
        //sounds[5].setMusic("D:/Bomberman/res/sound/ending.wav");
        sounds[6].setMusic("D:/Bomberman/res/sound/explosion-5981.wav");
        sounds[7].setMusic("D:/Bomberman/res/sound/stage_start.wav");
        sounds[i].play();
    }
    public void setSound() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

    }
    public void stopMusic(int i) {
        sounds[i].stop();
    }
    public void loopMusic(int i) {
        sounds[i].loop();
    }
    /*public void playSE() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        sound.setMusic("D:/Bomberman/res/sound/music_game2.wav");
        sound.play();
    }*/
}
