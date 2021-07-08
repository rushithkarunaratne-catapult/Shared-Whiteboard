package Graphics;

import java.awt.*;
import java.io.Serializable;

public class Circle implements Serializable {
    private Color color;
    private int x;
    private int y;

    public Circle(int x, int y,Color color){
        this.x = x;
        this.y = y;

        this.color =color;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Color getColor() {
        return color;
    }
}
