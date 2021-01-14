package com.alex.spaceattack.core.sprite;

import com.alex.spaceattack.graphics.Window;

public class Background extends Sprite {
    private static final int SPEED_BACKGROUND = 5;
    private int count = 1;

    public Background() {
        super(0, -Window.WINDOW_HEIGHT);

        initBackground();
        setDimension();
    }

    private void initBackground() {
        loadImage(PATH_TO_IMAGES + "Background.jpg");
    }

    public void increase() {
        if(y > 0) {
            count = 2;
        }

        if(y > Window.WINDOW_HEIGHT) {
            y = -height + y;
            count = 1;
        }

        y += SPEED_BACKGROUND;
    }

    public int getCount() {
        return count;
    }

    public int getY(int imageNumber) {
        if(imageNumber == 0)
            return y;
        else
            return -height + y;
    }
}
