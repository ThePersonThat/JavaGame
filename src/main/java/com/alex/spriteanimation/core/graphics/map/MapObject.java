package com.alex.spriteanimation.core.graphics.map;

public class MapObject {
    private int x;
    private int y;
    private int width;
    private int height;

    MapObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
