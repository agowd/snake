package org.cis1200.snake;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
 * GameCourt
 *
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 */
public class GameCourt extends JPanel {

    // the state of the game logic
    private Snake snake;
    private RedApple redApple;
    private YellowApple yellowApple;
    private PurpleApple purpleApple;
    private boolean playing = false;
    private final JLabel status;

    // Game constants
    public static final int COURT_WIDTH = 500;
    public static final int COURT_HEIGHT = 500;
    public static final int SQUARE_VELOCITY = 6;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 35;

    public static final JLabel PAUSED = new JLabel("Paused");

    public static final JLabel ERROR = new JLabel("ERROR IN SAVING/LOADING GAME");

    public GameCourt(JLabel status) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // The timer is an object which triggers an action periodically with the
        // given INTERVAL. We register an ActionListener with this timer, whose
        // actionPerformed() method is called each time the timer triggers. We
        // define a helper method called tick() that actually does everything
        // that should be done in a single time step.
        Timer timer = new Timer(INTERVAL, e -> tick());
        timer.start(); // MAKE SURE TO START THE TIMER!

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        // This key listener allows the square to move as long as an arrow key
        // is pressed, by changing the square's velocity accordingly. (The tick
        // method below actually moves the square.)
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    snake.setVx(-SQUARE_VELOCITY);
                    snake.setVy(0);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    snake.setVx(SQUARE_VELOCITY);
                    snake.setVy(0);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    snake.setVy(SQUARE_VELOCITY);
                    snake.setVx(0);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    snake.setVy(-SQUARE_VELOCITY);
                    snake.setVx(0);
                }
            }
        });

        this.status = status;

    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
        snake = new Snake(COURT_WIDTH, COURT_HEIGHT, Color.GREEN);
        redApple = new RedApple(COURT_WIDTH, COURT_HEIGHT);
        yellowApple = new YellowApple(COURT_WIDTH, COURT_HEIGHT);
        purpleApple = new PurpleApple(COURT_WIDTH, COURT_HEIGHT);
        playing = true;
        status.setText("Running...");
        requestFocusInWindow();
    }

    public void reset(Snake sn, RedApple red, YellowApple yel, PurpleApple pur) {
        snake = sn;
        redApple = red;
        yellowApple = yel;
        purpleApple = pur;
        playing = true;
        status.setText("Running...");
        requestFocusInWindow();
    }

    public void saveGame(JFrame frame) {
        BufferedWriter wr;
        try {
            wr = new BufferedWriter(new FileWriter("save.txt"));
            for (Square sq : snake.list()) {
                wr.write(sq.getPx() + " ");
                wr.write(sq.getPy() + " ");
                wr.write(sq.getVx() + " ");
                wr.write(sq.getVy() + "");
                wr.newLine();
                wr.flush();
            }
            wr.write(yellowApple.getPx() + " " + yellowApple.getPy());
            wr.newLine();
            wr.write(redApple.getPx() + " " + redApple.getPy());
            wr.newLine();
            wr.write(purpleApple.getPx() + " " + purpleApple.getPy());
            wr.flush();
            wr.close();
            frame.remove(this);
            frame.add(PAUSED);
        } catch (IOException e) {
            frame.remove(this);
            frame.add(ERROR);
            JOptionPane.showMessageDialog(frame, "invalid file");
        }

    }

    public void load(JFrame frame) {
        BufferedReader r;
        try {
            r = new BufferedReader(new FileReader("save.txt"));
            FileLineIterator fli = new FileLineIterator(r);
            snake = new Snake(COURT_WIDTH, COURT_HEIGHT, Color.GREEN);
            snake.remove();
            yellowApple = new YellowApple(COURT_WIDTH, COURT_HEIGHT);
            redApple = new RedApple(COURT_WIDTH, COURT_HEIGHT);
            purpleApple = new PurpleApple(COURT_WIDTH, COURT_HEIGHT);
            int appCount = 0;
            while (fli.hasNext()) {
                String next = fli.next();
                String[] line = next.split(" ");
                if (line.length == 4) {
                    snake.add(new Square(Integer.parseInt(line[0]),
                            Integer.parseInt(line[1]),
                            Integer.parseInt(line[2]),
                            Integer.parseInt(line[3]), COURT_WIDTH, COURT_HEIGHT, Color.GREEN));
                } else if (line.length == 2) {
                    if (appCount == 0) {
                        yellowApple.setPx(Integer.parseInt(line[0]));
                        yellowApple.setPy(Integer.parseInt(line[1]));
                        appCount++;
                    } else if (appCount == 1) {
                        redApple.setPx(Integer.parseInt(line[0]));
                        redApple.setPy(Integer.parseInt(line[1]));
                        appCount++;
                    } else if (appCount == 2) {
                        purpleApple.setPx(Integer.parseInt(line[0]));
                        purpleApple.setPy(Integer.parseInt(line[1]));
                        appCount++;
                    }
                }
            }
            status.setText("Score: " + snake.size());
            frame.remove(PAUSED);
            frame.add(this);
            requestFocusInWindow();
            repaint();
        } catch (IOException e) {
            frame.remove(this);
            frame.add(ERROR);
            JOptionPane.showMessageDialog(frame, "invalid file");
        }
    }


    public void setSnake(Snake in) {
        Snake out = new Snake(COURT_WIDTH, COURT_HEIGHT, Color.GREEN);
        for (Square sq : in.list()) {
            out.add(sq);
        }
        snake = out;
    }

    public Snake getSnake() {
        return snake;
    }

    public RedApple getRedApple() {
        return redApple;
    }

    public YellowApple getYellowApple() {
        return yellowApple;
    }

    public PurpleApple getPurpleApple() {
        return purpleApple;
    }

    /**
     * This method is called every time the timer defined in the constructor
     * triggers.
     */
    void tick() {
        if (playing) {
            // advance the snake
            snake.move();

            // check for the game end conditions
            if (snake.hitsWall() || snake.intersects()) {
                playing = false;
                status.setText("You lose!");
            } else if (snake.size() == 100) {
                playing = false;
                status.setText("You win!");
            }

            // check for snake eating apples
            if (snake.head().intersects(redApple)) {
                redApple.grow(snake);
                updateStatus();
                redApple.changePos();
            } else if (snake.head().intersects(yellowApple)) {
                yellowApple.grow(snake);
                updateStatus();
                yellowApple.changePos();
            } else if (snake.head().intersects(purpleApple)) {
                purpleApple.grow(snake);
                updateStatus();
                purpleApple.changePos();
            }

            // update the display
            repaint();
        }
    }

    private void updateStatus() {
        status.setText("Score: " + snake.size());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.draw(g);
        redApple.draw(g);
        yellowApple.draw(g);
        purpleApple.draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }


}