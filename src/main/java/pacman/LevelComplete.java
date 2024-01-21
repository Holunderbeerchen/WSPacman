package pacman;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.InputStream;

public class LevelComplete extends JFrame {
    Color myGreen = new Color (0, 255,0);

    public LevelComplete(int level, Pacman pacman) {
        super("Level Complete");
        setLayout(new GridLayout(3, 1));
        getContentPane().setBackground(myGreen);

        Font customFont;
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/whitrabt.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(24f);
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.BOLD, 30);
        }

        JLabel levelCompleteLabel = new JLabel("YOU WON", SwingConstants.CENTER);
        levelCompleteLabel.setFont(customFont);
        levelCompleteLabel.setForeground(Color.BLACK);
        levelCompleteLabel.setBackground(Color.GREEN);
        levelCompleteLabel.setBorder(new LineBorder(Color.GREEN, 4));
        levelCompleteLabel.setPreferredSize(new Dimension(100,32));

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

        continueButton.addActionListener(e -> continueGame(level + 1));
        menuButton.addActionListener(e -> returnToMenu());

        add(levelCompleteLabel);
        add(continueButton);
        add(menuButton);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void continueGame(int level) {
        Pacman pac = new Pacman(level);
        pac.setVisible(true);
        pac.setTitle("Pacman");
        pac.setSize(380,420);
        pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pac.setLocationRelativeTo(null);
        dispose();
    }

    private void returnToMenu() {
        Menu menu = new Menu();
        menu.setVisible(true);
        dispose();
    }
}