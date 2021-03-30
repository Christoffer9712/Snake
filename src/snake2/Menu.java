package snake2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Menu extends JPanel {

    private boolean running = false;
    private GridBagConstraints gbc = new GridBagConstraints();

    public Menu(Dimension d) {
        this.setPreferredSize(d);
        this.setFocusable(true);
        this.setLayout(new GridBagLayout());

        menuButton j = new menuButton("Start");
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(j, gbc);
    }

    private class menuButton extends JButton implements ActionListener {

        String s;

        public menuButton(String s) {
            this.s = s;
            this.setText(s);
            this.addActionListener(this);
            this.setFocusable(false);
        }

        public void actionPerformed(ActionEvent e) {
            running = true;
        }
    }

    public boolean getRunning() {
        return running;
    }
}
