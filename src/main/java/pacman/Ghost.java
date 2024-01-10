package pacman;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.Random;

public class Ghost extends Circle {
    private static final int GHOST_RADIUS = 20;
    private static final double GHOST_SPEED = 3.0;

    public Ghost(double initialX, double initialY) {
        super(GHOST_RADIUS, Color.RED);
        setCenterX(initialX);
        setCenterY(initialY);
    }

    public void moveRandomly() {
        Random random = new Random();
        double deltaX = (random.nextDouble() - 0.5) * GHOST_SPEED * 2;
        double deltaY = (random.nextDouble() - 0.5) * GHOST_SPEED * 2;

        setCenterX(getCenterX() + deltaX);
        setCenterY(getCenterY() + deltaY);
    }
}

