package org.cis1200.snake;
import java.awt.*;

/**
 * A basic game object starting in the upper left corner of the game court. It
 * is displayed as a square of a specified color.
 */
public class Square extends GameObj {
    public static final int SIZE = 15;
    private int px;
    private int py;
    private int vx;
    private int vy;

    private final Color color;

    /**
     * Note that, because we don't need to do anything special when constructing
     * a Square, we simply use the superclass constructor called with the
     * correct parameters.
     */
    public Square(int px, int py, int vx, int vy, int courtWidth, int courtHeight, Color color) {
        super(vx, vy, px, py, SIZE, SIZE, courtWidth, courtHeight);
        this.px = px;
        this.py = py;
        this.vx = vx;
        this.vy = vy;

        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }


}