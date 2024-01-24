package pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

public class Menu extends JFrame {
    Color myYellow = new Color(255, 255, 0);
    // Aufruf des Konstruktors der Oberklasse und Setzen des Fenstertitels
    public Menu() {
        //Titel
        super("Welcome to P4c!");
        // Setze das Layout des JFrames auf ein 3x1-GridLayout
        setLayout(new GridLayout(3, 1));
        // Setze die Hintergrundfarbe des JFrame auf Gelb
        getContentPane().setBackground(myYellow);

        // Benutzerdefinierte Schriftart laden
        Font rabbitFont;
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/whitrabt.ttf");
            rabbitFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(24f);
        } catch (Exception e) {
            e.printStackTrace();
            // Fallback to Arial, wenn die benutzerdefinierte Schriftart nicht geladen werden kann
            rabbitFont = new Font("Arial", Font.BOLD, 24);
        }

        // Benutzerdefinierte Schriftart laden
        JLabel welcomeLabel = new JLabel("Welcome to P4c!", SwingConstants.CENTER);
        welcomeLabel.setFont(rabbitFont);
        welcomeLabel.setForeground(Color.BLACK);

        JButton level1Button = new JButton("Level 1");
        JButton level2Button = new JButton("Level 2");

        // Buttons
        level1Button.setFont(rabbitFont);
        level2Button.setFont(rabbitFont);
        level1Button.setBackground(Color.BLACK);
        level2Button.setBackground(Color.BLACK);
        level1Button.setForeground(Color.YELLOW);
        level2Button.setForeground(Color.YELLOW);

        // Füge ActionListener für die Buttons hinzu, um das Spiel mit dem ausgewählten Level zu starten
        level1Button.addActionListener(e -> startGame(1));
        level2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(2);
            }
        });
        // FÜge die Komponenten zum JFrame hinzu
        add(welcomeLabel);
        add(level1Button);
        add(level2Button);

        // Füge die Komponenten zum JFrame hinzu
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Setze die Größe un zentriere das JFrame auf den Bildschirm
        setSize(400, 400);
        setLocationRelativeTo(null); // Zentriere JFrame
        setVisible(true);
    }

    // Methode zum Starten des Spiels mit dem ausgewählten Level
    private void startGame(int level) {
       // Erstelle ein neues Pacman-Objekt
        Pacman pac = new Pacman(level);
        // Setze Eigenschaften des Pacman-Fensters
        pac.setVisible(true);
        pac.setTitle("Pacman");
        pac.setSize(380,420);
        pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pac.setLocationRelativeTo(null);
        // Schließe das aktuelle JFrame
        dispose();
    }
}