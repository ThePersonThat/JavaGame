package com.alex.spriteanimation.graphics.map;

import com.alex.spriteanimation.graphics.draw.DrawnObject;
import com.alex.spriteanimation.graphics.sprites.UtilSprite;

import java.awt.image.BufferedImage;

public class Tile extends DrawnObject {
    private BufferedImage tile;

    public Tile(int position, int countColumns, int positionX, int positionY,
                int tileWidth, int tileHeight, BufferedImage image) {
        super(positionX * tileWidth, positionY * tileHeight, tileWidth, tileHeight);
        int xGrid = position % countColumns;
        int yGrid = position / countColumns;

        if(xGrid != 0)
            xGrid--;

        tile = UtilSprite.getSprite(image, xGrid, yGrid, tileWidth, tileHeight);
    }

    public BufferedImage getTile() {
        return tile;
    }
}
