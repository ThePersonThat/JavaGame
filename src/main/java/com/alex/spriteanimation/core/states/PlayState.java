package com.alex.spriteanimation.core.states;

import com.alex.spriteanimation.Window;
import com.alex.spriteanimation.core.entities.Player;
import com.alex.spriteanimation.core.graphics.map.TileMap;
import com.alex.spriteanimation.core.util.Camera;
import com.alex.spriteanimation.core.util.HandleEvent;

import java.awt.*;

public class PlayState extends GameState {
    private Player player;
    private TileMap map;
    private Camera camera;

    public PlayState() {
        player = new Player(Window.WINDOW_WIDTH / 2, Window.WINDOW_HEIGHT / 2);
        map = new TileMap("src/main/resources/map.xml");
        camera = new Camera(player, map);
    }

    public void input(HandleEvent event) {
        player.input(event);
    }

    @Override
    public void update() {
        player.update();
        camera.update();
    }

    @Override
    public void render(Graphics2D g) {
        map.render(g, camera);
        player.render(g, camera);
    }
}
