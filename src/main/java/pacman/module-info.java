module pacman {
    requires javafx.controls;
    requires javafx.fxml;

    opens pacman to javafx.fxml;

    exports pacman;
}