package ru.serduke.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Game extends JFrame implements ActionListener {

    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    protected static int[][] grid;
    private Timer timer;
    private Snake snake;

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

    public void paint(Graphics g){

        if (snake != null) {
            ArrayList<SnakeSection> snakeSections = snake.getSnakeSections();
            snake.move();
            for (SnakeSection section: snakeSections){
                grid[section.getX()][section.getY()] = 1;
            }
        }

        for (int x = 30; x < grid.length - 30; x = x+30){
            for (int y = 30; y < grid[x].length - 30; y = y+30){
                g.drawRect( x, y, 30, 30 );
                if (grid[x][y] == 1){
                    g.fillRect( x, y, 30, 30 );
                } else {
                    g.clearRect(x, y,30,30);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
