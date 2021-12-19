package day17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LauncherController {
    private int startX;
    private int endX;
    private int startY;
    private int endY;
    private List<Coordinate> positions = new ArrayList<>();
    /*
    The probe's x position increases by its x velocity.
    The probe's y position increases by its y velocity.
    Due to drag, the probe's x velocity changes by 1 toward the value 0; that is, it decreases by 1 if it is greater than 0, increases by 1 if it is less than 0, or does not change if it is already 0.
    Due to gravity, the probe's y velocity decreases by 1
    target area: x=209..238, y=-86..-59 -> 1443 too low
    target area: x=20..30, y=-10..-5 max height: 45
     */
    public void calcInitParams(){
        List<String> lines = readFile(Path.of("src/main/resources/day17.txt"));
        readFromLines(lines);
        //for (int i = -2*endX; i < 2*endX; i++) {
            //for (int j = -2*endY; j > 2*endY; j--) {
        for (int i = 0; i < 2000; i++) {
            for (int j = 2000; j > -2000; j--) {
                if (simulate(i,j)) {
                    System.out.println(i + ", " + j);
                }
            }
        }
        Coordinate highest = positions.get(0);
        for (Coordinate p : positions) {
            if (p.getMaxHeight() > highest.getMaxHeight()) {
                highest = p;
            }
        }
        System.out.println(highest);
        System.out.println(positions.size());
    }

    private boolean simulate(int x, int y) {
        int xV = x;
        int yV = y;
        x -= xV;
        y -= yV;

        int maxY = Integer.MIN_VALUE;

        for (int i = 0; i < 1000; i++) {
            x += xV;
            if (xV > 0) {
                xV--;
            }
            if (xV < 0) {
                xV++;
            }
            y += yV;
            yV--;

            if (y > maxY) {
                maxY = y;
            }
            if (checkCoords(x, y)) {
                positions.add(new Coordinate(x,y,maxY));
                return true;
            }
        }
        return false;
    }

    private boolean checkCoords(int x, int y) {
        if (x >= startX && x <= endX && y >= startY && y <= endY) {
            return true;
        }
        return false;
    }
    private void readFromLines(List<String> lines) {
        String datas = lines.get(0).substring(15, lines.get(0).length());
        int pos = datas.indexOf('.');
        startX = Integer.parseInt(datas.substring(0,pos));
        datas = datas.substring(pos);
        pos = datas.indexOf(',');
        endX = Integer.parseInt(datas.substring(2,pos));
        datas = datas.substring(pos);
        pos = datas.indexOf('.');
        startY = Integer.parseInt(datas.substring(4,pos));
        datas = datas.substring(pos);
        endY = Integer.parseInt(datas.substring(2));
    }

    private List<String> readFile(Path path) {
        List<String> lines;
        try {
            lines = Files.readAllLines(path);
            return lines;
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file!", ioe);
        }
    }
}
