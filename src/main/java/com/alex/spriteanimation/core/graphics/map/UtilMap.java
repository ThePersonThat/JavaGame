package com.alex.spriteanimation.core.graphics.map;

public class UtilMap {

    static int[] parseMapData(String dataMap, int width, int height) {
        int data[] = new int[height * width];
        String[] stringData = dataMap.split(",");
        String temp;

        for(int i = 0; i < height * width; i++) {
                temp = stringData[i].replace("\n", "");
                data[i] = Integer.parseInt(temp);
        }

        return data;
    }
}
