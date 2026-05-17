package ru.serduke.snake;
import javax.swing.*;

public class SnakeApp {
    public static void main(String[] args) {
        Game game = new Game();
        Snake snake = new Snake();
        snake.setDirection(SnakeDirections.DOWN);
        game.setSnake(snake);
        game.addKeyListener(snake);
        game.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }
}
