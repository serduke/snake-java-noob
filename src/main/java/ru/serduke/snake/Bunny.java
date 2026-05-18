package ru.serduke.snake;

public class Bunny {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Bunny(int x, int y) {
        this.x = x;
        this.y = y;
        Game.grid[x][y] = 2;
    }
}
