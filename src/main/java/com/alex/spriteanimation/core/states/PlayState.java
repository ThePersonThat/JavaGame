package com.alex.spriteanimation.core.states;

import com.alex.spriteanimation.core.entities.Player;
import com.alex.spriteanimation.core.graphics.map.TileMap;
import com.alex.spriteanimation.core.util.HandleEvent;

import java.awt.*;

public class PlayState extends GameState {
    private Player player;
    private TileMap map;

    public PlayState() {
        player = new Player(0, 0);
        map = new TileMap("src/main/resources/map.xml");
    }

    public void input(HandleEvent event) {
        player.input(event);
    }

    @Override
    public void update() {
        player.update();
        map.update();
    }



    @Override
    public void render(Graphics2D g) {
        map.render(g);
        player.render(g);
    }
}
