package pacman;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.InputStream;

public class GameOver extends JFrame {
    public GameOver(int level) {
        super("Game Over");
        setLayout(new GridLayout(3, 1));

        Font customFont;
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/whitrabt.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(24f);
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.BOLD, 24);
        }

        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverLabel.setFont(customFont);
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setBackground(Color.BLACK);


        JButton restartButton = new JButton("Restart Level");
        JButton menuButton = new JButton("Return to Menu");

        restartButton.setFont(customFont);
        restartButton.setBackground(Color.BLUE);
        restartButton.setForeground(Color.RED);
        restartButton.setBorder(new LineBorder(Color.RED, 4)); // Add a red border
        restartButton.setPreferredSize(new Dimension(100, 32)); // Set preferred size

        menuButton.setFont(customFont);
        menuButton.setBackground(Color.BLUE);
        menuButton.setForeground(Color.RED);
        menuButton.setBorder(new LineBorder(Color.RED, 4)); // Add a red border
        menuButton.setPreferredSize(new Dimension(100, 32)); // Set preferred size

        restartButton.addActionListener(e -> restartLevel(level));
        menuButton.addActionListener(e -> returnToMenu());

        add(gameOverLabel);
        add(restartButton);
        add(menuButton);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void restartLevel(int level) {
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