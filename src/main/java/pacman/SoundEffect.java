package pacman;

import javax.sound.sampled.*;

import java.io.IOException;
import java.net.URL;

// Die Klasse "SoundEffect" ist verantwortlich für das Abspielen von Soundeffekten im Spiel.
public class SoundEffect {
    // Die Methode "play" nimmt den Namen einer Sounddatei als Parameter und spielt den entsprechenden Sound ab.
    public static void play(String soundFileName) {
        try {
            // Die Methode "getResource" wird verwendet, um die URL der Sounddatei zu erhalten.
            URL url = SoundEffect.class.getClassLoader().getResource(soundFileName);
            // Es wird sichergestellt, dass die URL nicht null ist.
            assert url != null;
            // Ein AudioInputStream wird erstellt, um den Sound zu lesen.
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Ein Clip wird erstellt, der den Sound abspielen kann.
            Clip clip = AudioSystem.getClip();
            // Der Clip wird mit dem AudioInputStream geöffnet.
            clip.open(audioIn);
            // Der Clip wird gestartet, was den Sound abspielt.
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // Wenn beim Abspielen des Sounds ein Fehler auftritt, wird der Stacktrace ausgegeben.
            e.printStackTrace();
        }
    }
}

