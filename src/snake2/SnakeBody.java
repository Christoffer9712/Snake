package snake2;

import java.awt.Color;
import java.awt.Graphics;

public class SnakeBody {

    private int xCoord, yCoord, tileSize;
    private Color color;

    public SnakeBody(int xCoord, int yCoord, int tileSize, Color c) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.tileSize = tileSize;
        this.color = c;

    }

    public int getyCoord() {
        return (yCoord);
    }

    public int getxCoord() {
        return (xCoord);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(xCoord * tileSize, yCoord * tileSize, tileSize, tileSize);

        g.setColor(color);
        g.fillRect(xCoord * tileSize + 2, yCoord * tileSize + 2, tileSize - 4, tileSize - 4);

    }

}
