package com.alex.spaceattack.core.sprite;

import com.alex.spaceattack.graphics.Window;

public class Missile extends Sprite implements isMove {
    private final int MISSILE_SPEED = 4;

    public Missile(int x, int y) {
        super(x, y);

        initMissile();
    }

    private void initMissile() {
        loadImage(PATH_TO_IMAGES + "missile.png");

        setDimension(30, 30);
    }

    @Override
    public void move() {
        y -= MISSILE_SPEED;

        if(x > Window.WINDOW_HEIGHT)
            visible = false;
    }
}
