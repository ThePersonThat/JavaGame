package com.alex.spriteanimation.graphics.sprites;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UtilSprite {

    public static final String PATH_TO_SPRITES = "src/main/resources/images/";

    public static BufferedImage loadSprite(String filename) {
        BufferedImage sprite = null;

        try {
            sprite = ImageIO.read(new File(filename));
        } catch (IOException e) {
            System.out.println("Can't open file");
        }

        return sprite;
    }

    public static BufferedImage getSprite(BufferedImage spriteSheet, int xGrid, int yGrid, int tileWidth, int tileHeight) {
        if(spriteSheet == null) {
            throw new RuntimeException("spriteSheet is null");
        }

        return spriteSheet.getSubimage(xGrid * tileWidth, yGrid * tileHeight, tileWidth, tileHeight);
    }

}
