package pacman;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

public class SoundEffect {
    public static void play(String soundFileName) {
        try {
                URL url = SoundEffect.class.getClassLoader().getResource(soundFileName);
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
        }
    }
}

