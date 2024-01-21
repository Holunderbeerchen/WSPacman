package pacman;

import javax.swing.*;


public class Pacman extends JFrame{

    private int level;

    public Pacman(int level) {
        this.level = level;
        add(new Model(this, level));
    }

    public int getLevel() {
        return this.level;
    }

    public void death() {
        GameOver gameOver = new GameOver(level);
        gameOver.setVisible(true);
        setVisible(false);
    }


    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        new Menu();

    }

}

