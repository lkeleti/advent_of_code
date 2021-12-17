package day17;

public class Coordinate {
    private int posX;
    private int posY;
    private int maxHeight;

    public Coordinate(int posX, int posY, int maxHeight) {
        this.posX = posX;
        this.posY = posY;
        this.maxHeight = maxHeight;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    @Override
    public String toString() {
        return posX + ", " +
                posY + ", " +
                maxHeight;
    }
}
