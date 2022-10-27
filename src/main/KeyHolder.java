package main;
import javax.security.auth.kerberos.KeyTab;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyEvent;
import  java.awt.event.KeyListener;
import java.io.IOException;

public class KeyHolder implements KeyListener {

    GamePanel gp;
    public static boolean upPressed;
    public static boolean downPressed;
    public static boolean leftPressed;
    public static boolean rightPressed;
    public static boolean bombPlaced;
    public static boolean unExploded = true;
    public static boolean bombPresent = false;
    public KeyHolder(GamePanel gp) {
        this.gp = gp;
    }
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //menu
        if(gp.gameState == gp.menuState) {
            if(code == KeyEvent.VK_W) {
                gp.menu.commandNumber--;
                if(gp.menu.commandNumber<0) {
                    gp.menu.commandNumber = 1;
                }
            }
            if(code == KeyEvent.VK_S) {
                gp.menu.commandNumber++;
                if(gp.menu.commandNumber>2) {
                    gp.menu.commandNumber = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER) {
                if(gp.menu.commandNumber == 0) {
                    gp.gameState = gp.playState;
                    gp.tileM.loadMap(gp.currentLevel);
                    gp.aSetter.setBalloom();
                    try {
                        gp.playMusic(GamePanel.STAGE_START);
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if(gp.menu.commandNumber ==1) {
                    System.exit(0);
                }
            }
        }
        //win
        if (gp.isWin) {
            if(code == KeyEvent.VK_W) {
                gp.menu.winCommandNumber--;
                if(gp.menu.winCommandNumber<0) {
                    gp.menu.winCommandNumber = 1;
                }
            }
            if(code == KeyEvent.VK_S) {
                gp.menu.winCommandNumber++;
                if(gp.menu.winCommandNumber>2) {
                    gp.menu.winCommandNumber = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER) {
                if(gp.menu.winCommandNumber == 0) {
                    gp.gameState = gp.menuState;
                    gp.currentLevel = 1;
                    gp.player.worldX = gp.tileSize;
                    gp.player.worldY = gp.tileSize*2;
                    gp.isWin = false;
                }
                if(gp.menu.winCommandNumber == 1) {
                    System.exit(0);
                }
            }
        }
        //pause
        if(gp.gameState == gp.pauseState) {
            if(code == KeyEvent.VK_W) {
                gp.menu.pcommandNumber--;
                if(gp.menu.pcommandNumber<0) {
                    gp.menu.pcommandNumber = 1;
                }
            }
            if(code == KeyEvent.VK_S) {
                gp.menu.pcommandNumber++;
                if(gp.menu.pcommandNumber>2) {
                    gp.menu.pcommandNumber = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER) {
                if(gp.menu.pcommandNumber == 0) {
                    gp.gameState = gp.playState;
                }
                if(gp.menu.pcommandNumber ==1) {
                    System.exit(0);
                }
            }
        }
        if(code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_B || code == KeyEvent.VK_SPACE) {
            if(bombPresent == false) {
                bombPlaced = true;
                unExploded = true;
                bombPresent = true;
            }
        }
        if(code == KeyEvent.VK_P) {
            if(gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            }
            /*else if(gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }*/
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if(code == KeyEvent.VK_B || code == KeyEvent.VK_SPACE) {
            bombPlaced = false;
            bombPresent = false;
        }
    }

}
