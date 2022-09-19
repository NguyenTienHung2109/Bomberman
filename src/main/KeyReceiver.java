package main;

import java.awt.event.KeyEvent;

public interface KeyReceiver {
    void keyTyped(KeyEvent e);

    void KeyPressed(KeyEvent e);

    void KeyReleased(KeyEvent e);
}
