package com.alex.spriteanimation.core.graphics.map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadMap {
    private Document document;

    public LoadMap(String filename) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(filename));
        } catch (ParserConfigurationException e) {
            System.out.println("Error ParserConfiguration");
        } catch (SAXException e) {
            System.out.println("SAX error");
        } catch (IOException exception) {
            System.out.println("Error open file: " + exception.getMessage());
        }
    }

    public String[] getFieldAttribute(String tagName, String ... attrName) {
        NodeList list = document.getElementsByTagName(tagName);
        Element element = (Element) list.item(0);
        String[] data = new String[attrName.length];

        for(int i = 0; i < attrName.length; i++) {
            data[i] = element.getAttribute(attrName[i]);
        }

        return data;
    }

    public String getData(String tagName) {
        NodeList list = document.getElementsByTagName(tagName);
        Element element = (Element) list.item(0);

        return element.getTextContent();
    }

    public MapObject getObject(String tagName) {
        NodeList list = document.getElementsByTagName(tagName);
        Element element = (Element) list.item(0);


        int x = (int) Math.round(Double.parseDouble(element.getAttribute("x")));
        int y = (int) Math.round(Double.parseDouble(element.getAttribute("y")));
        int width = (int) Math.round(Double.parseDouble(element.getAttribute("width")));
        int height = (int) Math.round(Double.parseDouble(element.getAttribute("height")));

        return new MapObject(x, y, width, height);
    }

    public List<TileSet> loadTileSets() {
        NodeList list = document.getElementsByTagName("tileset");
        ArrayList<TileSet> sets = new ArrayList<>();

        for(int i = 0; i < list.getLength(); i++) {
            Element element = (Element) list.item(i);
            sets.add(loadTileSet(element));
        }

        return sets;
    }

    private TileSet loadTileSet(Element element) {
        int tilesCount = Integer.parseInt(element.getAttribute("tilecount"));
        int columns = Integer.parseInt(element.getAttribute("columns"));

        NodeList list = element.getElementsByTagName("image");
        String imageName = ((Element) list.item(0)).getAttribute("source");

        return new TileSet(imageName, tilesCount, columns);
    }
}
