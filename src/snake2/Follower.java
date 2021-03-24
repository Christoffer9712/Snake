package snake2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Follower {

    private int xCoord = 1, yCoord = 1, size = 1; //coord for snake head
    private ArrayList<SnakeBody> followerbody;
    // private Thread t = new Thread(this);
    private boolean run = false;
    private int ticks = 0;

    public Follower() {
        followerbody = new ArrayList();
        followerbody.add(new SnakeBody(xCoord, yCoord, 10, Color.DARK_GRAY));

    }

    public void tick(int xSnake, int ySnake) {
        this.goTo(xSnake, ySnake);
        followerbody.add(new SnakeBody(xCoord, yCoord, 10, Color.DARK_GRAY));
        followerbody.remove(0);
    }

    public void paint(Graphics g) {
        for (int i = 0; i < followerbody.size(); i++) {
            followerbody.get(0).paint(g);
        }
    }

    public int getX() {
        return (this.xCoord);
    }

    public int getY() {
        return (this.yCoord);
    }

    public void goTo(int xSnake, int ySnake) {
        Random r = new Random();
        int slump = r.nextInt(2);
        if (this.yCoord > ySnake && slump == 1) {
            this.yCoord--;
        } else if (this.yCoord < ySnake && slump == 1) {
            this.yCoord++;
        } else if (this.xCoord < xSnake) {
            this.xCoord++;
        } else if (this.xCoord > xSnake) {
            this.xCoord--;
        }

    }
}
