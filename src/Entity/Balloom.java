<<<<<<< Updated upstream
package Entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Balloom extends Entity{

    public Balloom(GamePanel gp) {
        super(gp);
        direction = "left";
        speed = 2;
        getBallommImage();
    }
    public void getBallommImage(){
            try {
                left = ImageIO.read(getClass().getResourceAsStream("/balloom/balloom_left1.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/balloom/balloom_left2.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/balloom/balloom_left3.png"));
                right = ImageIO.read(getClass().getResourceAsStream("/balloom/balloom_right1.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/balloom/balloom_right2.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/balloom/balloom_right3.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    public void draw (Graphics2D g2) {
        int screenX = 100;
        int screenY = 100;
        //int screenX = worldX - gp.player.worldX + gp.player.screenX;
        //int screenY = worldY - gp.player.worldY + gp.player.screenY;
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = left;
                } else if (spriteNum == 2) {
                    image = left;
                } else if (spriteNum == 3) {
                    image = left;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = right;
                } else if (spriteNum == 2) {
                    image = right;
                } else if (spriteNum == 3) {
                    image = right;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left;
                } else if (spriteNum == 2) {
                    image = left1;
                } else if (spriteNum == 3) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right;
                } else if (spriteNum == 2) {
                    image = right1;
                } else if (spriteNum == 3) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
    public void setAction() {
        actionLockCounter++;
        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
  }
=======
package Entity;public class Balloom {
>>>>>>> Stashed changes
}
