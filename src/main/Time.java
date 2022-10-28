package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import main.GamePanel;
import javax.swing.*;
import javax.swing.Timer;


public class Time {
    Timer timer;
    public boolean timeOn = false;
    public int second = -1;
    public void simpleTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeOn == true) {
                    second++;
                }
            }
        });
    }
    public void runTime() {
            simpleTimer();
            timer.start();
    }
    public void restartTime() {
        second = 0;
        timer.start();
    }
    public void stopTime() {
        timer.stop();
    }
}