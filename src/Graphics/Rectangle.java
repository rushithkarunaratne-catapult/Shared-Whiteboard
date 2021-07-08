package Graphics;

import java.awt.*;
import java.io.Serializable;

public class Rectangle implements Serializable {
    private int x;
    private int y;
    private Color color;
    private int width;
    private int height;

    public Rectangle(int x, int y,int width,int height, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
        this.width = width;
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}

