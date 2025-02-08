package org.cis1200.snake;
// imports necessary libraries for Java swing
import java.awt.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class RunSnake implements Runnable {
    public void run() {

        // Top-level frame in which game components live.
        final JFrame frame = new JFrame("SNAKE");
        frame.setLocation(300, 400);

        final JFrame instructions = new JFrame("Welcome :)");
        instructions.setLocation(300, 200);
        instructions.add(new JPanel(), BorderLayout.SOUTH);
        instructions.add(new JPanel(), BorderLayout.NORTH);
        final JPanel instruction_status = new JPanel();
        instruction_status.add(new JLabel("Snake! \n"));
        instruction_status.add(new JLabel("Use arrow keys to move the snake.\n"));
        instruction_status.add(new JLabel("Red Apples grow the snake by 8.\n"));
        instruction_status.add(new JLabel("Yellow Apples grow the snake by 4.\n"));
        instruction_status.add(new JLabel("Purple Apples make the snake smaller by 1.\n"));
        instruction_status.add(new JLabel("Apples will reappear at random once eaten.\n"));
        instruction_status.add(new JLabel("Get the snake to exactly 100 to win.\n"));
        instruction_status.add(new JLabel("Enjoy!"));
        instructions.add(instruction_status);


        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);

        // Main playing area
        final GameCourt court = new GameCourt(status);
        frame.add(court, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // reset button
        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> court.reset());
        control_panel.add(reset);

        // pause button
        final JButton pause = new JButton("Pause");
        pause.addActionListener(e -> court.saveGame(frame));
        control_panel.add(pause);

        // resume button
        final JButton resume = new JButton("Resume");
        resume.addActionListener(e -> court.load(frame));
        control_panel.add(resume);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        instructions.pack();
        instructions.setVisible(true);

        // Start game
        if (court.getSnake() == null) {
            court.reset();
        } else {
            court.reset(court.getSnake(), court.getRedApple(),
                    court.getYellowApple(), court.getPurpleApple());
        }


    }
}
