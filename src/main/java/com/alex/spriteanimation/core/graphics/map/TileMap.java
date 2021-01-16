package com.alex.spriteanimation.core.graphics.map;


import com.alex.spriteanimation.Window;
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
    private Tile[][] tiles;


    public TileMap(String filename) {
        loadMap(filename);
        int[] data = UtilMap.parseMapData(mapData, countTilesWidth, countTilesHeight);
        tiles = new Tile[countTilesWidth][countTilesHeight];

        for(int i = 0, k = 0; i < countTilesHeight; i++) {
            for(int j = 0; j < countTilesWidth; j++) {
                tiles[i][j] = new Tile(data[k], columns, j, i, tileWidth, tileHeight, image);
                k++;
            }
        }
    }

    private void loadMap(String filename) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filename));

            NodeList list = document.getElementsByTagName("map");
            Element element = (Element) list.item(0);
            countTilesWidth = Integer.parseInt(element.getAttribute("width"));
            countTilesHeight = Integer.parseInt(element.getAttribute("height"));
            tileHeight = Integer.parseInt(element.getAttribute("tileheight"));
            tileWidth = Integer.parseInt(element.getAttribute("tilewidth"));

            mapHeight = countTilesHeight * tileHeight;
            mapWidth = countTilesWidth * tileWidth;

            list = document.getElementsByTagName("tileset");
            element = (Element) list.item(0);
            columns = Integer.parseInt(element.getAttribute("columns"));

            list = document.getElementsByTagName("image");
            element = (Element) list.item(0);
            imageName = element.getAttribute("source");
            image = UtilSprite.loadSprite("src/main/resources/" + imageName);

            list = document.getElementsByTagName("data");
            element = (Element) list.item(0);

            mapData = element.getTextContent();
        } catch (ParserConfigurationException e) {
            System.out.println("Error ParserConfiguration");
        } catch (SAXException e) {
            System.out.println("SAX error");
        } catch (IOException exception) {
            System.out.println("Error open file: " + exception.getMessage());
        }
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
