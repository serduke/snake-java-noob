package ru.serduke.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JFrame implements ActionListener {

    public final int WIDTH = 600;
    public final int HEIGHT = 600;
    public final int SIZE = 30;
    protected static int[][] grid;
    private Timer timer;
    private Snake snake;
    private Bunny bunny;
    private boolean gameOver = false;

    public Game() {
        setSize(WIDTH+SIZE, HEIGHT+SIZE);
        grid = new int[WIDTH][HEIGHT];
        setVisible( true );
        this.timer = new Timer(200, this);
        this.bunny = new Bunny(210, 210);
        timer.start();
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public void setBunny(Bunny bunny) {
        this.bunny = bunny;
    }

    private void checkBorders(SnakeSection head){
        if (head.getX() > WIDTH - SIZE || head.getX() < SIZE){
            snake.setAlive(false);
        } else if (head.getY() > HEIGHT - SIZE || head.getY() < SIZE) {
            snake.setAlive(false);
        }
    }

    public void paint(Graphics g){

        if (snake != null) {
            ArrayList<SnakeSection> snakeSections = snake.getSnakeSections();
            if (snake.move(bunny)) {
                Random random = new Random();
                int rangeX = ((WIDTH-SIZE) - SIZE*2)/SIZE + 1;
                int rangeY = ((HEIGHT-SIZE) - SIZE*2)/SIZE + 1;
                int stepsX = random.nextInt(1,rangeX);
                int stepsY = random.nextInt(1,rangeY);
                int resultX = stepsX * SIZE;
                int resultY = stepsY * SIZE;
                System.out.printf("Bunny: %s %s%n",resultX, resultY);
                System.out.println();
                bunny = new Bunny(resultX, resultY);
            }
            checkBorders(snakeSections.get(snakeSections.size()-1));
            if (gameOver || !snake.isAlive()){
                JPanel gameOver = new JPanel();
                JLabel label = new JLabel("GAME OVER!");
                gameOver.add(label);

                JButton cancelButton = new JButton("Exit");
                cancelButton.setActionCommand("Exit");
                cancelButton.addActionListener(this);
                gameOver.add(cancelButton);
                this.add(gameOver);
                setVisible( true );
                this.timer.stop();

            }

            for (SnakeSection section: snakeSections){
                grid[section.getX()][section.getY()] = 1;
            }
        }

        for (int x = SIZE; x < grid.length - SIZE; x = x+SIZE){
            for (int y = SIZE; y < grid[x].length - SIZE; y = y+SIZE){

                if (grid[x][y] == 2){
                    g.drawOval(x, y, SIZE, SIZE);
                    g.fillOval(x, y, SIZE, SIZE);
                } else {
                    g.drawRect( x, y, SIZE, SIZE );
                }
                if (grid[x][y] == 1){
                    g.fillRect( x, y, SIZE, SIZE );
                } else if (grid[x][y] == 0) {
                    g.clearRect(x, y,SIZE,SIZE);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command != null && command.equals("Exit")){
            System.exit(0);
        }
        repaint();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
