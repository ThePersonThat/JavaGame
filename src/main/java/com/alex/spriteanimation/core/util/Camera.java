package com.alex.spriteanimation.core.util;

import com.alex.spriteanimation.Window;
import com.alex.spriteanimation.core.entities.Player;
import com.alex.spriteanimation.core.graphics.map.TileMap;

import java.awt.*;

public class Camera {
    private int x = 0;
    private int y = 0;
    private final int width = 300;
    private final int height = 300;
    private final int SPEED_CAMERA = 1;
    private Player player;
    private TileMap map;

    public Camera(Player player, TileMap map) {
        this.player = player;
        this.map = map;
    }

    public void update() {
        int targetX = -player.getX() + (width + player.getWidth()) / 2;
        int targetY = -player.getY() + (height + player.getHeight()) / 2;

        targetX = Math.min(map.getMapWidth() - width, Math.min(0, targetX));
        targetY = Math.min(map.getMapHeight() - height, Math.min(0, targetY));

        x += (targetX - x) * SPEED_CAMERA;
        y += (targetY - y) * SPEED_CAMERA;
    }

    public int startX() {
        int offset = (player.getX() - Window.WINDOW_WIDTH) / map.getTileWidth();

        return Math.max(offset, 0);

    }

    public int endX() {
        int offset = (player.getX() + Window.WINDOW_WIDTH) / map.getTileWidth();

        return Math.min(offset, map.getCountTilesWidth());
    }

    public int startY() {
        int offset = (player.getY() - Window.WINDOW_HEIGHT) / map.getTileHeight();

        return Math.max(offset, 0);
    }

    public int endY() {
        int offset = (player.getY() + Window.WINDOW_HEIGHT) / map.getTileHeight();

        return Math.min(offset, map.getCountTilesHeight());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
