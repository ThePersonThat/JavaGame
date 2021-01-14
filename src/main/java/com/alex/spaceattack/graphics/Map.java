package com.alex.spaceattack.graphics;

import com.alex.spaceattack.core.sprite.Background;
import com.alex.spaceattack.core.sprite.Hero;
import com.alex.spaceattack.core.sprite.Missile;
import com.alex.spaceattack.core.sprite.Alien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Map extends JPanel implements ActionListener {
    private final int DELAY = 25;
    private Timer timer;
    private int x, y;
    private Hero hero;
    private Background background;
    private java.util.List<Alien> aliens;
    private boolean playerInGame = true;

    private final int[][] pos = {
            { ThreadLocalRandom.current().nextInt(50, 350), ThreadLocalRandom.current().nextInt(-400, 0) },
            { ThreadLocalRandom.current().nextInt(50, 350), ThreadLocalRandom.current().nextInt(-400, 0) },
            { ThreadLocalRandom.current().nextInt(50, 350), ThreadLocalRandom.current().nextInt(-400, 0) },
            { ThreadLocalRandom.current().nextInt(50, 350), ThreadLocalRandom.current().nextInt(-400, 0) },
            { ThreadLocalRandom.current().nextInt(50, 350), ThreadLocalRandom.current().nextInt(-400, 0) },
            { ThreadLocalRandom.current().nextInt(50, 350), ThreadLocalRandom.current().nextInt(-400, 0) },
            { ThreadLocalRandom.current().nextInt(50, 350), ThreadLocalRandom.current().nextInt(-400, 0) },
            { ThreadLocalRandom.current().nextInt(50, 350), ThreadLocalRandom.current().nextInt(-400, 0) },
            { ThreadLocalRandom.current().nextInt(50, 350), ThreadLocalRandom.current().nextInt(-400, 0) },
            { ThreadLocalRandom.current().nextInt(50, 350), ThreadLocalRandom.current().nextInt(-400, 0) },
            { ThreadLocalRandom.current().nextInt(50, 350), ThreadLocalRandom.current().nextInt(-400, 0) },
            { ThreadLocalRandom.current().nextInt(50, 350), ThreadLocalRandom.current().nextInt(-400, 0) },
            { ThreadLocalRandom.current().nextInt(50, 350), ThreadLocalRandom.current().nextInt(-400, 0) },
            { ThreadLocalRandom.current().nextInt(50, 350), ThreadLocalRandom.current().nextInt(-400, 0) },
            { ThreadLocalRandom.current().nextInt(50, 350), ThreadLocalRandom.current().nextInt(-400, 0) },
            { ThreadLocalRandom.current().nextInt(50, 350), ThreadLocalRandom.current().nextInt(-400, 0) },
            { ThreadLocalRandom.current().nextInt(50, 350), ThreadLocalRandom.current().nextInt(-400, 0) },
            { ThreadLocalRandom.current().nextInt(50, 350), ThreadLocalRandom.current().nextInt(-400, 0) },
            { ThreadLocalRandom.current().nextInt(50, 350), ThreadLocalRandom.current().nextInt(-400, 0) },
            { ThreadLocalRandom.current().nextInt(50, 350), ThreadLocalRandom.current().nextInt(-400, 0) },
    };

    public Map() {
        initPanel();
    }

    private void initPanel() {

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT));
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        hero = new Hero(Window.WINDOW_WIDTH / 2 - 25, Window.WINDOW_HEIGHT / 2 + 100);
        background = new Background();

        initAliens();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void initAliens() {
        aliens = new ArrayList<>();

        for(int[] p : pos) {
            aliens.add(new Alien(p[0], p[1]));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(playerInGame) {
            drawingGame(g);
        }
        else {
            drawingGameOver(g);
        }
    }

    private void drawingGameOver(Graphics g) {
        String string = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.WHITE);
        g.setFont(small);
        g.drawString(string, (Window.WINDOW_WIDTH - fm.stringWidth(string)) / 2, Window.WINDOW_HEIGHT / 2);
    }



    private void drawingGame(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();

        for(int i = 0; i < background.getCount(); i++) {
            g2d.drawImage(background.getImage(), background.getX(), background.getY(i), this);
        }

        if(hero.isVisible()) {
            g2d.drawImage(hero.getImage(), hero.getX(), hero.getY(), hero.getWidth(), hero.getHeight(), this);
        }



        java.util.List<Missile> missileList = hero.getMissiles();

        for(Missile missile : missileList) {
            g2d.drawImage(missile.getImage(), missile.getX(), missile.getY(), missile.getWidth(), missile.getHeight(), this);
        }


        for(Alien alien : aliens) {
            if(alien.isVisible()) {
                g2d.rotate(Math.toRadians(180), alien.getX() + alien.getWidth() / 2, alien.getY() + alien.getHeight() / 2);
                g2d.drawImage(alien.getImage(), alien.getX(), alien.getY(), alien.getWidth(), alien.getHeight(), this);
                g2d.setTransform(old);
            }
        }



        g2d.setColor(Color.WHITE);
        g2d.drawString("Aliens left: " + aliens.size(), 5, 15);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();

        updateHero();
        updateBackground();
        updateMissiles();
        updateAliens();

        checkCollisions();

        repaint();
    }

    private void inGame() {
        if(!playerInGame)
            timer.stop();
    }

    private void updateBackground() {
        background.increase();
    }

    private void updateMissiles() {
        java.util.List<Missile> missileList = hero.getMissiles();

        for(int i = 0; i < missileList.size(); i++) {
            Missile missile = missileList.get(i);

            if(missile.isVisible())
                missile.move();
            else
                missileList.remove(i);
        }
    }

    private void updateAliens() {
        if(aliens.isEmpty()) {
            playerInGame = false;
            return;
        }

        for(int i = 0; i < aliens.size(); i++) {
            Alien alien = aliens.get(i);

            if(alien.isVisible()) {
                alien.move();
            } else {
                aliens.remove(i);
            }
        }
    }

    private void checkCollisions() {
        Rectangle rectangleHero = hero.getBounds();

        for(Alien alien : aliens) {
            Rectangle rectangleAlien = alien.getBounds();

            if(rectangleAlien.intersects(rectangleHero)) {
                hero.setVisible(false);
                alien.setVisible(false);
                playerInGame = false;
            }
        }

        java.util.List<Missile> missiles = hero.getMissiles();

        for(Missile m : missiles) {
            Rectangle rectangleMissile = m.getBounds();

            for(Alien alien : aliens) {
                Rectangle rectangleAlien = alien.getBounds();

                if(rectangleMissile.intersects(rectangleAlien)) {
                    m.setVisible(false);
                    alien.setVisible(false);
                }
            }
        }
    }

    private void updateHero() {
        hero.move();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            hero.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            hero.keyPressed(e);
        }
    }
}
