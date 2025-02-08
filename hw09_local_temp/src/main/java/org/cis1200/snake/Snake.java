package org.cis1200.snake;

import java.awt.*;
import java.util.*;
import java.util.List;


public class Snake extends GameObj {

    private final List<Square> snake;

    private Square head;

    private Square tail;

    public static final int SIZE = 15;
    public static final int INIT_POS_X = 50;
    public static final int INIT_POS_Y = 50;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;

    private final Color color;

    public Snake(int courtWidth, int courtHeight, Color color) {
        super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);
        snake = new LinkedList<>();
        this.color = color;
        head = new Square(INIT_POS_X, INIT_POS_Y, INIT_VEL_X, INIT_VEL_Y,
                courtWidth, courtHeight, color);
        tail = head;
        snake.add(head);
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        for (Square sq : snake) {
            g.fillRect(sq.getPx(), sq.getPy(), sq.getWidth(), sq.getHeight());
        }
    }


    public void setPx(int px) {
        head.setPx(px);
    }

    public void setPy(int py) {
        head.setPy(py);
    }
    public void setVx(int vx) {
        head.setVx(vx);
    }

    public void setVy(int vy) {
        head.setVy(vy);
    }

    public Square head() {
        return new Square(head.getPx(), head.getPy(), head.getVx(), head.getVy(),
                head.getWidth(), head.getHeight(), Color.GREEN);
    }

    public Square tail() {
        return new Square(tail.getPx(), tail.getPy(), tail.getVx(), tail.getVy(),
                tail.getWidth(), tail.getHeight(), Color.GREEN);
    }


    public void add(Square sq) {
        if (snake.size() == 0) {
            snake.add(sq);
            head = sq;
            tail = sq;
            return;
        }
        snake.add(sq);
        tail = sq;
    }

    public Square get(int i) {
        return snake.get(i);
    }

    public int size() {
        return snake.size();
    }

    public void remove() {
        if (snake.size() == 1) {
            snake.remove(0);
            head = null;
            tail = null;
            return;
        }
        tail = snake.get(snake.size() - 2);
        snake.remove(snake.size() - 1);

    }

    public List<Square> list() {
        return snake;
    }

    public boolean hitsWall() {

        boolean right = head.getPx() + 15 >= 500;
        boolean left = head.getPx() <= 0;
        boolean bottom = head.getPy() <= 0;
        boolean top = head.getPy() + 15 >= 500;

        return left || right || top || bottom;
    }

    public void move() {
        if (snake.size() == 0) {
            return;
        }
        Iterator<Square> iter = snake.iterator();

        int cpx = head.getPx();
        int cpy = head.getPy();
        int cvx = head.getVx();
        int cvy = head.getVy();

        int tpx = 0;
        int tpy = 0;
        int tvx = 0;
        int tvy = 0;

        head.move();
        iter.next();
        Square sq = head;
        while (iter.hasNext()) {
            sq = iter.next();
            tpx = sq.getPx();
            tpy = sq.getPy();
            tvx = sq.getVx();
            tvy = sq.getVy();
            sq.setPx(cpx);
            sq.setPy(cpy);
            sq.setVx(cvx);
            sq.setVy(cvy);
            cpx = tpx;
            cpy = tpy;
            cvx = tvx;
            cvy = tvy;
        }
    }

    public boolean intersects() {
        boolean out = false;
        if (size() == 1) {
            return false;
        }
        for (Square sq : snake) {
            if (sq == snake.get(1) ||
                sq == snake.get(2) ||
                sq == snake.get(3) ||
                sq == snake.get(4)) {
                continue;
            }
            if (sq != head) {
                out = out || head.intersects(sq);
            }
        }
        return out;
    }
}
