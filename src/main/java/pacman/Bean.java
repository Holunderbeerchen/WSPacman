package pacman;

import java.awt.*;

// Die Klasse "Bean" repräsentiert ein Element im Spiel Pacman, das von der Spielfigur gesammelt werden kann.
public class Bean {
    // x und y sind die Koordinaten der Bohne auf dem Spielfeld.
    private int x, y;
    // POINTS ist die Punktzahl, die der Spieler erhält, wenn er diese Bohne sammelt.
    private final int POINTS = 50;
    // image ist das Bild der Bohne, das auf dem Bildschirm angezeigt wird.
    private Image image;

    // Der Konstruktor der Klasse "Bean". Er initialisiert die Koordinaten und das Bild der Bohne.
    public Bean(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    // Getter-Methode für die x-Koordinate der Bohne.
    public int getX() {
        return x;
    }

    // Getter-Methode für die y-Koordinate der Bohne.
    public int getY() {
        return y;
    }

    // Getter-Methode für die Punktzahl der Bohne.
    public int getPoints() {
        return POINTS;
    }

    // Getter-Methode für das Bild der Bohne.
    public Image getImage() {
        return image;
    }
}
