package Graphics;

import javax.swing.*;
import java.io.Serializable;

public class TextAnywhere implements Serializable {
    private int x;
    private int y;
    private JTextField textField = new JTextField("Text");

    public TextAnywhere(int x,int y){
        this.x = x;
        this.y = y;
        textField.setBounds(x,y,40,40);
    }

    public JTextField getTextField() {
        return textField;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
