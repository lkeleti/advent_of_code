package day05;

public class Line {
    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public boolean isHorisontal() {
        return start.getxCoord() == end.getxCoord();
    }
    public boolean isVertical() {
        return start.getyCoord() == end.getyCoord();
    }
}
