package pacman;

import java.awt.*;

public class Bean {
    private int x, y;
    private final int POINTS = 50;
    private Image image;

    public Bean(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPoints() {
        return POINTS;
    }

    public Image getImage() {
        return image;
    }

}
