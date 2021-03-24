package snake2;

import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Snake2 extends JFrame {

    private SnakePanel sp;
    private int WIDTH = 400;
    private int HEIGHT = 300;
    private JTextField text;
    public int level = 0;
    private int highscore = 0;

    private GridBagConstraints gbc = new GridBagConstraints();

    public Snake2() {
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        Menu m = new Menu(new Dimension(WIDTH, HEIGHT)); //Ev missing import???
        this.add(m);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        while (!m.getRunning()) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        this.remove(m);
        sp = new SnakePanel(WIDTH, HEIGHT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 10;
        this.add(sp, gbc);
        Button j = new Button("Stop");
        j.setPreferredSize(new Dimension(WIDTH / 2, 50));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        this.add(j, gbc);

        Button jb = new Button("Restart");
        jb.setPreferredSize(new Dimension(WIDTH / 2, 50));
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        this.add(jb, gbc);
        
        text = new JTextField("Level " + level + "   Highscore " + highscore);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 10;
        this.add(text, gbc);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println(e);
        }
        sp.start1();
        while(true){
            level = sp.level;
            if(level > highscore) highscore = level;
            text.setText("Level " + level + "   Highscore " + highscore);
        }

    }


    private class Button extends JButton implements ActionListener {

        String s;
        int speed = 100;

        public Button(String s) {
            this.s = s;
            this.setText(s);
            this.addActionListener(this);
            this.setFocusable(false);
        }

        public void actionPerformed(ActionEvent e) {
            if (s.equalsIgnoreCase("Stop")) {
                sp.stop1();
            }
            if (s.equalsIgnoreCase("Restart")) {
                try {
                    Thread.sleep(500);
                } catch (Exception f) {
                    System.out.println(f);
                }
                sp.newLevel(0);
                sp.start1();
            }
        }

    }

    public static void main(String[] args) {

        Snake2 s = new Snake2();
    }

}
