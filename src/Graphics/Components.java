package Graphics;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Components  extends JPanel {
    private JButton circleButton=new JButton("Circle");
    private JButton rectangleButton=new JButton("Rectangle");
    private JButton ovalButton = new JButton("TEXT");
    private JButton lineButton = new JButton("Line");
    private ArrayList<JButton> colorButtons = new ArrayList<>();
    private ArrayList<Circle> circlePoints = new ArrayList<>();
    private ArrayList<Rectangle> rectangles = new ArrayList<>();
    private ArrayList<LinePoint> line2DS = new ArrayList<>();
    private ArrayList<TextAnywhere> textAreas = new ArrayList<>();
    private boolean circleChoose = false;
    private boolean recChoose = false;
    private boolean lineChoose1 = false;
    private boolean lineChoose2 = false;
    private boolean textChoose = false;
    private JTextField chatText;
    private JButton submit;
    private boolean checkSubmit = false;
    private JTextArea messageBox = new JTextArea();
    private String messageS;


    //private int colour;
    private int linex;
    private int liney;
    private Color colour = Color.BLACK;

    private int xClick;
    GridBagConstraints gbc = new GridBagConstraints();

    public Components(){

        ArrayList<Color> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.BLACK);
        colors.add(Color.RED);
        colors.add(Color.PINK);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.ORANGE);
        colors.add(Color.CYAN);
        colors.add(Color.DARK_GRAY);
        colors.add(Color.MAGENTA);
        ArrayList<String> scol = new ArrayList<>();
        scol.add("Blue");
        scol.add("Black");
        scol.add("Red");
        scol.add("Pink");
        scol.add("Yellow");
        scol.add("Green");
        scol.add("Orange");
        scol.add("Cyan");
        scol.add("Dark Grey");
        scol.add("Magenta");


        //b.setBounds(20,100,95,30);
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1;
        this.add(circleButton,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(rectangleButton,gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        this.add(ovalButton,gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        this.add(lineButton,gbc);
        line2DS.add(new LinePoint(0,0,colour));

        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(1, 260, 2, 1);
        gbc.anchor = GridBagConstraints.NORTHEAST;

        for (int index = 0; index < 10; index++) {
            JButton temp = new JButton(scol.get(index) );
            colorButtons.add(temp);
            this.add(temp, gbc);
            gbc.gridy++;
        }

        gbc.gridy++;
        gbc.gridx = 3;
        gbc.gridwidth = 5;
        gbc.ipady += 50;
        messageBox.setEditable(false);
        gbc.insets = new Insets(1, 1, 1, 1);
        messageBox.setBackground(Color.GRAY);
        //this.setMessageBox(" ");
        this.add(messageBox,gbc);

        gbc.ipady -= 50;
        gbc.gridy++;
        gbc.gridx = 3;
        gbc.insets = new Insets(1, 1, 1, 1);
        gbc.gridwidth = 5;
        chatText = new JTextField();
        //textField.setText("Fds");
        this.add(chatText,gbc);

        submit = new JButton("Send");
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        this.add(submit,gbc);

        //chatText.setEditable(false);



        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x=e.getX();
                int y=e.getY();

                if (circleChoose) {
                    circlePoints.add(new Circle(x,y,colour));
                }
                else if (recChoose){
                    rectangles.add(new Rectangle(x,y,40,40,colour));
                }
                else if (lineChoose1 && !lineChoose2){
                    line2DS.add(new LinePoint(x,y,colour));
                }
                else if (lineChoose1){
                    line2DS.add(new LinePoint(x,y,colour));
                    lineChoose1 = false;
                    lineChoose2 = false;
                }
                else if (textChoose){
                   /* JTextArea jTextArea = new JTextArea("TEXT");
                    jTextArea.setBounds(x,y,200,50);
                    jTextArea.setEditable(true);
                    textAreas.add(jTextArea);*/
                    JTextField jTextField = new JTextField("TEXT");
                    jTextField.setBounds(x,y,100,50);

                    textAreas.add(new TextAnywhere(jTextField.getX(),jTextField.getY()));
                }
                System.out.println(x+","+y);
                createTextF();
                colorButtonsActions();
                repaint();
                System.out.println(circlePoints.size());
            }
        });

        circleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recChoose = false;
                circleChoose = true;
                lineChoose1 = false;
                lineChoose2 = false;
            }
        });
        rectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recChoose = true;
                circleChoose= false;
                lineChoose1 = false;
                lineChoose2 = false;
            }
        });
        lineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lineChoose1){
                    lineChoose2 = true;
                }
                lineChoose1 = true;
                recChoose = false;
                circleChoose= false;
            }
        });
        ovalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textChoose = true;
                lineChoose1 = false;
                recChoose = false;
                circleChoose= false;
                lineChoose2 = false;

            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                checkSubmit = true;
            }
        });

    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        this.setBackground(Color.WHITE);
        Color color = colour;
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setColor(color);
        //
        /*graphics2D.setStroke(new BasicStroke(20));
        graphics2D.drawLine(0,0,400,400);*/

        for (int i = 0; i < circlePoints.size();i++){
            g2.setColor(circlePoints.get(i).getColor());
            g2.fillOval(circlePoints.get(i).getX(), circlePoints.get(i).getY(), 20, 20);
        }
        for (int i = 0 ; i<rectangles.size();i++){
            g2.setColor(rectangles.get(i).getColor());
            g2.fillRect(rectangles.get(i).getX(),rectangles.get(i).getY(),rectangles.get(i).getWidth(),rectangles.get(i).getHeight());
        }
        for (int i = 0; i<line2DS.size()-1;i++){
            if (i%2 != 0) {
                g2.setColor(line2DS.get(i).getColor());
                g2.drawLine(line2DS.get(i).getX1(), line2DS.get(i).getY1(), line2DS.get(i + 1).getX1(), line2DS.get(i + 1).getY1());
            }
        }
        repaint();
        createTextF();

    }

    public void createTextF(){
        for (int i = 0; i < textAreas.size();i++){
            //textAreas.get(i).setEditable(true);
            this.add(textAreas.get(i).getTextField());
        }
    }

    public void colorButtonsActions(){
        colorButtons.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                colour = Color.BLUE;
            }
        });
        colorButtons.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colour = Color.BLACK;
            }
        });
        colorButtons.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colour = Color.RED;
            }
        });
        colorButtons.get(3).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colour = Color.PINK;
            }
        });
        colorButtons.get(4).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colour = Color.YELLOW;
            }
        });
        colorButtons.get(5).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colour = Color.GREEN;
            }
        });
        colorButtons.get(6).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colour = Color.ORANGE;
            }
        });
        colorButtons.get(7).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colour = Color.CYAN;
            }
        });
        colorButtons.get(8).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colour = Color.GRAY;
            }
        });
        colorButtons.get(9).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colour = Color.MAGENTA;
            }
        });
    }


    public ArrayList<Circle> getCirclePoints() {
        return circlePoints;
    }

    public void setCirclePoints(ArrayList<Circle> circlePoints) {
        int val = 0;
        for (int i = 0; i < circlePoints.size();i++){
            val = 0;
            for (int j = 0; j < this.circlePoints.size() ;j++){
                if (circlePoints.get(i).getX() == this.circlePoints.get(j).getX() && circlePoints.get(i).getY() == this.circlePoints.get(j).getY()){
                    val = 1;
                }
            }
            if (val == 0){
                this.circlePoints.add(circlePoints.get(i));
            }
        }

    }

    public void setRectangles(ArrayList<Rectangle> rectangles) {
        int val = 0;
        for (int i = 0; i < rectangles.size() ; i++){
            val = 0;
            for (int j = 0; j < this.rectangles.size() ;j++){
                if (rectangles.get(i).getX() == this.rectangles.get(j).getX() && rectangles.get(i).getY() == this.rectangles.get(j).getY()){
                    val = 1;
                }
            }
            if (val == 0){
                this.rectangles.add(rectangles.get(i));
            }
        }

    }

    public ArrayList<Rectangle> getRectangles() {
        return rectangles;
    }

    public ArrayList<LinePoint> getLine2DS() {
        return line2DS;
    }

    public void setLine2DS(ArrayList<LinePoint> line2DS) {
        int val = 0;
        for (int i = 0; i < line2DS.size() ; i++){
            val = 0;
            for (int j = 0; j < this.line2DS.size() ;j++){
                if (line2DS.get(i).getX1() == this.line2DS.get(j).getX1() && line2DS.get(i).getY1() == this.line2DS.get(j).getY1()){
                    val = 1;
                }
            }
            if (val == 0){
                this.line2DS.add(line2DS.get(i));
            }
        }
    }

    public void setTextAreas(ArrayList<TextAnywhere> textAreas) {

        int val = 0;
        for (int i = 0; i < textAreas.size() ; i++){
            val = 0;
            for (int j = 0; j < this.textAreas.size() ;j++){
                if (textAreas.get(i).getX() == this.textAreas.get(j).getX() && textAreas.get(i).getY() == this.textAreas.get(j).getY()){
                    val = 1;
                }
            }
            if (val == 0){
                this.textAreas.add(textAreas.get(i));
            }
        }
    }

    public boolean getCheckSubmit(){
        return this.checkSubmit;
    }
    public ArrayList<TextAnywhere> getTextAreas() {
        return textAreas;
    }

    public String getChatText() {
        return chatText.getText();
    }

    public void setCheckSubmit(boolean checkSubmit) {
        this.checkSubmit = checkSubmit;
    }

    public void setMessageBox(String messageBox) {
        System.out.println(messageBox);

        this.messageS = messageBox;
    }

    public String getMessageS() {
        return messageS;
    }

    public void setMessageBoxAct(String s) {
        this.messageBox.setText(s);
    }

    public JTextArea getMessageBox() {
        return messageBox;
    }
}

