package pacman;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.InputStream;

// Die Klasse "GameOver" repräsentiert den Bildschirm, der angezeigt wird, wenn das Spiel verloren ist.
public class GameOver extends JFrame {
    // Eine benutzerdefinierte rote Farbe wird erstellt.
    Color myRed = new Color (255, 0,0);

    // Der Konstruktor initialisiert das Layout und die Komponenten des Fensters.
    public GameOver(int level) { //Konstruktor wird aufgerufen, wenn ein Objekt der Klasse erstellt wird.
        // Parameter level wird übergeben, um das aktuelle Level zu speichern
        super("Game Over");
        // Spielt einen traurigen Soundeffekt ab, wenn das Spiel verloren ist.
        SoundEffect.play("Audio/tf_nemesis.wav");
        setLayout(new GridLayout(3, 1)); //Das Layout des Fenster, Raster mit 3 Reihen und 1 Spalte
        getContentPane().setBackground(myRed);

        // Versucht, eine benutzerdefinierte Schriftart zu laden. Wenn dies fehlschlägt, wird eine Standardschriftart verwendet.
        Font customFont;
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/whitrabt.ttf");
            assert is != null;
            customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(24f);
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.BOLD, 30);
        }

        // Erstellt und konfiguriert ein JLabel, das anzeigt, dass das Spiel verloren ist.
        JLabel gameOverLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
        gameOverLabel.setFont(customFont);
        gameOverLabel.setForeground(Color.BLACK);
        gameOverLabel.setBackground(Color.RED);
        gameOverLabel.setBorder(new LineBorder(Color.RED, 4));
        gameOverLabel.setPreferredSize(new Dimension(100,32)); //Rahmengröße des Fensters

        // Erstellt und konfiguriert zwei JButton-Objekte, die es dem Benutzer ermöglichen, das Level neu zu starten oder zum Menü zurückzukehren.
        JButton restartButton = new JButton("Restart Level");
        JButton menuButton = new JButton("Return to Menu");

        //Jeweils für Fortsetzungsbutton und Menübutton die Farben
        restartButton.setFont(customFont);
        restartButton.setBackground(Color.BLACK);
        restartButton.setForeground(Color.YELLOW);
        restartButton.setBorder(new LineBorder(Color.YELLOW, 4));
        restartButton.setPreferredSize(new Dimension(100, 32));

        menuButton.setFont(customFont);
        menuButton.setBackground(Color.BLACK);
        menuButton.setForeground(Color.YELLOW);
        menuButton.setBorder(new LineBorder(Color.YELLOW, 4));
        menuButton.setPreferredSize(new Dimension(100, 32));

        // Fügt den Buttons Aktionen hinzu, die ausgeführt werden, wenn sie geklickt werden.
        restartButton.addActionListener(e -> restartLevel(level));
        menuButton.addActionListener(e -> returnToMenu());

        // Fügt die Komponenten zum Fenster hinzu.
        add(gameOverLabel);
        add(restartButton);
        add(menuButton);
        pack(); //Die Größe des Fensters automatisch an die Größe der enthaltenen Komponenten anzupassen.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Die Methode "restartLevel" startet das aktuelle Level neu.
    private void restartLevel(int level) {
        Pacman pac = new Pacman(level); //Neues Pacman-Objekt wird erstellt und das aktuelle Level übergeben
        pac.setVisible(true);
        pac.setTitle("Pacman");
        pac.setSize(380,420);
        pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pac.setLocationRelativeTo(null);
        dispose(); //Das aktuelle Fenster wird geschlossen
    }

    // Die Methode "returnToMenu" kehrt zum Hauptmenü des Spiels zurück.
    private void returnToMenu() {
        Menu menu = new Menu();
        menu.setVisible(true);
        dispose();
    }
}