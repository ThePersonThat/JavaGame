package com.alex.spaceattack.core.sprite;

public class Alien extends Sprite implements isMove {
    private static final int INITIAL_Y = -400;
    private static final int SPEED_ALIEN = 2;

    public Alien(int x, int y) {
        super(x, y);

        initAlien();
    }

    private void initAlien() {
        loadImage(PATH_TO_IMAGES + "alien.png");

        setDimension(30, 30);
    }

    @Override
    public void move() {
        if(y > 400)
            y = INITIAL_Y;

        y += SPEED_ALIEN;
    }
}
