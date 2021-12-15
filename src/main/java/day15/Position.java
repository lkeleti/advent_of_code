package day15;

import java.util.Objects;

public class Position {
    private int xPos;
    private int yPos;
    private int cost;

    public Position(int xPos, int yPos, int cost) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.cost = cost;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object obj) {
        return (xPos == ((Position)obj).getxPos() && yPos == ((Position)obj).getyPos());
    }
}
