import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.KeyEvent;

public class GameController {
        @FXML
        private GridPane gameGrid;

        private org.example.Pacman pacman;
        public void initialize() {
            setUpGameBoard();
            initializePacman();
        }

        private void setUpGameBoard() {
            // Example: Add walls to the grid
            addWall(1, 1);
            addWall(3, 3);
            // Add more walls as needed

            // Example: Add Pacman to the grid
            addPacman(2, 2);

            // Example: Add Ghost to the grid
            addGhost(4, 4);
        }

        private void addWall(int col, int row) {
            Rectangle wall = new Rectangle(40, 40, Color.BLACK);
            gameGrid.add(wall, col, row);
        }

        private void addPacman(int col, int row) {
            Circle pacman = new Circle(20, Color.YELLOW);
            gameGrid.add(pacman, col, row);
        }

    private void initializePacman() {
        pacman = new org.example.Pacman();
        gameGrid.add(pacman, 2, 2);  // Example: Initial position
    }
    @FXML
    private void handleKeyPress(KeyEvent event) {
        pacman.handleInput(event.getCode());
    }

    private void addGhost(int col, int row) {
            Circle ghost = new Circle(20, Color.RED);
            gameGrid.add(ghost, col, row);
        }

        // Add more methods for handling user input, updating game state, etc.
}

