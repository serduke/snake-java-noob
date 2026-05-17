package ru.serduke.snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Snake implements KeyListener {

    private ArrayList<SnakeSection> snakeSections = new ArrayList<>();
    private SnakeDirections direction;

    public void setDirection(SnakeDirections direction) {
        this.direction = direction;
    }

    public Snake() {
        this.snakeSections.add(0, new SnakeSection(60, 60));
    }

    public ArrayList<SnakeSection> getSnakeSections() {
        return snakeSections;
    }

    public void setSnakeSections(ArrayList<SnakeSection> snakeSections) {
        this.snakeSections = snakeSections;
    }

    public void move(){
        if (direction == SnakeDirections.DOWN){
            moveInDirection(0,30);
        } else if (direction == SnakeDirections.UP) {
            moveInDirection(0, -30);
        } else if (direction == SnakeDirections.LEFT) {
            moveInDirection(-30,0);
        } else if (direction == SnakeDirections.RIGHT) {
            moveInDirection(30, 0);
        }

    }

    private void moveInDirection(int xMoving, int yMoving){
        SnakeSection snakeHead = snakeSections.get(0);
        Game.grid[snakeHead.getX()][snakeHead.getY()] = 0;
        SnakeSection snakeNewHead = new SnakeSection(snakeHead.getX()+xMoving, snakeHead.getY()+yMoving);
        snakeSections.set(0,snakeNewHead);

    }

    public void addSection(SnakeSection section){
        this.snakeSections.add(section);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP){
            setDirection(SnakeDirections.UP);
        } else if (e.getExtendedKeyCode() == KeyEvent.VK_RIGHT) {
            setDirection(SnakeDirections.RIGHT);
        } else if (e.getExtendedKeyCode() == KeyEvent.VK_LEFT) {
            setDirection(SnakeDirections.LEFT);
        } else if (e.getExtendedKeyCode() == KeyEvent.VK_DOWN) {
            setDirection(SnakeDirections.DOWN);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
