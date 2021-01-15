package com.alex.spriteanimation;

import javax.swing.*;
import java.awt.image.BufferStrategy;

public class Window extends JFrame {
    public final static int WINDOW_HEIGHT = 400;
    public final static int WINDOW_WIDTH = 400;

    private BufferStrategy buffer;

    public Window() {
        setTitle("The Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIgnoreRepaint(true);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void addNotify() {
        super.addNotify();

        createBufferStrategy(2);
        buffer = getBufferStrategy();

        setContentPane(new Panel(buffer));
    }
}
