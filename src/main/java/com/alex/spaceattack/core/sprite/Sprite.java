package com.alex.spaceattack.core.sprite;

import javax.swing.*;
import java.awt.*;

public class Sprite {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;

    protected static final String PATH_TO_IMAGES = "src/main/resources/images/";

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
        visible = true;
    }

    protected void getImageDimension() {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    protected void loadImage(String filename) {
        ImageIcon icon = new ImageIcon(filename);
        image = icon.getImage();
    }

    protected void setDimension(int h, int w) {
        width = w;
        height = h;
    }

    protected void setDimension() {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
