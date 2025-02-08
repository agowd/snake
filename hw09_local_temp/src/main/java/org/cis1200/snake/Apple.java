package org.cis1200.snake;

import java.awt.*;

public abstract class Apple extends GameObj {

    public static final int SIZE = 15;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;

    private final Color color;



    public Apple(int courtWidth, int courtHeight) {
        super(INIT_VEL_X, INIT_VEL_Y, (int) (Math.random() * 400), (int) (Math.random() * 400),
                SIZE, SIZE, courtWidth, courtHeight);
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


    public abstract void grow(Snake snake);


}
