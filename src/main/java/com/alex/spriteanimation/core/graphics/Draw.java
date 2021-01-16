package com.alex.spriteanimation.core.graphics;

import com.alex.spriteanimation.core.util.Camera;

import java.awt.*;

public interface Draw {
    void render(Graphics2D g, Camera camera);
    void update();
}
