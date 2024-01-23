package pacman;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.InputStream;

// Die Klasse "LevelComplete" repräsentiert den Bildschirm, der angezeigt wird, wenn ein Level abgeschlossen ist.
public class LevelComplete extends JFrame {
    // Eine benutzerdefinierte grüne Farbe wird erstellt.
    Color myGreen = new Color (0, 255,0);

    // Der Konstruktor der Klasse "LevelComplete". Er initialisiert das Layout und die Komponenten des Fensters.
    public LevelComplete(int level) {
        super("Level Complete");
        setLayout(new GridLayout(3, 1));
        getContentPane().setBackground(myGreen);

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

        // Erstellt und konfiguriert ein JLabel, das anzeigt, dass das Level abgeschlossen ist.
        JLabel levelCompleteLabel = new JLabel("YOU WON", SwingConstants.CENTER);
        levelCompleteLabel.setFont(customFont);
        levelCompleteLabel.setForeground(Color.BLACK);
        levelCompleteLabel.setBackground(Color.GREEN);
        levelCompleteLabel.setBorder(new LineBorder(Color.GREEN, 4));
        levelCompleteLabel.setPreferredSize(new Dimension(100,32));

        // Erstellt und konfiguriert zwei JButton-Objekte, die es dem Benutzer ermöglichen, das Spiel fortzusetzen oder zum Menü zurückzukehren.
        JButton continueButton = new JButton("Continue");
        JButton menuButton = new JButton("Return to Menu");

        continueButton.setFont(customFont);
        continueButton.setBackground(Color.BLACK);
        continueButton.setForeground(Color.YELLOW);
        continueButton.setBorder(new LineBorder(Color.YELLOW, 4));
        continueButton.setPreferredSize(new Dimension(100, 32));

        menuButton.setFont(customFont);
        menuButton.setBackground(Color.BLACK);
        menuButton.setForeground(Color.YELLOW);
        menuButton.setBorder(new LineBorder(Color.YELLOW, 4));
        menuButton.setPreferredSize(new Dimension(100, 32));

        // Fügt den Buttons Aktionen hinzu, die ausgeführt werden, wenn sie geklickt werden.
        continueButton.addActionListener(e -> continueGame(level + 1));
        menuButton.addActionListener(e -> returnToMenu());

        // Fügt die Komponenten zum Fenster hinzu.
        add(levelCompleteLabel);
        add(continueButton);
        add(menuButton);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Die Methode "continueGame" startet das nächste Level des Spiels.
    private void continueGame(int level) {
        Pacman pac = new Pacman(level);
        pac.setVisible(true);
        pac.setTitle("Pacman");
        pac.setSize(380,420);
        pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pac.setLocationRelativeTo(null);
        dispose();
    }

    // Die Methode "returnToMenu" kehrt zum Hauptmenü des Spiels zurück.
    private void returnToMenu() {
        Menu menu = new Menu();
        menu.setVisible(true);
        dispose();
    }
}