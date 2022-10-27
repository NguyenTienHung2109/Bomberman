package main;

import java.util.*;
import main.GamePanel;

public class Time {

    public GamePanel gp;
    static int interval;
    static Timer timer;
    static String GAME_TIME = "300";
    int timeCount = 0;

    public void runTime() {
        /*Scanner sc = new Scanner(System.in);
        System.out.print("Input seconds => : ");
        String secs = sc.nextLine();*/
        int delay = 1000;
        int period = 1000;
        timer = new Timer();

        interval = Integer.parseInt(GAME_TIME);
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                //if (gp.gameState == gp.playState) {
                timeCount++;
                //}
            }
        }, delay, period);
    }

    private static final int setInterval() {
        if (interval == 0)
            timer.cancel();
        return --interval;
    }
}
