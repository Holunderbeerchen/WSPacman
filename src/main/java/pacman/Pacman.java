package org.example;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Pacman extends Circle {
    private static final int PACMAN_RADIUS = 20;
    private static final double PACMAN_SPEED = 5.0;

    public Pacman() {
        super(PACMAN_RADIUS, Color.YELLOW);
    }

    public void move(KeyCode direction) {
        switch (direction) {
            case UP:
                setCenterY(getCenterY() - PACMAN_SPEED);
                break;
            case DOWN:
                setCenterY(getCenterY() + PACMAN_SPEED);
                break;
            case LEFT:
                setCenterX(getCenterX() - PACMAN_SPEED);
                break;
            case RIGHT:
                setCenterX(getCenterX() + PACMAN_SPEED);
                break;
            default:
                break;
        }
    }

    public void handleInput(KeyCode keyCode) {
        switch (keyCode) {
            case UP:
            case DOWN:
            case LEFT:
            case RIGHT:
                move(keyCode);
                break;
            default:
                // Handle other input if needed
                break;
        }
    }
}

