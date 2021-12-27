package day22;

public class StatusInfo {
    private boolean active= false;
    private int startX;
    private int endX;
    private int startY;
    private int endY;
    private int startZ;
    private int endZ;

    public StatusInfo(boolean active, int startX, int endX, int startY, int endY, int startZ, int endZ) {
        this.active = active;
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
        this.startZ = startZ;
        this.endZ = endZ;
    }

    public boolean isActive() {
        return active;
    }

    public int getStartX() {
        return startX;
    }

    public int getEndX() {
        return endX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndY() {
        return endY;
    }

    public int getStartZ() {
        return startZ;
    }

    public int getEndZ() {
        return endZ;
    }
}
