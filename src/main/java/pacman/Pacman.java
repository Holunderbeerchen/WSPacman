package pacman;

import javax.swing.*;


public class Pacman extends JFrame{
    // Das ausgewählte Level
    private final int level;

    public Pacman(int level) {
        this.level = level;

        // Initialisiere eine Model-Instanz und füge sie dem JFrame hinzu
        Model model = new Model(this, level); 
        add(model);
    }

    // Methode zum Abrufen des ausgewählten Levels
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

    // Hauptmethode für den Start des Spiels
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        // Setze den Look and Feel auf das plattformübergreifende Look and Feel
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

        // Starte das Menü, um das Spiel zu initialisieren
        new Menu();

    }

}

