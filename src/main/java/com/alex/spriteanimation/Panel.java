package com.alex.spriteanimation;


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

        player = new Player(0, 0);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(player.getSprite(), player.getX(), player.getY(), player.getWidth(), player.getHeight(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        player.move();
        repaint();
    }
}
