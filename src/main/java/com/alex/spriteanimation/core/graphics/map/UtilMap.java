package com.alex.spriteanimation.core.graphics.map;

import com.alex.spriteanimation.core.util.Tuple;

import java.util.List;
import java.util.Set;

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

    static Tuple chooseSet(int positionTile, List<TileSet> sets) {
        for(int i = 0, prevCountTiles = 0; i < sets.size(); i++) {
            TileSet set = sets.get(i);

            if(set.inThisSet(Math.abs(prevCountTiles - positionTile))) {
                return new Tuple(i, Math.abs(prevCountTiles - positionTile));
            }
            else {
                prevCountTiles = set.getTileCount();
            }
        }

        throw new RuntimeException("Error find tile");
    }
}
