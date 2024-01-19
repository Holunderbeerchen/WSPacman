package pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

public class Menu extends JFrame {
    Color myBlue = new Color(34, 83, 128);
    public Menu() {
        super("Welcome to P4c!"); // Set the title of the JFrame
        setLayout(new GridLayout(3, 1)); // Adjust the layout to accommodate the new label
        getContentPane().setBackground(myBlue);
        // Load the custom font
        Font rabbitFont;
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/whitrabt.ttf");
            rabbitFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(24f);
        } catch (Exception e) {
            e.printStackTrace();
            // Fallback to Arial if the custom font cannot be loaded
            rabbitFont = new Font("Arial", Font.BOLD, 24);
        }

        // Create a JLabel for the welcome message
        JLabel welcomeLabel = new JLabel("Welcome to P4c!", SwingConstants.CENTER);
        welcomeLabel.setFont(rabbitFont);
        welcomeLabel.setForeground(Color.BLUE);

        JButton level1Button = new JButton("Level 1");
        JButton level2Button = new JButton("Level 2");

        // Customize buttons
        level1Button.setFont(rabbitFont);
        level2Button.setFont(rabbitFont);
        level1Button.setBackground(Color.BLUE);
        level2Button.setBackground(Color.BLUE);
        level1Button.setForeground(Color.RED);
        level2Button.setForeground(Color.RED);

        level1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(1);
            }
        });
        level2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(2);
            }
        });
        add(welcomeLabel); // Add the welcome label to the JFrame
        add(level1Button);
        add(level2Button);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400); // Set size of the JFrame
        setLocationRelativeTo(null); // Center the JFrame
        setVisible(true);
    }

    private void startGame(int level) {
        Pacman pac = new Pacman(level);
        pac.setVisible(true);
        pac.setTitle("Pacman");
        pac.setSize(380,420);
        pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pac.setLocationRelativeTo(null);
        dispose();
    }
}