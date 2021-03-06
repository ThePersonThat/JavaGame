package com.alex.spriteanimation.core.graphics.sprites;

import java.awt.image.BufferedImage;

public class SpriteFrame {
    private BufferedImage frame;
    private int duration;

    public SpriteFrame(BufferedImage frame, int duration) {
        this.frame = frame;
        this.duration = duration;
    }

    public BufferedImage getFrame() {
        return frame;
    }

    public void setFrame(BufferedImage frame) {
        this.frame = frame;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
