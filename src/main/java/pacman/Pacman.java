package pacman;

import javax.swing.*;


public class Pacman extends JFrame{

    public Pacman(int level) {
        add(new Model(level));
    }


    public static void main(String[] args) {
        new Menu();

    }

}

