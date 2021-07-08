package Graphics;

import java.awt.*;
import java.io.Serializable;

public class LinePoint implements Serializable {
    private Color color;
    private int x1;

    private int y1;


    public LinePoint(int x1,int y1,Color color){
        this.x1 = x1;

        this.y1 = y1;

        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }
}

