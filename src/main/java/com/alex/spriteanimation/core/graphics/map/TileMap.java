package com.alex.spriteanimation.core.graphics.map;


import com.alex.spriteanimation.core.graphics.Draw;
import com.alex.spriteanimation.core.graphics.sprites.UtilSprite;
import com.alex.spriteanimation.core.util.Camera;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;


public class TileMap implements Draw {
    private int countTilesWidth;
    private int countTilesHeight;
    private int tileWidth;
    private int tileHeight;
    private int mapHeight;
    private int mapWidth;
    private int columns;
    private String imageName;
    private BufferedImage image;
    private String mapData;
    private LoadMap loadMap;
    private final Tile[][] tiles;
    private final HashMap<String, MapObject> objects = new HashMap<>();



    public TileMap(String filename) {
        loadMap = new LoadMap(filename);
        initMap();
        int[] data = UtilMap.parseMapData(mapData, countTilesWidth, countTilesHeight);
        tiles = new Tile[countTilesWidth][countTilesHeight];

        for(int i = 0, k = 0; i < countTilesHeight; i++) {
            for(int j = 0; j < countTilesWidth; j++) {
                tiles[i][j] = new Tile(data[k], columns, j, i, tileWidth, tileHeight, image);
                k++;
            }
        }
    }

    private void initMap() {
            String[] setting = loadMap.getFieldAttribute("map", "width", "height", "tileheight", "tilewidth");

            countTilesWidth = Integer.parseInt(setting[0]); // width
            countTilesHeight = Integer.parseInt(setting[1]); // height
            tileHeight = Integer.parseInt(setting[2]); // tileHeight
            tileWidth = Integer.parseInt(setting[3]); // tileWidth

            mapHeight = countTilesHeight * tileHeight;
            mapWidth = countTilesWidth * tileWidth;

            setting = loadMap.getFieldAttribute("tileset", "columns");
            columns = Integer.parseInt(setting[0]); // columns

            setting = loadMap.getFieldAttribute("image", "source");
            imageName = setting[0];
            image = UtilSprite.loadSprite("src/main/resources/" + imageName);

            mapData = loadMap.getData("data");

            loadObjects();
    }

    private void loadObjects() {
        MapObject object = loadMap.getObject("object");
        objects.put("Player", object);
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    @Override
    public void render(Graphics2D g, Camera camera) {
        for(int i = camera.startY(); i < camera.endY(); i++) {
            for(int j = camera.startX(); j < camera.endX(); j++) {
                g.drawImage(tiles[i][j].getTile(), camera.getX() + tiles[i][j].getX(), camera.getY() + tiles[i][j].getY(), tileWidth, tileHeight, null);
            }
        }
    }

    @Override
    public void update() {

    }

    public HashMap<String, MapObject> getObjects() {
        return objects;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getCountTilesHeight() {
        return countTilesHeight;
    }

    public int getCountTilesWidth() {
        return countTilesWidth;
    }
}
