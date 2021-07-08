package Server;

import javax.swing.*;

public class Management  extends JFrame {
    Peers peers = new Peers();

    public Management(){
        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(peers);
        this.setTitle("Management");
        this.setVisible(true);
    }
}
