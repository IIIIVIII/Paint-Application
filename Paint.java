public class Paint {
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * A program that reads names from a text file, sorts them, then writes them to another text file.
 *
 * <p>Purdue University -- CS18000 -- Fall 2022</p>
 *
 * @author Purdue CS
 * @version July 13, 2023
 */
public class Paint extends JComponent implements Runnable {
    //JFrame frame = new JFrame("Paint Exercise");
    JButton clrButton;
    JButton fillButton;
    JButton eraseButton;
    JButton randomButton;
    JButton hexButton;
    JButton rgbButton;
    JTextField hexText;
    JTextField rText;
    JTextField gText;
    JTextField bText;
    int eraser = 0;
    boolean flag = true;
    Image image; // the canvas
    private Graphics2D graphics2D;  // this will enable drawing
    int curX; // current mouse x coordinate
    int curY; // current mouse y coordinate
    int oldX; // previous mouse x coordinate
    int oldY; // previous mouse y coordinate
    //JButton enterButton; // button to enter information
    JTextField strTextField; // text field for input
    Paint draw;
    JFrame frame = new JFrame("Paint Exercise");
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clrButton) {
                draw.clear();
            }
            if (e.getSource() == fillButton) {
                draw.fill();
            }
            if (e.getSource() == eraseButton) {
                draw.eraser();
            }
            if (e.getSource() == randomButton) {
                draw.random();
            }
            if (e.getSource() == hexButton) {
                if (Integer.parseInt(rText.getText()) > 255 || Integer.parseInt(rText.getText()) < 0) {
                    flag = false;
                    //System.out.println("1");
                }
                if (Integer.parseInt(gText.getText()) > 255 || Integer.parseInt(gText.getText()) < 0) {
                    flag = false;
                    //System.out.println("2");
                }
                if (Integer.parseInt(bText.getText()) > 255 || Integer.parseInt(bText.getText()) < 0) {
                    flag = false;
                    //System.out.println("3");
                }
                if (flag) {
                    String r = rText.getText();
                    String g = gText.getText();
                    String b = bText.getText();
                    draw.hex(r, g, b);
                    //hexText.setText(r + g + b);
                    rText.setText(String.valueOf(255));
                    gText.setText(String.valueOf(255));
                    bText.setText(String.valueOf(255));
                } else {
                    JOptionPane.showMessageDialog(frame, "Not a valid RGB Value"
                            , "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
            if (e.getSource() == rgbButton) {

                //System.out.println(rText.getText());
                if (rText.getText().equals("")) {
                    System.out.println("Work");
                    //rText = new JTextField("0", 5);
                    rText.setText("0");
                }
                if (gText.getText().equals("")) {
                    //gText = new JTextField("0", 5);
                    gText.setText("0");
                }
                if (bText.getText().equals("")) {
                    //bText = new JTextField("0", 5);
                    bText.setText("0");
                }
                if (Integer.parseInt(rText.getText()) > 255 || Integer.parseInt(rText.getText()) < 0) {
                    flag = false;
                    //System.out.println("1");
                }
                if (Integer.parseInt(gText.getText()) > 255 || Integer.parseInt(gText.getText()) < 0) {
                    flag = false;
                    //System.out.println("2");
                }
                if (Integer.parseInt(bText.getText()) > 255 || Integer.parseInt(bText.getText()) < 0) {
                    flag = false;
                    //System.out.println("3");
                }
                if (flag) {
                    String r = rText.getText();
                    String g = gText.getText();
                    String b = bText.getText();
                    draw.rgb(r, g, b);
                    hexText.setText(r + g + b);
                    rText.setText(String.valueOf(255));
                    gText.setText(String.valueOf(255));
                    bText.setText(String.valueOf(255));
                } else {
                    JOptionPane.showMessageDialog(frame, "Not a valid RGB Value"
                            , "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
                /*if (e.getSource() == hexText) {
                    draw.hexText();
                }
                if (e.getSource() == rText) {
                    draw.rText();
                }
                if (e.getSource() == gText) {
                    draw.gText();
                }
                if (e.getSource() == bText) {
                    draw.bText();
                }*/

        }
    };

    public void run() {
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        draw = new Paint();
        content.add(draw, BorderLayout.CENTER);
        clrButton = new JButton("Clear");
        clrButton.addActionListener(actionListener);
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        panel.add(clrButton);
        content.add(panel, BorderLayout.NORTH);
        fillButton = new JButton("Fill");
        panel.add(fillButton);
        content.add(panel, BorderLayout.NORTH);
        fillButton.addActionListener(actionListener);
        eraseButton = new JButton("Erase");
        panel.add(eraseButton);
        content.add(panel, BorderLayout.NORTH);
        eraseButton.addActionListener(actionListener);
        randomButton = new JButton("Random");
        randomButton.addActionListener(actionListener);
        panel.add(randomButton);
        content.add(panel, BorderLayout.NORTH);
        hexButton = new JButton("hexButton");
        hexButton.addActionListener(actionListener);
        panel1.add(hexButton);
        content.add(panel1, BorderLayout.SOUTH);
        rgbButton = new JButton("rgbButton");
        rgbButton.addActionListener(actionListener);
        panel1.add(rgbButton);
        content.add(panel1, BorderLayout.SOUTH);
        hexText = new JTextField("#", 10);
        hexText.addActionListener(actionListener);
        panel1.add(hexText);
        content.add(panel1, BorderLayout.SOUTH);
        rText = new JTextField(5);
        rText.addActionListener(actionListener);
        panel1.add(rText);
        content.add(panel1, BorderLayout.SOUTH);
        gText = new JTextField(5);
        gText.addActionListener(actionListener);
        panel1.add(gText);
        content.add(panel1, BorderLayout.SOUTH);
        bText = new JTextField(5);
        bText.addActionListener(actionListener);
        panel1.add(bText);
        content.add(panel1, BorderLayout.SOUTH);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            //System.out.println("print something");
            image = createImage(getSize().width, getSize().height);
            /* this lets us draw on the image (ie. the canvas)*/
            graphics2D = (Graphics2D) image.getGraphics();
            /* gives us better rendering quality for the drawing lines */
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            /* set canvas to white with default paint color */
            graphics2D.setPaint(Color.white);
            graphics2D.setBackground(Color.white);
            graphics2D.fillRect(0, 0, getSize().width, getSize().height);
            graphics2D.setPaint(Color.black);
            repaint();
        }
        g.drawImage(image, 0, 0, null);

    }

    public void clear() {
        graphics2D.setPaint(Color.white);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        graphics2D.setPaint(Color.black);
        graphics2D.setBackground(Color.white);
        repaint();
    }

    public void fill() {
        graphics2D.setPaint(graphics2D.getColor());
        graphics2D.setBackground(graphics2D.getColor());
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        graphics2D.setColor(Color.black);
        repaint();
    }

    public void eraser() {
        eraser = 1;
    }

    public void random() {
        eraser = 0;
        Random random = new Random();
        //random.nextInt();
        Color color = new Color(random.nextInt());
        graphics2D.setColor(color);
    }

    public void hex(String r, String g, String b) {
        graphics2D.setColor(new Color(Integer.parseInt(r), Integer.parseInt(g),
                Integer.parseInt(b)));
    }

    public void rgb(String r, String g, String b) {
        graphics2D.setPaint(new Color(Integer.parseInt(r), Integer.parseInt(g),
                Integer.parseInt(b)));
    }
        /*public void hexText(){
            JTextField textField = new JTextField();
            //textField.setText("# ");
            textField.setColumns(10);
        }
    public void rText(){
        JTextField textField = new JTextField();
        //textField.setText("# ");
        textField.setColumns(5);
    }
    public void gText(){
        JTextField textField = new JTextField();
        //textField.setText("# ");
        textField.setColumns(5);
    }
    public void bText(){
        JTextField textField = new JTextField();
        //textField.setText("# ");
        textField.setColumns(5);
    }
        public Graphics2D getGraphics2D() {
            return graphics2D;
        }*/

    public Paint() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // set oldX and oldY coordinates to beginning mouse press
                oldX = e.getX();
                oldY = e.getY();
            }
        });


        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // set current coordinates to where mouse is being dragged
                curX = e.getX();
                curY = e.getY();

                // draw the line between old coordinates and new ones
                Stroke stroke = new BasicStroke(5f);
                graphics2D.setStroke(stroke);
                if (eraser == 1) {
                    graphics2D.setColor(Color.white);
                    graphics2D.drawLine(oldX, oldY, curX, curY);
                } else {
                    graphics2D.drawLine(oldX, oldY, curX, curY);
                }
                // refresh frame and reset old coordinates
                repaint();
                oldX = curX;
                oldY = curY;

            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Paint());
    }
}

