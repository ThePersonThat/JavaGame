package com.alex.spriteanimation.core.graphics.map;

import com.alex.spriteanimation.core.graphics.sprites.UtilSprite;

import java.awt.image.BufferedImage;

public class TileSet {
    private BufferedImage image;
    private final int tileCount;
    private final int columns;

    public TileSet(String imageName, int tileCount, int columns) {
        this.tileCount = tileCount;
        this.columns = columns;

        image = UtilSprite.loadSprite("src/main/resources/" + imageName);
    }

    public boolean inThisSet(int positionTile) {
        return positionTile < tileCount;
    }

    public int getTileCount() {
        return tileCount;
    }

    public int getColumns() {
        return columns;
    }

    public BufferedImage getImage() {
        return image;
    }
}
