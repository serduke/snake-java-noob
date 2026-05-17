package ru.serduke.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Game extends JFrame implements ActionListener {

    public final int WIDTH = 600;
    public final int HEIGHT = 600;
    public final int SIZE = 30;
    protected static int[][] grid;
    private Timer timer;
    private Snake snake;
    private boolean gameOver = false;

    public Game() {
        setSize(WIDTH, HEIGHT);
        grid = new int[WIDTH][HEIGHT];
        setVisible( true );
        this.timer = new Timer(200, this);
        timer.start();
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    private void checkBorders(SnakeSection head){
        if (head.getX() > WIDTH || head.getX() < SIZE){
            snake.setAlive(false);
        } else if (head.getY() > HEIGHT || head.getY() < SIZE) {
            snake.setAlive(false);
        }
    }

    public void paint(Graphics g){

        if (snake != null) {
            ArrayList<SnakeSection> snakeSections = snake.getSnakeSections();
            snake.move();
            checkBorders(snakeSections.get(0));
            if (gameOver || !snake.isAlive()){
                JPanel gameOver = new JPanel();
                JLabel label = new JLabel("GAME OVER!");
                gameOver.add(label);
                this.add(gameOver);
                setVisible( true );
            }

            for (SnakeSection section: snakeSections){
                grid[section.getX()][section.getY()] = 1;
            }
        }

        for (int x = SIZE; x < grid.length - SIZE; x = x+SIZE){
            for (int y = SIZE; y < grid[x].length - SIZE; y = y+SIZE){
                g.drawRect( x, y, SIZE, SIZE );
                if (grid[x][y] == 1){
                    g.fillRect( x, y, SIZE, SIZE );
                } else {
                    g.clearRect(x, y,SIZE,SIZE);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
