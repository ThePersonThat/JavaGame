package com.alex.spriteanimation;


import com.alex.spriteanimation.core.states.GameState;
import com.alex.spriteanimation.core.states.PlayState;
import com.alex.spriteanimation.core.util.HandleEvent;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Panel implements Runnable {

    private final int WIDTH = Window.WINDOW_WIDTH;
    private final int HEIGHT = Window.WINDOW_HEIGHT;

    private JFrame frame;
    private Canvas canvas;
    private BufferStrategy bufferStrategy;
    private GameState state;

    public Panel() {
        frame = new JFrame("Game");

        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(null);

        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);

        canvas.addKeyListener(new KeyControl());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();

        canvas.requestFocus();

        state = new PlayState();
    }


    private class KeyControl extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            state.input(new HandleEvent(e, true));
        }

        @Override
        public void keyReleased(KeyEvent e) {
            state.input(new HandleEvent(e, false));
        }
    }

    private final long TARGET_FPS = 65;
    private final long TARGET_DELTA_LOOP = (1000_000_000) / TARGET_FPS;
    private boolean running = true;
    private int fps;

    @Override
    public void run() {
        long beginLoopTime;
        long endLoopTime;
        long currentUpdateTime = System.nanoTime();
        long lastUpdateTime;
        long deltaLoop;
        int counter = 0;

        while (running) {
            beginLoopTime = System.nanoTime();

            render();

            lastUpdateTime = currentUpdateTime;
            currentUpdateTime = System.nanoTime();
            update();

            endLoopTime = System.nanoTime();
            deltaLoop = endLoopTime - beginLoopTime;

            counter += currentUpdateTime - lastUpdateTime;
            fps++;

            if(counter >= 1000_000_000) {
                System.out.println("fps: " + fps);
                fps = 0;
                counter = 0;
            }

            if (deltaLoop > TARGET_DELTA_LOOP) {

            } else {
                try {
                    Thread.sleep((TARGET_DELTA_LOOP - deltaLoop) / (1000 * 1000));
                } catch (InterruptedException e) {

                }
            }
        }
    }

    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        render(g);
        g.dispose();
        bufferStrategy.show();
    }


    private void update() {
        state.update();
    }

    private void render(Graphics2D g){
        state.render(g);
    }

}
