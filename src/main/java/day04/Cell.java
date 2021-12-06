package day04;

public class Cell {
    private int value;
    private boolean status;

    public Cell(int value) {
        this.value = value;
        status = false;
    }

    public int getValue() {
        return value;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
