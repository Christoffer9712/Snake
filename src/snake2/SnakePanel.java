package snake2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class SnakePanel extends JPanel implements Runnable {

    private int xCoord = 1, yCoord = 1, size = 1; //coord for snake head
    private boolean running = true;
    private boolean right = true, left = false, up = false, down = false;
    private Thread t;
    private int snakeTicks = 0, followerTicks = 0;
    Random rand;
    private ArrayList<SnakeBody> snakebody;
    private ArrayList<Apple> apples;
    private int speed = 4, followerSpeed = 6;
    private boolean sync = true;
    private int WIDTH;
    private int HEIGHT;
    private ArrayList<Follower> follower;
    public int followerNmb = 0;
    public int level = 0;
    

    public SnakePanel(int WIDTH, int HEIGHT) {
        this.HEIGHT = HEIGHT;
        this.WIDTH = WIDTH;
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(new Key());

        snakebody = new ArrayList();
        snakebody.add(new SnakeBody(xCoord, yCoord, 10, Color.GREEN));
        apples = new ArrayList();
        apples = new ArrayList();
        rand = new Random();
        apples.add(new Apple(rand.nextInt(WIDTH / 10), rand.nextInt(HEIGHT / 10), 10));

        follower = new ArrayList();
        for (int i = 0; i < followerNmb; i++) {
            follower.add(new Follower());
        }

    }

    public void paint(Graphics g) {

        this.requestFocus();

        g.clearRect(0, 0, WIDTH, HEIGHT);
        //if(running){
        for (int i = 0; i < WIDTH / 10; i++) {
            g.drawLine(i * 10, 0, i * 10, HEIGHT);
        }
        for (int i = 0; i < HEIGHT / 10; i++) {
            g.drawLine(0, i * 10, WIDTH, i * 10);
        }
        for (int i = 0; i < snakebody.size(); i++) {
            snakebody.get(i).paint(g);
            //     }
        }
        for (int i = 0; i < apples.size(); i++) {
            apples.get(i).paint(g);
            //     }
        }
//        follower.get(0).paint(g);
        for (int i = 0; i < follower.size(); i++) {
            follower.get(i).paint(g);
        }
    }

    public void start1() {
        snakebody = new ArrayList<SnakeBody>();
        this.size = 1;
        running = true;
        xCoord = WIDTH / 20;
        yCoord = HEIGHT / 20;
        t = new Thread(this);
        t.start();

    }

    public void stop1() {
        running = false;

    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return (this.speed);
    }

    public void tick() {
        snakeTicks++;
        followerTicks++;
        if (snakeTicks >= speed) {
            repaint();
            if (right) {
                xCoord++;
            }
            if (left) {
                xCoord--;
            }
            if (up) {
                yCoord--;
            }
            if (down) {
                yCoord++;
            }

            snakebody.add(new SnakeBody(xCoord, yCoord, 10, Color.GREEN));

            for (int i = 0; i < snakebody.size() - 1; i++) {
                if (snakebody.get(i).getxCoord() == xCoord
                        && snakebody.get(i).getyCoord() == yCoord) {
                    stop1();
                }
                for (int j = 0; j < follower.size(); j++) {
                    if (follower.get(j).getX() == snakebody.get(i).getxCoord()
                            && follower.get(j).getY() == snakebody.get(i).getyCoord()) {
                        stop1();
                    }
                }
            }

            if (xCoord > WIDTH / 10 - 1 || yCoord > HEIGHT / 10 - 1 || xCoord < 0 || yCoord < 0) {
                stop1();
            }

            if (apples.size() == 0) {
                rand = new Random();
                apples.add(new Apple(rand.nextInt(WIDTH / 10), rand.nextInt(HEIGHT / 10), 10));

            } else {
                for (int i = 0; i < apples.size(); i++) {
                    if (apples.get(i).getxCoord() == xCoord && apples.get(i).getyCoord() == yCoord) {
                        this.size++;
                        apples.remove(0);
                        if (this.size == 3) {

                            followerNmb++;
                            newLevel(followerNmb);
                        }
                    }
                }
            }

            if (snakebody.size() > size) {

                snakebody.remove(0);

            }

            snakeTicks = 0;
        }

        if (followerTicks >= followerSpeed) {
            for (int i = 0; i < follower.size(); i++) {
                follower.get(i).tick(xCoord, yCoord);
            }
            followerTicks = 0;

        }

    }

    public void run() {
        while (running) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            //repaint();
            tick();
        }

    }

    public void newLevel(int followerNmb) {
        this.followerNmb = followerNmb;
        level = followerNmb;
        this.size = 1;
        snakebody = new ArrayList();
        snakebody.add(new SnakeBody(xCoord, yCoord, 10, Color.GREEN));
        follower = new ArrayList();
        for (int i = 0; i < followerNmb; i++) {
            follower.add(new Follower());
        }

        
    }

    private class Key implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            //          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                left = false;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_LEFT && !right) {
                right = false;
                left = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_UP && !down) {
                right = false;
                left = false;
                up = true;
                down = false;
            }
            if (key == KeyEvent.VK_DOWN && !up) {
                right = false;
                left = false;
                up = false;
                down = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
}
