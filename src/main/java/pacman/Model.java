package pacman;


import javax.swing.*;
/*
javax.swing ist ein Paket in der Java Standard Edition (Java SE), das Teil der Java API ist.
Weil es Teil der Java SE ist, wird kein weiteres Framework benötigt.
Es stellt eine Sammlung von Klassen und Schnittstellen bereit, die für die Entwicklung von grafischen
Benutzeroberflächen (GUIs) in Java-Anwendungen verwendet werden. Erweitert java.awt.
*/
import java.awt.*;
/*
javax.awt, ebenfalls Teil von Java SE. Enthält ähnlich wie javax.swing Klassen und Interfaces für GUIs.
Älter als java.swing.
*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Model extends JPanel implements ActionListener {
    /*
    Klasse "Model" enthält das Meiste vom Code. Klasse "JPanel" gehört zum Package: javax.swing.
    Mit dieser Klasse kann man GUI Fenster erstellen die andere GIU-Komponenten enthalten.

    Das ActionListener-Interface gehört zum Package: java.awt.event. Dieses Interface ermöglicht es auf Eingaben vom
    Benutzer zu reagieren. Unter anderem auch auf Eingaben über die Maus.
    */
    private Dimension d;
    /*
    "d" ist ein Dimensions-Objekt. Die Klasse "Dimension" gehört zum Package: java.awt.
    "d" gibt die Anzahl an Pixeln an, aus denen die Spielfläche aufgebaut ist.
    */
    private final Font smallFont = new Font("Arial", Font.BOLD, 14);
    private boolean inGame = false; // eine Variable die überprüft, ob das Spiel gerade ausgeführt wird.
    private boolean dying = false; // eine Variable die überprüft, ob die Spielfigur noch lebt.

    private final int BLOCK_SIZE = 24; // Die Variable gibt an aus wie vielen Pixeln ein Block besteht.
    private final int N_BLOCKS = 15; // Die Variable gibt an aus wie vielen Blöcken die Spielfläche aufgebaut ist.
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    private final int MAX_GHOSTS = 12;


    private int N_GHOSTS = 6; // Anfängliche Anzahl an Gegner.
    private int lives, score;
    private int[] dx; // mögliche Änderung der x-Koordinate der Geister, für alle 4 Situationen
    private int[] dy; // mögliche Änderung der y-Koordinate der Geister, für alle 4 Situationen
    private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;

    private Image heart, ghost;
    private Image up, down, left, right;

    private int pacman_x, pacman_y; // Koordinaten der Spielfigur
    private int pacmand_x, pacmand_y; // die Richtungs-Änderung in x- und y-Richtung der Spielfigur.
    private int req_dx, req_dy;
    // Variablen für die Eingabe mit den Pfeiltasten. Diese Variablen werden mittels der TAdapter Klasse
    // bestimmt. class TAdapter extends KeyAdapter

    private final ArrayList<Bean> beans = new ArrayList<>();
    private final Random random = new Random();
    private Image beanImage;


    private short[] levelData;


    private final int[] validSpeeds = {1, 2, 3, 4, 6, 8};

    private int currentSpeed = 3;
    private short[] screenData; // erstellt mithilfe von levelData[] das Spielfeld.
    private Timer timer;


    private final Pacman pacman;

    public Model(Pacman pacman, int level) {
        if (level == 1) {
            short[] levelData1 = {
                    19, 18, 18, 18, 18, 18, 18, 18, 26, 18, 18, 18, 18, 18, 22,
                    17, 16, 16, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 16, 20,
                    17, 16, 16, 16, 16, 16, 16, 20, 0, 17, 24, 16, 16, 16, 20,
                    17, 16, 16, 16, 16, 16, 16, 20, 0, 21, 0, 17, 16, 16, 20,
                    17, 16, 16, 24, 24, 24, 24, 20, 0, 29, 0, 25, 24, 16, 20,
                    17, 16, 20, 0, 0, 0, 0, 21, 0, 0, 0, 0, 0, 17, 20,
                    17, 16, 20, 0, 19, 22, 0, 17, 18, 22, 0, 19, 18, 16, 20,
                    17, 16, 20, 0, 17, 28, 0, 17, 16, 20, 0, 17, 16, 16, 20,
                    17, 16, 20, 0, 21, 0, 0, 17, 16, 20, 0, 17, 16, 16, 20,
                    17, 16, 20, 0, 17, 18, 18, 16, 16, 20, 0, 17, 16, 16, 20,
                    17, 16, 20, 0, 17, 16, 16, 16, 16, 16, 18, 16, 16, 16, 20,
                    17, 16, 20, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
                    17, 16, 16, 18, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 20,
                    17, 16, 16, 20, 0, 0, 0, 0, 0, 0, 0, 17, 16, 16, 20,
                    25, 24, 24, 24, 26, 26, 26, 26, 26, 26, 26, 24, 24, 24, 28
            };
            levelData = levelData1;
            /*
            Jeder Eintrag = ein Spielbaustein.
            Codierung: 0 = Hindernis, 1 = linker Rand, 2 = oberer Rand, 4 = rechter Rand, 8 = unterer Rand, 16 = Sammelstein.
            Die Summe dieser Werte ist eindeutig, wenn jeder Wert nur einmal vorkommt und eignet sich daher gut um jeden
            Baustein eindeutig zu beschreiben.
            */
        } else if (level == 2) {
            short[] levelData2 = {
                    19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
                    17, 16, 16, 24, 16, 16, 16, 16, 16, 16, 16, 24, 16, 16, 20,
                    17, 16, 20, 0, 17, 16, 16, 16, 16, 16, 20, 0, 17, 16, 20,
                    17, 16, 20, 0, 25, 24, 16, 16, 16, 24, 28, 0, 17, 16, 20,
                    17, 16, 20, 0, 0, 0, 17, 16, 20, 0, 0, 0, 17, 16, 20,
                    17, 16, 20, 0, 19, 18, 16, 16, 16, 18, 22, 0, 17, 16, 20,
                    17, 16, 20, 0, 17, 16, 16, 16, 16, 16, 20, 0, 17, 16, 20,
                    17, 16, 16, 18, 16, 16, 16, 16, 16, 16, 16, 18, 16, 16, 20,
                    17, 16, 16, 16, 16, 24, 24, 24, 24, 24, 16, 16, 16, 16, 20,
                    17, 16, 16, 16, 20, 0, 0, 0, 0, 0, 17, 16, 16, 16, 20,
                    17, 16, 24, 16, 16, 18, 22, 0, 19, 18, 16, 16, 24, 16, 20,
                    17, 20, 0, 25, 16, 16, 20, 0, 17, 16, 16, 28, 0, 17, 20,
                    17, 20, 0, 0, 17, 16, 16, 18, 16, 16, 20, 0, 0, 17, 20,
                    17, 16, 18, 18, 16, 16, 16, 16, 16, 16, 16, 18, 18, 16, 20,
                    25, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28
            };
            levelData = levelData2;
        }

        loadImages();
        initVariables();
        addKeyListener(new TAdapter());
        setFocusable(true);
        initGame();
        this.pacman = pacman;
    }



    private void loadImages() {
        down = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/down.gif"))).getImage();
        up = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/up.gif"))).getImage();
        left = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/left.gif"))).getImage();
        right = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/right.gif"))).getImage();
        ghost = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/ghost.gif"))).getImage();
        heart = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/heart.png"))).getImage();
        beanImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/bohnen.png"))).getImage();
    }
    private void initVariables() {

        screenData = new short[N_BLOCKS * N_BLOCKS];
        d = new Dimension(400, 400);
        ghost_x = new int[MAX_GHOSTS];
        ghost_dx = new int[MAX_GHOSTS];
        ghost_y = new int[MAX_GHOSTS];
        ghost_dy = new int[MAX_GHOSTS];
        ghostSpeed = new int[MAX_GHOSTS];
        dx = new int[4];
        dy = new int[4];

        timer = new Timer(40, this);
        /*
        this: gibt das Objekt an, das als ActionListener für den Timer fungieren wird. Der ActionListener hört
        in dem Fall auf das Timer-Objekt. Also die hört die Klasse "Model" auf den Timer.
        Der Timer in Java ist ein Mechanismus, der dazu dient, regelmäßig wiederkehrende Aufgaben auszuführen.
        Um auf Ereignisse des Timers zu reagieren, muss man einen sogenannten "ActionListener" implementieren.
        Ein ActionListener ist ein Interface in Java, die eine Methode namens actionPerformed definiert. Diese
        Methode wird aufgerufen, wenn das Ereignis eintritt, auf das der ActionListener hört.

        Timer-delay hat keinen Einfluss auf die Geschwindigkeit der .gif-Animationen.
         */
        timer.start();
    }

    private void playGame(Graphics2D g2d) {

        if (dying) {

            death();

        } else {

            movePacman();
            drawPacman(g2d);
            moveGhosts(g2d);
            checkMaze();
        }
    }

    private void showIntroScreen(Graphics2D g2d) {

        String start = "Press SPACE to start";
        g2d.setColor(Color.yellow);
        g2d.drawString(start, (SCREEN_SIZE)/3, 180);
    }

    private void drawScore(Graphics2D g) {
        g.setFont(smallFont);
        g.setColor(new Color(5, 181, 79));
        String s = "Score: " + score;
        g.drawString(s, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 16);

        for (int i = 0; i < lives; i++) {
            g.drawImage(heart, i * 28 + 8, SCREEN_SIZE + 1, this);
        }
    }

    private void placeBeansRandomly() {
        ArrayList<Integer> emptyBlocks = new ArrayList<>();
        for (int i = 0; i < N_BLOCKS * N_BLOCKS; i++) {
            if (screenData[i] == 0) { // if the block is empty
                emptyBlocks.add(i);
            }
        }
        if (!emptyBlocks.isEmpty()) {
            int randomBlockIndex = random.nextInt(emptyBlocks.size());
            int randomBlock = emptyBlocks.get(randomBlockIndex);
            int x = randomBlock % N_BLOCKS;
            int y = randomBlock / N_BLOCKS;
            beans.add(new Bean(x * BLOCK_SIZE, y * BLOCK_SIZE, beanImage));
        }
    }

    private void checkMaze() {
        // Überprüft, ob das Spiel gewonnen wurde und startet es dann mit neuen Gegnern neu.
        int i = 0;
        boolean finished = true;

        while (i < N_BLOCKS * N_BLOCKS && finished) {

            if ((screenData[i] & 48)!= 0) {
                finished = false;
            }

            i++;
        }
        for (Bean bean : beans) {
            if (pacman_x > (bean.getX() - 12) && pacman_x < (bean.getX() + 12)
                    && pacman_y > (bean.getY() - 12) && pacman_y < (bean.getY() + 12)) {

                score += bean.getPoints();
                beans.remove(bean);
                SoundEffect.play("Audio/bean.wav"); // play the sound when a bean is collected
                break;
            }
        }
        if (finished) {
            SoundEffect.play("Audio/slowClap2.wav");
            score += 50;

            if (N_GHOSTS < MAX_GHOSTS) {
                N_GHOSTS++;
            }

            int maxSpeed = 6;
            if (currentSpeed < maxSpeed) {
                currentSpeed++;
            }
            // Aufruf der LevelComplete-Klasse
            LevelComplete levelCompleteScreen = new LevelComplete(pacman.getLevel(), pacman);
            levelCompleteScreen.setVisible(true);
            pacman.setVisible(false);

            initLevel(); // Level wir mit mehr gegnern neu gestartet.
        }
    }



    private void death() {
        lives--;

        if (lives == 0) {
            pacman.death();
        } else {
            continueLevel();
        }
    }


    private void moveGhosts(Graphics2D g2d) {

        int pos;
        int count;

        for (int i = 0; i < N_GHOSTS; i++) {
            if (ghost_x[i] % BLOCK_SIZE == 0 && ghost_y[i] % BLOCK_SIZE == 0) {
                pos = ghost_x[i] / BLOCK_SIZE + N_BLOCKS * (int) (ghost_y[i] / BLOCK_SIZE);
                // Geister Position in Pixeln muss ein Vielfaches der Blockgröße sein damit
                // seine Position aktualisiert wird.

                count = 0;

                if ((screenData[pos] & 1) == 0 && ghost_dx[i] != 1) { // Darf sich der Geist nach links Bewegen
                    dx[count] = -1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 2) == 0 && ghost_dy[i] != 1) { // Darf sich der Geist nach oben Bewegen
                    dx[count] = 0;
                    dy[count] = -1;
                    count++;
                }

                if ((screenData[pos] & 4) == 0 && ghost_dx[i] != -1) { // Darf sich der Geist nach rechts Bewegen
                    dx[count] = 1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 8) == 0 && ghost_dy[i] != -1) { // Darf sich der Geist nach unten Bewegen
                    dx[count] = 0;
                    dy[count] = 1;
                    count++;
                }

                if (count == 0) { // Falls sich der jeweilige Geist gar nicht bewegen darf.

                    if ((screenData[pos] & 15) == 15) { // Weil er von Hindernissen komplett eingeschlossen ist.
                        ghost_dx[i] = 0;
                        ghost_dy[i] = 0;
                    } else {
                        ghost_dx[i] = -ghost_dx[i];
                        ghost_dy[i] = -ghost_dy[i];
                    }

                } else {

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                    ghost_dx[i] = dx[count]; // es wird eine zufällige Bewegung vorerst ausgewählt.
                    ghost_dy[i] = dy[count];
                }

            }

            ghost_x[i] = ghost_x[i] + (ghost_dx[i] * ghostSpeed[i]); // Die ausgewählte Bewegung wird nun durchgeführt.
            ghost_y[i] = ghost_y[i] + (ghost_dy[i] * ghostSpeed[i]);
            drawGhost(g2d, ghost_x[i] + 1, ghost_y[i] + 1);

            if (pacman_x > (ghost_x[i] - 12) && pacman_x < (ghost_x[i] + 12) // Hit-Detection zwischen Pacman und Geistern
                    && pacman_y > (ghost_y[i] - 12) && pacman_y < (ghost_y[i] + 12) // 12 entspricht der halben Pixelbreite der Modelle.
                    && inGame) {

                dying = true;
            }
        }
    }

    private void drawGhost(Graphics2D g2d, int x, int y) {
        g2d.drawImage(ghost, x, y, this);
    }

    private void movePacman() {

        int pos;
        short ch;

        // Die erste if-Verzweigung liest Pacmans Position aus während seiner konstanten Bewegung aus.
        // Pacmans Position in Pixeln muss ein Vielfaches der Blockgröße sein damit seine Position aktualisiert wird.
        if (pacman_x % BLOCK_SIZE == 0 && pacman_y % BLOCK_SIZE == 0) {
            // Pacmans Position wird als einzelne Zahl zwischen 0 und 224 in "pos" gespeichert.
            pos = pacman_x / BLOCK_SIZE + N_BLOCKS * (int) (pacman_y / BLOCK_SIZE);
            ch = screenData[pos];
            // wenn pacman auf einem Feld mit Sammelstein steht, wird seine Punktzahl um 1 erhöht.
            if ((ch & 16) != 0) {
                screenData[pos] = (short) (ch & 15);
                // Der Sammelstein wird entfernt. ANDing von "ch" mit der Maske 00001111. Es wird also um 16 reduziert.
                score++;
                SoundEffect.play("Audio/pop.wav");
            }

            if (req_dx != 0 || req_dy != 0) { // Falls man eine Richtungs-Eingabe macht
                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0) // Überprüfen op Pacman die Bewegung machen darf.
                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0) // kein rechter Rand im Weg.
                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0) // kein oberer Rand im Weg.
                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
                    pacmand_x = req_dx; // Falls Pacman die Bewegung machen darf, werden seine
                    pacmand_y = req_dy; // Richtungänderungs-Koordinaten aktualisiert.
                }
            }

            // // Die if-Bedingung überprüft ob Pacman eine still stehen sollte.
            if ((pacmand_x == -1 && pacmand_y == 0 && (ch & 1) != 0) // Pacman will gegen einen linken Rand.
                    || (pacmand_x == 1 && pacmand_y == 0 && (ch & 4) != 0) // Pacman will gegen einen rechten Rand.
                    || (pacmand_x == 0 && pacmand_y == -1 && (ch & 2) != 0) // Pacman will gegen einen oberen Rand.
                    || (pacmand_x == 0 && pacmand_y == 1 && (ch & 8) != 0)) { // Pacman will gegen einen unteren Rand.
                pacmand_x = 0; // In all diesen Fällen sollen seine Richtungänderungs-Koordinaten, 0 sein.
                pacmand_y = 0;
            }
        }
        int PACMAN_SPEED = 6;
        pacman_x = pacman_x + PACMAN_SPEED * pacmand_x; // Pacmans Koordinaten werden hier aktualisiert.
        pacman_y = pacman_y + PACMAN_SPEED * pacmand_y;
    }

    private void drawPacman(Graphics2D g2d) {
        // Erstellt die jeweiligen richtungsabhängigen Pacman Bilder
        // g2d.drawImage(left, pacman_x + 1, pacman_y + 1, this)
        // welches Bild und wo es gezeichnet werden soll.
        if (req_dx == -1) {
            g2d.drawImage(left, pacman_x + 1, pacman_y + 1, this);
        } else if (req_dx == 1) {
            g2d.drawImage(right, pacman_x + 1, pacman_y + 1, this);
        } else if (req_dy == -1) {
            g2d.drawImage(up, pacman_x + 1, pacman_y + 1, this);
        } else {
            g2d.drawImage(down, pacman_x + 1, pacman_y + 1, this);
        }
    }

    private void drawMaze(Graphics2D g2d) {

        short i = 0;
        int x, y;

        for (Bean bean : beans) {
            g2d.drawImage(bean.getImage(), bean.getX(), bean.getY(), this);
        }
        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {

                g2d.setColor(new Color(110,80,150));
                g2d.setStroke(new BasicStroke(5));

                if ((levelData[i] == 0)) { // Hindernissblock wird erstellt.
                    g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                }

                if ((screenData[i] & 1) != 0) { // Linker Rand wird erstellt.
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1); //drawLine
                }

                if ((screenData[i] & 2) != 0) {
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y); // Oberer Rand wird erstellt.
                }

                if ((screenData[i] & 4) != 0) { // Rechter Rand wird erstellt.
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 8) != 0) { // Unterrand Rand wird erstellt.
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 16) != 0) { // Weißer Punkt
                    g2d.setColor(new Color(255,255,255));
                    g2d.fillOval(x + 10, y + 10, 6, 6);
                }

                i++;
            }
        }
    }

    private void initGame() {

        lives = 3;
        score = 0;
        initLevel();
        N_GHOSTS = 6;
        currentSpeed = 3;
    }

    private void initLevel() {
        //Die Daten aus levelData werden in screenData reinkopiert.
        int i;
        for (i = 0; i < N_BLOCKS * N_BLOCKS; i++) {
            screenData[i] = levelData[i];
        }
        Timer beanTimer = new Timer(15000, e -> {
            if (beans.isEmpty()) {
                placeBeansRandomly();
            }
        });
        beanTimer.start();

        continueLevel(); //es wird continueLevel() aufgerufen.
    }

    private void continueLevel() {
        // Anfangspositionen der Gegner und von Pacman werden festlegt.
        // Auch deren Geschwindigkeit wird durch Math.random gesetzt.

        int dx = 1;
        int random;

        for (int i = 0; i < N_GHOSTS; i++) {

            ghost_y[i] = 4 * BLOCK_SIZE; // Anfangs-Position der Gegner.
            ghost_x[i] = 4 * BLOCK_SIZE;
            ghost_dy[i] = 0;
            ghost_dx[i] = dx;
            dx = -dx;
            random = (int) (Math.random() * (currentSpeed + 1));

            if (random > currentSpeed) {
                random = currentSpeed;
            }

            ghostSpeed[i] = validSpeeds[random];
        }

        pacman_x = 7 * BLOCK_SIZE;  // Anfangs-Position von Pacman
        pacman_y = 11 * BLOCK_SIZE;
        pacmand_x = 0;	// Pacman's Bewegungsrichtung zu Beginn des Spiels. Wird zu Beginn, also vor
        pacmand_y = 0;  // Pacman's Bewegungsrichtung zu Beginn des Spiels. Wird zu Beginn, also vor
        req_dx = 0;		// Diese Variablen werden durch die Pfeiltasten gesteuert und zu Beginn auf 0 gesetzt.
        req_dy = 0;
        dying = false;
    }
    /*
    paintComponent() ist eine Methode der Klasse JPanel (Subklasse von JComponent).
    paintComponent() ist eine Methode in Java Swing, die in Komponenten-klassen wie JPanel überschrieben wird,
    um die visuelle Darstellung der Komponente zu steuern.
    paintComponent() muss nicht extra aufgerufen werden, sondern wird automatisch vom Framework aufgerufen.
    */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Parameter "g" wird automatisch vom Framework übergeben solbald die Komponente dargestellt werden muss.
        // In der Regel rufst du zuerst die super.paintComponent(g)-Methode
        // auf, um sicherzustellen, dass die Standardzeichnung der übergeordneten Klasse durchgeführt wird.

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);

        drawMaze(g2d);
        drawScore(g2d);

        if (inGame) {
            playGame(g2d);
        } else {
            showIntroScreen(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
        // sync() ist eine Methode von Toolkit, die die Darstellung von Grafiken synchronisiert.
        // Dies bedeutet, dass alle ausstehenden Zeichenoperationen auf dem Bildschirm sofort durchgeführt werden.
        g2d.dispose(); // Objekt entfernen
    }


    //controls
    class TAdapter extends KeyAdapter {
        /*
        Die Klasse KeyAdapter bietet leere (nichts tuende) Standardimplementierungen für alle Methoden des
        KeyListener, sodass du nur diejenigen überschreiben musst, für die du spezifische Aktionen implementieren möchtest.
        Bei: class MyKeyListener implements KeyListener müsste man alle anderen Funktionen ebenfalls implementieren.
        */
        @Override
        public void keyPressed(KeyEvent e) {
            // KeyEvent ist eine Klasse in Java, die Teil des java.awt.event-Pakets ist.
            int key = e.getKeyCode();
            // Die Methode getKeyCode() in KeyEvent gibt den Tastencodes des betreffenden Ereignisses zurück.
            // Der Rückgabewert ist ein ganzzahliger Wert, der der jeweiligen Taste entspricht.
            if (inGame) {
                if (key == KeyEvent.VK_LEFT) {
                    req_dx = -1;
                    req_dy = 0;
                } else if (key == KeyEvent.VK_RIGHT) {
                    req_dx = 1;
                    req_dy = 0;
                } else if (key == KeyEvent.VK_UP) {
                    req_dx = 0;
                    req_dy = -1;
                } else if (key == KeyEvent.VK_DOWN) {
                    req_dx = 0;
                    req_dy = 1;
                } else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
                    inGame = false;
                    //GameOver = true;
                }
            } else {
                // mit der Leertaste wird das Spiel gestartet.
                if (key == KeyEvent.VK_SPACE) {
                    inGame = true;
                    initGame(); // Der Start des Spiels erfolgt durch die Methode "initGame()".
                }
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}
