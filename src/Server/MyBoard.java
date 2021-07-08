package Server;

import Graphics.Components;

import javax.swing.*;
import java.awt.*;

public class MyBoard  extends JFrame {

    Components components = new Components();

    public MyBoard(){
        this.setSize(800,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(components);

        this.setVisible(true);


    }
}

