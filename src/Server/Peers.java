package Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Peers  extends JPanel {
    private JTextArea jTextArea = new JTextArea();
    private JLabel label = new JLabel("Clients");
    private JTextArea messageBox = new JTextArea();
    private int count = 0;
    private ArrayList<JButton> buttons = new ArrayList<>();
    GridBagConstraints gbc = new GridBagConstraints();

    public Peers(){
        setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(label,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        jTextArea.setEditable(false);
        gbc.gridy = 1;
        //gbc.ipady += 50;
        gbc.gridwidth = 300;
        //gbc.insets = new Insets(1, 1, 1, 1);
        this.add(jTextArea,gbc);


    }

    public void setjTextArea(String text) {
        this.jTextArea.setText(text);
    }

    public void addButton(){
        gbc.gridy++;

        JButton button = new JButton("KICK" + count);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(count);
            }
        });
        buttons.add(button);
        count++;
        this.add(button,gbc);
    }


}
