package com.alex.spriteanimation.core.util;

import java.awt.event.KeyEvent;
import java.util.EventListener;

public class HandleEvent {
    private KeyEvent event;
    private boolean pressed;

    public HandleEvent(KeyEvent event, boolean pressed) {
        this.event = event;
        this.pressed = pressed;
    }

    public int getKey() {
        return event.getKeyCode();
    }

    public boolean isPressed() {
        return pressed;
    }
}
