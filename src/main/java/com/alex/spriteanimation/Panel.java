package com.alex.spriteanimation;


import com.alex.spriteanimation.core.states.GameState;
import com.alex.spriteanimation.core.states.PlayState;
import com.alex.spriteanimation.core.util.HandleEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Panel extends JPanel implements Runnable {
    private GameState state;
    private boolean running = true;
    private Thread thread;
    private BufferStrategy buffer;
    private BufferedImage image;
    private Graphics graphics;
    private double fps;

    public Panel(BufferStrategy buffer) {
        this.buffer = buffer;
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
                state.input(new HandleEvent(e, true));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                state.input(new HandleEvent(e, false));
            }
        });

        state = new PlayState();
    }

    private void initGraphics() {
        image = new BufferedImage(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        graphics = image.getGraphics();
    }


    @Override
    public void run() {

        initGraphics();

        final double GAME_HERTZ = 60;
        final double TBU = 1_000_000_000 / GAME_HERTZ;

        final int MUBR = 5;
        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 60;
        final double TTBR = 1_000_000_000 / TARGET_FPS;
        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1_000_000_000);
        int oldFrameCount = 0;

        while (running) {
            double now = System.nanoTime();
            int updateCount = 0;
            while(((now - lastUpdateTime) > TBU) && (updateCount < MUBR)) {
                update();
                lastUpdateTime += TBU;
                updateCount++;
            }

            if(now - lastUpdateTime > TBU) {
                lastUpdateTime = now - TBU;
            }

            render();
            draw();
            lastRenderTime = now;
            frameCount++;
            int thisSecond = (int) (lastUpdateTime / 1_000_000_000);

            if(thisSecond > lastSecondTime) {
                if(frameCount != oldFrameCount) {
                    System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
                }
                fps = frameCount;
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            while (now - lastRenderTime < TTBR && now - lastUpdateTime < TBU) {
                Thread.yield();

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                now = System.nanoTime();
            }
        }
    }

    private void update() {
        state.update();
    }

    private void render() {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        state.render((Graphics2D) graphics);
    }

    private void draw() {
        do {
            Graphics g2 = buffer.getDrawGraphics();
            g2.drawImage(image, 3, 26,Window.WINDOW_WIDTH + 10, Window.WINDOW_HEIGHT + 10, null);
            g2.dispose();
            buffer.show();
        } while(buffer.contentsLost());
    }

    @Override
    public void addNotify() {
        super.addNotify();

        if (thread == null) {
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }
}
