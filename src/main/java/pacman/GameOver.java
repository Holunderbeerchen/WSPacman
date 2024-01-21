package pacman;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.InputStream;

public class GameOver extends JFrame {
    Color myRed = new Color (255, 0,0);
    public GameOver(int level) {
        super("Game Over");
        SoundEffect.play("Audio/tf_nemesis.wav");
        setLayout(new GridLayout(3, 1));
        getContentPane().setBackground(myRed);

        Font customFont;
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/whitrabt.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(24f);
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.BOLD, 30);
        }

        JLabel gameOverLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
        gameOverLabel.setFont(customFont);
        gameOverLabel.setForeground(Color.BLACK);
        gameOverLabel.setBackground(Color.RED);
        gameOverLabel.setBorder(new LineBorder(Color.RED, 4));
        gameOverLabel.setPreferredSize(new Dimension(100,32));


        JButton restartButton = new JButton("Restart Level");
        JButton menuButton = new JButton("Return to Menu");

        restartButton.setFont(customFont);
        restartButton.setBackground(Color.BLACK);
        restartButton.setForeground(Color.YELLOW);
        restartButton.setBorder(new LineBorder(Color.YELLOW, 4)); // Add a red border
        restartButton.setPreferredSize(new Dimension(100, 32)); // Set preferred size

        menuButton.setFont(customFont);
        menuButton.setBackground(Color.BLACK);
        menuButton.setForeground(Color.YELLOW);
        menuButton.setBorder(new LineBorder(Color.YELLOW, 4)); // Add a red border
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