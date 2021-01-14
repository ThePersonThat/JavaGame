package com.alex.spaceattack.core.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEffect {

    public static final String PATH_TO_SOUNDS = "src/main/resources/sounds/";
    Clip clip;

    public void setSoundFile(String filename) {
        try {
            File file = new File(filename);
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (LineUnavailableException e) {
            System.out.println("Unavailable");
        } catch (IOException exception) {
            System.out.println("IOException");
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Unsupported");
        }
    }

    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }
}
