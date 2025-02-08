package org.cis1200.snake;
import java.awt.*;

public class RedApple extends Apple {

    private final Color color;
    public RedApple(int courtWidth, int courtHeight) {
        super(courtWidth, courtHeight);
        color = Color.RED;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }

    public void changePos() {
        this.setPx((int) (Math.random() * 400));
        this.setPy((int) (Math.random() * 400));
    }




    public void grow(Snake snake) {
        if (snake.size() == 1) {
            growShort(snake);
        } else {
            growLong(snake);
        }
    }

    private void growShort(Snake snake) {
        if (snake.tail().getVy() > 0) {
            for (int i = 0; i < 8; i++) {
                snake.add(new Square(snake.tail().getPx(), snake.tail().getPy() - 15,
                        snake.tail().getVx(), snake.tail().getVy(), snake.tail().getWidth(),
                        snake.tail().getHeight(), Color.GREEN));
            }
            return;
        } else if (snake.tail().getVy() < 0) {
            for (int i = 0; i < 8; i++) {
                snake.add(new Square(snake.tail().getPx(), snake.tail().getPy() + 15,
                        snake.tail().getVx(), snake.tail().getVy(), snake.tail().getWidth(),
                        snake.tail().getHeight(), Color.GREEN));
            }
            return;
        } else if (snake.tail().getVx() < 0) {
            for (int i = 0; i < 8; i++) {
                snake.add(new Square(snake.tail().getPx() + 15, snake.tail().getPy(),
                        snake.tail().getVx(), snake.tail().getVy(), snake.tail().getWidth(),
                        snake.tail().getHeight(), Color.GREEN));
            }
            return;
        } else if (snake.tail().getVx() > 0) {
            for (int i = 0; i < 8; i++) {
                snake.add(new Square(snake.tail().getPx() - 15, snake.tail().getPy(),
                        snake.tail().getVx(), snake.tail().getVy(), snake.tail().getWidth(),
                        snake.tail().getHeight(), Color.GREEN));
            }
            return;
        }
    }

    private void growLong(Snake snake) {
        if (snake.get(snake.size() - 1).getPy() < snake.get(snake.size() - 2).getPy()) {
            for (int i = 0; i < 8; i++) {
                snake.add(new Square(snake.tail().getPx(), snake.tail().getPy() - 15,
                        snake.tail().getVx(), snake.tail().getVy(), snake.tail().getWidth(),
                        snake.tail().getHeight(), Color.GREEN));
            }
            return;
        } else if (snake.get(snake.size() - 1).getPy() > snake.get(snake.size() - 2).getPy()) {
            for (int i = 0; i < 8; i++) {
                snake.add(new Square(snake.tail().getPx(), snake.tail().getPy() + 15,
                        snake.tail().getVx(), snake.tail().getVy(), snake.tail().getWidth(),
                        snake.tail().getHeight(), Color.GREEN));
            }
            return;
        } else if (snake.get(snake.size() - 1).getPx() > snake.get(snake.size() - 2).getPx()) {
            for (int i = 0; i < 8; i++) {
                snake.add(new Square(snake.tail().getPx() + 15, snake.tail().getPy(),
                        snake.tail().getVx(), snake.tail().getVy(), snake.tail().getWidth(),
                        snake.tail().getHeight(), Color.GREEN));
            }
            return;
        } else if (snake.get(snake.size() - 1).getPx() < snake.get(snake.size() - 2).getPx()) {
            for (int i = 0; i < 8; i++) {
                snake.add(new Square(snake.tail().getPx() - 15, snake.tail().getPy(),
                        snake.tail().getVx(), snake.tail().getVy(), snake.tail().getWidth(),
                        snake.tail().getHeight(), Color.GREEN));
            }
            return;
        }
    }
}
