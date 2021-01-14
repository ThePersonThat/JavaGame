package com.alex.spaceattack.core.sprite;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import com.alex.spaceattack.core.sound.SoundEffect;
import com.alex.spaceattack.graphics.Window;

public class Hero extends Sprite implements isMove {
    private int dx;
    private int dy;
    private List<Missile> missiles;
    private SoundEffect sound;

    public Hero (int x, int y) {
        super(x, y);

        initHero();
    }

    private void initHero() {
        missiles = new ArrayList<>();

        loadImage(PATH_TO_IMAGES + "ship.png");
        setDimension(50, 50);

        loadSound();
    }

    private void loadSound() {
        sound = new SoundEffect();
        sound.setSoundFile(SoundEffect.PATH_TO_SOUNDS + "laser.wav");
    }

    @Override
    public void move() {
        x += dx;
        y += dy;

        if(x > Window.WINDOW_WIDTH + width / 3)
            x = -width;

        if(y > Window.WINDOW_HEIGHT + height / 3)
            y = -height;

        if(x < -width)
            x = Window.WINDOW_WIDTH;

        if(y < -height)
            y = Window.WINDOW_HEIGHT;
    }

    public List<Missile> getMissiles() {
        return missiles;
    }

    public void fire() {
        missiles.add(new Missile(x + (width / 5), y - 5));
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_LEFT:
                dx = -2;
                break;
            case KeyEvent.VK_RIGHT:
                dx = 2;
                break;
            case KeyEvent.VK_UP:
                dy = -2;
                break;
            case KeyEvent.VK_DOWN:
                dy = 2;
                break;
            case KeyEvent.VK_SPACE:
                fire();
                sound.play();
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}
