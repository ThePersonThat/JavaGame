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
}
