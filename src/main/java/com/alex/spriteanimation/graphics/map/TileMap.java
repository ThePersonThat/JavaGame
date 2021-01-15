package com.alex.spriteanimation.graphics.map;


import com.alex.spriteanimation.graphics.sprites.UtilSprite;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class TileMap {
    private int width;
    private int height;
    private int tileWidth;
    private int tileHeight;
    private int columns;
    private String imageName;
    private BufferedImage image;
    private String mapData;
    private Tile[][] tiles;


    public TileMap(String filename) {
        loadMap(filename);
        int[] data = UtilMap.parseMapData(mapData, height, width);
        tiles = new Tile[width][height];

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                tiles[i][j] = new Tile(data[i + j], columns, i, j, tileWidth, tileHeight, image);
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

            width = Integer.parseInt(element.getAttribute("width"));
            height = Integer.parseInt(element.getAttribute("height"));
            tileHeight = Integer.parseInt(element.getAttribute("tileheight"));
            tileWidth = Integer.parseInt(element.getAttribute("tilewidth"));

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

    public void update(Graphics g, ImageObserver imageObserver) {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                g.drawImage(tiles[i][j].getTile(), tiles[i][j].getX(), tiles[i][j].getY(), tileWidth, tileHeight, imageObserver);
            }
        }
    }
}
