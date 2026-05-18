package ru.serduke.snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Snake implements KeyListener {

    private ArrayList<SnakeSection> snakeSections = new ArrayList<>();
    private SnakeDirections direction;
    private boolean isAlive = true;

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

    public boolean move(Bunny bunny){
        boolean feed = false;
        SnakeSection fakeBunny = new SnakeSection(bunny.getX(), bunny.getY());
        if (snakeSections.contains(fakeBunny)){
            feed = true;
        }
        if (isAlive()){
            if (direction == SnakeDirections.DOWN){
                moveInDirection(0,30, feed);
            } else if (direction == SnakeDirections.UP) {
                moveInDirection(0, -30, feed);
            } else if (direction == SnakeDirections.LEFT) {
                moveInDirection(-30,0, feed);
            } else if (direction == SnakeDirections.RIGHT) {
                moveInDirection(30, 0, feed);
            }
        }
        return feed;
    }

    private void moveInDirection(int xMoving, int yMoving, boolean feed){
        for (SnakeSection section: snakeSections){
            Game.grid[section.getX()][section.getY()] = 1;
        }
        SnakeSection head = snakeSections.get(snakeSections.size()-1);
        SnakeSection snakeNewHead = new SnakeSection(head.getX()+xMoving, head.getY()+yMoving);
        if (snakeSections.contains(snakeNewHead)){
            this.setAlive(false);
        }
        snakeSections.add(snakeNewHead);
        if (!feed){
            SnakeSection removed = snakeSections.remove(0);
            Game.grid[removed.getX()][removed.getY()] = 0;
        }

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

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
