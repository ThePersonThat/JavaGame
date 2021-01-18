package com.alex.spriteanimation.core.graphics.map;


import com.alex.spriteanimation.core.graphics.Draw;
import com.alex.spriteanimation.core.graphics.sprites.UtilSprite;
import com.alex.spriteanimation.core.util.Camera;
import com.alex.spriteanimation.core.util.Tuple;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;


public class TileMap implements Draw {
    private int countTilesWidth;
    private int countTilesHeight;
    private int tileWidth;
    private int tileHeight;
    private int mapHeight;
    private int mapWidth;
    private String mapData;
    private LoadMap loadMap;
    private List<TileSet> sets;
    private final Tile[][] tiles;
    private final HashMap<String, MapObject> objects = new HashMap<>();



    public TileMap(String filename) {
        loadMap = new LoadMap(filename);
        initMap();
        int[] data = UtilMap.parseMapData(mapData, countTilesWidth, countTilesHeight);
        tiles = new Tile[countTilesWidth][countTilesHeight];

        for(int i = 0, k = 0; i < countTilesHeight; i++) {
            for(int j = 0; j < countTilesWidth; j++) {
                Tuple tuple = UtilMap.chooseSet(data[k], sets);
                TileSet set = sets.get(tuple.getValue1());
                tiles[i][j] = new Tile(tuple.getValue2(), set.getColumns(), j, i, tileWidth, tileHeight, set.getImage());
                k++;
            }
        }

        sets.clear();
    }

    private void initMap() {
            String[] setting = loadMap.getFieldAttribute("map", "width", "height", "tileheight", "tilewidth");

            countTilesWidth = Integer.parseInt(setting[0]); // width
            countTilesHeight = Integer.parseInt(setting[1]); // height
            tileHeight = Integer.parseInt(setting[2]); // tileHeight
            tileWidth = Integer.parseInt(setting[3]); // tileWidth

            mapHeight = countTilesHeight * tileHeight;
            mapWidth = countTilesWidth * tileWidth;

            mapData = loadMap.getData("data");

            loadSets();
            loadObjects();
    }

    private void loadSets() {
        sets = loadMap.loadTileSets();
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
