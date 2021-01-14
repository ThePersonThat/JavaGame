package com.alex.spriteanimation.graphics.draw;


import com.alex.spriteanimation.graphics.sprites.SpriteFrame;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {
    private int frameCount;
    private int frameDelay;
    private int currentFrame;
    private int animationDirection;
    private int totalFrames;
    private int startFrame;

    private boolean stooped;

    private List<SpriteFrame> frames = new ArrayList<>();

    public Animation(BufferedImage[] frames, int frameDelay, int startFrame) {
        this.frameDelay = frameDelay;
        this.stooped = true;

        for(int i = 0; i < frames.length; i++) {
            addFrame(frames[i], frameDelay);
        }

        this.frameCount = 0;
        this.currentFrame = 0;
        this.animationDirection = 1;
        this.totalFrames = this.frames.size();
        this.startFrame = startFrame;
    }

    public void start() {
        if(!stooped)
            return;

        if(frames.size() == 0)
            return;

        stooped = false;
    }

    public void stop() {
        if(frames.size() == 0)
            return;

        stooped = true;
    }

    public void restart() {
        if(frames.size() == 0)
            return;

        stooped = false;
        currentFrame = 0;
    }

    public void reset() {
        stooped = true;
        frameCount = 0;
        currentFrame = 0;
    }

    private void addFrame(BufferedImage frame, int duration) {
        if(duration <= 0) {
            System.out.println("Invalid duration: " + duration);
            throw new RuntimeException("Invalid duration: " + duration);
        }

        frames.add(new SpriteFrame(frame, duration));
        currentFrame = 0;
    }

    public BufferedImage getSprite() {
        return frames.get(currentFrame).getFrame();
    }

    public void update() {
        if(!stooped)
            frameCount++;

        if(frameCount > frameDelay) {
            frameCount = 0;
            currentFrame += animationDirection;

            if(currentFrame > totalFrames - 1) {
                currentFrame = startFrame;
            }
            else if(currentFrame < 0) {
                currentFrame = totalFrames - 1;
            }

        }
    }

}
