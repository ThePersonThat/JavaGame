package com.alex.spriteanimation.core.states;

import com.alex.spriteanimation.core.util.HandleEvent;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class GameState {
   //protected StateManager manager;

    public abstract void update();
    public abstract void input(HandleEvent event);
    public abstract void render(Graphics2D g);
}
