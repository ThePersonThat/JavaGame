package com.alex.spriteanimation;


import com.alex.spriteanimation.graphics.map.TileMap;
import com.alex.spriteanimation.logic.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Panel extends JPanel implements ActionListener{
    private static final int DELAY = 25;
    private Timer timer;
    private Player player;
    private TileMap map;

    public Panel() {
        initPanel();
    }

    private void initPanel() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.keyReleased(e);
            }
        });

        setBackground(Color.WHITE);
        map = new TileMap("src/main/resources/map.xml");

        player = new Player(0, 0);
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        map.update(g, this);
        g.drawImage(player.getSprite(), player.getX(), player.getY(), player.getWidth(), player.getHeight(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        player.move();
        repaint();
    }
}
