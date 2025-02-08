package org.cis1200.snake;

import org.junit.jupiter.api.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * You can use this file (and others) to test your
 * implementation.
 */

public class GameTest {

    @Test
    public void test() {
        assertNotEquals("CIS 120", "CIS 160");
    }

    @Test
    public void testSnakeConstructor() {
        Snake snake = new Snake(500, 500, Color.GREEN);
        assertEquals(1, snake.size());
    }

    @Test
    public void testRemove() {
        Snake snake = new Snake(500, 500, Color.GREEN);
        snake.remove();
        assertEquals(0, snake.size());
    }

    @Test
    public void testAddZero() {
        Snake snake = new Snake(500, 500, Color.GREEN);
        snake.remove();
        snake.add(new Square(50, 50, 0, 0, 500, 500, Color.GREEN));
        assertEquals(1, snake.size());
    }

    @Test
    public void testRedGrow() {
        Snake snake = new Snake(500, 500, Color.GREEN);
        RedApple rap = new RedApple(500, 500);
        snake.setVx(6);
        rap.grow(snake);
        assertEquals(9, snake.size());
        snake.setVy(6);
        rap.grow(snake);
        assertEquals(17, snake.size());
        snake.setVx(-6);
        rap.grow(snake);
        assertEquals(25, snake.size());
        snake.setVy(-6);
        rap.grow(snake);
        assertEquals(33, snake.size());
    }

    @Test
    public void testYellowGrow() {
        Snake snake = new Snake(500, 500, Color.GREEN);
        YellowApple yap = new YellowApple(500, 500);
        snake.setVx(6);
        yap.grow(snake);
        assertEquals(5, snake.size());
        snake.setVy(6);
        yap.grow(snake);
        assertEquals(9, snake.size());
        snake.setVx(-6);
        yap.grow(snake);
        assertEquals(13, snake.size());
        snake.setVy(-6);
        yap.grow(snake);
        assertEquals(17, snake.size());
    }

    @Test
    public void testPurpleGrow() {
        Snake snake = new Snake(500, 500, Color.GREEN);
        PurpleApple pap = new PurpleApple(500, 500);
        pap.grow(snake);
        assertEquals(0, snake.size());
    }

    @Test
    public void testHitsWall() {
        Snake snake = new Snake(500, 500, Color.GREEN);
        snake.setPx(500);
        assertTrue(snake.hitsWall());
        snake.setPx(100);
        snake.setPy(500);
        assertTrue(snake.hitsWall());
        snake.setPy(100);
        snake.setPx(-3);
        assertTrue(snake.hitsWall());
        snake.setPx(100);
        snake.setPy(-3);
        assertTrue(snake.hitsWall());
    }

    @Test
    public void testMove() {
        Snake snake = new Snake(500, 500, Color.GREEN);
        snake.setVx(6);
        snake.move();
        assertEquals(56, snake.head().getPx());
    }

    @Test
    public void testMoveMultipleSquares() {
        Snake snake = new Snake(500, 500, Color.GREEN);
        snake.setVx(6);
        snake.move();
        Square sq = new Square(snake.tail().getPx(), snake.tail().getPy() - 15,
                snake.tail().getVx(), snake.tail().getVy(), snake.tail().getWidth(),
                snake.tail().getHeight(), Color.GREEN);
        snake.add(sq);
        snake.move();
        assertEquals(56, sq.getPx());
        assertEquals(62, snake.head().getPx());
    }

}
