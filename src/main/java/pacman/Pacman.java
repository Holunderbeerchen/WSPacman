package pacman;

import javax.swing.*;


public class Pacman extends JFrame{
    // Das ausgew�hlte Level
    private final int level;

    public Pacman(int level) {
        this.level = level;

        // Initialisiere eine Model-Instanz und f�ge sie dem JFrame hinzu
        Model model = new Model(this, level); 
        add(model);
    }

    // Methode zum Abrufen des ausgew�hlten Levels
    public int getLevel() {
        return this.level;
    }

    // Methode, die aufgerufen wird, wenn Pacman stirbt
    public void death() {
        
        // Erstelle ein GameOver-Fenster mit dem aktuellen Level
        GameOver gameOver = new GameOver(level);
        gameOver.setVisible(true);
        setVisible(false);
    }

    // Hauptmethode f�r den Start des Spiels
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Setze den Look and Feel auf das plattform�bergreifende Look and Feel
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

        // Starte das Men�, um das Spiel zu initialisieren
        new Menu();

    }

}

