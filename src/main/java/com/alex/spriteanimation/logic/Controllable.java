package com.alex.spriteanimation.logic;

import java.awt.event.KeyEvent;

public interface Controllable {
    void keyReleased(KeyEvent event);
    void keyPressed(KeyEvent event);
}
