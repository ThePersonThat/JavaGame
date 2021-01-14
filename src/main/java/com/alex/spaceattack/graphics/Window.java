package com.alex.spaceattack.graphics;

import javax.swing.*;

public class Window extends JFrame {

    public final static int WINDOW_HEIGHT = 400;
    public final static int WINDOW_WIDTH = 400;

    public Window() {
        add(new Map());
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Space Attack");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);
        setResizable(false);
        pack();
    }
}
