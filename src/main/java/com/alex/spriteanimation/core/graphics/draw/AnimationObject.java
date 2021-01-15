package com.alex.spriteanimation.core.graphics.draw;

import java.awt.image.BufferedImage;

public class AnimationObject extends DrawnObject {

    protected Animation currentAnimation;


    protected AnimationObject(int x, int y, int width, int height, Animation currentAnimation) {
        super(x, y, width, height);
        this.currentAnimation = currentAnimation;
    }

    protected void changeAnimation(Animation animation) {
        currentAnimation.stop();
        currentAnimation = animation;
        currentAnimation.start();
    }

    protected void stopCurrentAnimation() {
        currentAnimation.stop();
        currentAnimation.reset();
    }


    public BufferedImage getSprite() {
        return currentAnimation.getSprite();
    }
}
