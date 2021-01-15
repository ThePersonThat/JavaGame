package com.alex.spriteanimation.core.graphics.draw;

public class DrawnObject {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    protected DrawnObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected DrawnObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void setDimension(int width, int height) {
        this.width = width;
        this.height = height;
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
}
