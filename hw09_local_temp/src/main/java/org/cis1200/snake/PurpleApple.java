package org.cis1200.snake;
import java.awt.*;

public class PurpleApple extends Apple {

    private final Color color;
    public PurpleApple(int courtWidth, int courtHeight) {
        super(courtWidth, courtHeight);
        color = Color.MAGENTA;
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
        snake.remove();
    }


}
