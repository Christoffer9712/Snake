package snake2;

import java.awt.Color;
import java.awt.Graphics;

public class Apple {

    private int xCoord, yCoord, tileSize;

    public Apple(int xCoord, int yCoord, int tileSize) {

        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.tileSize = tileSize;

    }

    public int getyCoord() {
        return (yCoord);
    }

    public int getxCoord() {
        return (xCoord);
    }

    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(xCoord * tileSize, yCoord * tileSize, tileSize, tileSize);

    }

}
