package com.alex.spriteanimation.core.entities;

import com.alex.spriteanimation.core.graphics.Draw;
import com.alex.spriteanimation.core.graphics.draw.Animation;
import com.alex.spriteanimation.core.graphics.draw.AnimationObject;
import com.alex.spriteanimation.core.graphics.sprites.UtilSprite;
import com.alex.spriteanimation.core.util.Camera;
import com.alex.spriteanimation.core.util.HandleEvent;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends AnimationObject implements Draw {

    private static final BufferedImage spriteSheet = UtilSprite.loadSprite(UtilSprite.PATH_TO_SPRITES + "alex.png");
    private static final int TILE_WIDTH = spriteSheet.getWidth() / 5;
    private static final int TILE_HEIGHT = spriteSheet.getHeight() / 8;
    private static final int MOVING_DELAY = 7;
    private static final int MOVING_DELAY_SPEED = 2;


    private static final BufferedImage[] movingLeft = {
            UtilSprite.getSprite(spriteSheet, 0, 2, TILE_WIDTH, TILE_HEIGHT),
            UtilSprite.getSprite(spriteSheet, 1, 2, TILE_WIDTH, TILE_HEIGHT),
            UtilSprite.getSprite(spriteSheet, 2, 2, TILE_WIDTH, TILE_HEIGHT),
            UtilSprite.getSprite(spriteSheet, 3, 2, TILE_WIDTH, TILE_HEIGHT),
            UtilSprite.getSprite(spriteSheet, 4, 2, TILE_WIDTH, TILE_HEIGHT),
    };

    private static final BufferedImage[] movingDown = {
            UtilSprite.getSprite(spriteSheet, 0, 4, TILE_WIDTH, TILE_HEIGHT),
            UtilSprite.getSprite(spriteSheet, 1, 4, TILE_WIDTH, TILE_HEIGHT),
            UtilSprite.getSprite(spriteSheet, 2, 4, TILE_WIDTH, TILE_HEIGHT),
            UtilSprite.getSprite(spriteSheet, 3, 4, TILE_WIDTH, TILE_HEIGHT),
            UtilSprite.getSprite(spriteSheet, 4, 4, TILE_WIDTH, TILE_HEIGHT),
    };

    private static final BufferedImage[] movingUp = {
            UtilSprite.getSprite(spriteSheet, 0, 0, TILE_WIDTH, TILE_HEIGHT),
            UtilSprite.getSprite(spriteSheet, 1, 0, TILE_WIDTH, TILE_HEIGHT),
            UtilSprite.getSprite(spriteSheet, 2, 0, TILE_WIDTH, TILE_HEIGHT),
            UtilSprite.getSprite(spriteSheet, 3, 0, TILE_WIDTH, TILE_HEIGHT),
            UtilSprite.getSprite(spriteSheet, 4, 0, TILE_WIDTH, TILE_HEIGHT),
    };

    private static final BufferedImage[] movingRight = {
            UtilSprite.getSprite(spriteSheet, 0, 6, TILE_WIDTH, TILE_HEIGHT),
            UtilSprite.getSprite(spriteSheet, 1, 6, TILE_WIDTH, TILE_HEIGHT),
            UtilSprite.getSprite(spriteSheet, 2, 6, TILE_WIDTH, TILE_HEIGHT),
            UtilSprite.getSprite(spriteSheet, 3, 6, TILE_WIDTH, TILE_HEIGHT),
            UtilSprite.getSprite(spriteSheet, 4, 6, TILE_WIDTH, TILE_HEIGHT),
    };

    private static Animation animationMovingLeft = new Animation(movingLeft, MOVING_DELAY, 1);
    private static Animation animationMovingDown = new Animation(movingDown, MOVING_DELAY, 1);
    private static Animation animationMovingUp = new Animation(movingUp, MOVING_DELAY, 1);
    private static Animation animationMovingRight = new Animation(movingRight, MOVING_DELAY, 1);
    private int dx;
    private int dy;

    public Player(int x, int y) {
        super(x, y, TILE_WIDTH, TILE_HEIGHT, animationMovingDown);
    }


    public void keyReleased(int key) {
        switch (key) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                dx = 0;
                stopCurrentAnimation();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_UP:
                dy = 0;
                stopCurrentAnimation();
                break;
        }
    }

    public void keyPressed(int key) {
        switch (key) {
            case KeyEvent.VK_LEFT:
                changeAnimation(animationMovingLeft);
                dx = -MOVING_DELAY_SPEED;
                break;
            case KeyEvent.VK_RIGHT:
                changeAnimation(animationMovingRight);
                dx = MOVING_DELAY_SPEED;
                break;
            case KeyEvent.VK_UP:
                changeAnimation(animationMovingUp);
                dy = -MOVING_DELAY_SPEED;
                break;
            case KeyEvent.VK_DOWN:
                changeAnimation(animationMovingDown);
                dy = MOVING_DELAY_SPEED;
                break;
        }
    }

    @Override
    public void render(Graphics2D g, Camera camera) {
        g.drawImage(currentAnimation.getSprite(), camera.getX() + x, camera.getY() + y, width, height, null);
    }

    @Override
    public void update() {
        x += dx;
        y += dy;

        currentAnimation.update();
    }

    public void input(HandleEvent event) {

        int key = event.getKey();

        if(event.isPressed()) {
            keyPressed(key);
        }
        else {
            keyReleased(key);
        }
    }
}
