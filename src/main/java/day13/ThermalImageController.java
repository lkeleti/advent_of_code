package day13;

import day12.Node;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThermalImageController {

    List<String> foldValues = new ArrayList<>();

    public long countDots() {
        List<String> lines = readFile(Path.of("src/main/resources/day13.txt"));
        boolean[][] chars = readFromLines(lines);

        for (int i = 0; i < foldValues.size(); i++) {
            if (foldValues.get(i).charAt(0) == 'y') {
                //fold up
                int foldLine = Integer.parseInt(foldValues.get(i).substring(2));
                chars = foldUp(chars, foldLine);
            } else {
                //fold left
                int foldLine = Integer.parseInt(foldValues.get(i).substring(2));
                chars = foldLeft(chars, foldLine);
            }
            System.out.println("Pontok száma: " + countNumberOfDots(chars));
        }
        char[][] dots = recodeArray(chars);
        for (int i = 0; i < dots.length; i++) {
            System.out.println(Arrays.toString(dots[i]));
        }
        return 0;
    }

    private char[][] recodeArray(boolean[][] newChars) {
        char[][] dots = new char[newChars[0].length][newChars.length];
        for (int i = 0; i < newChars.length; i++) {
            for (int j = 0; j < newChars[0].length; j++) {
                dots[j][i] = newChars[i][j] ? '#' : ' ';
            }
        }
        return dots;
    }

    private int countNumberOfDots(boolean[][] newChars) {
        int count = 0;
        for (int i = 0; i < newChars.length; i++) {
            for (int j = 0; j < newChars[0].length; j++) {
                if (newChars[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean[][] foldLeft(boolean[][] chars, int foldLine) {
        System.out.println("Hajtás: " + foldLine);
        System.out.println("Méret: " + chars.length + " x " + chars[0].length);

        boolean[][] newChars;
        newChars = new boolean[foldLine][chars[0].length];

            for (int i = 0; i < foldLine; i++) {
                for (int j = 0; j < chars[0].length; j++) {
                    newChars[i][j] = chars[i][j];
                    newChars[i][j] = newChars[i][j] || chars[chars.length - i - 1][j];
                }
            }
        return newChars;
    }

    private boolean[][] foldUp(boolean[][] chars, int foldLine) {
        System.out.println("Hajtás: " + foldLine);
        System.out.println("Méret: " + chars.length + " x " + chars[0].length);

        boolean[][] newChars;
        newChars = new boolean[chars.length][foldLine];

        if ((chars[0].length-1) /2 == foldLine) {
            for (int i = 0; i < chars.length; i++) {
                for (int j = 0; j < foldLine; j++) {
                    newChars[i][j] = chars[i][j];
                    newChars[i][j] = newChars[i][j] || chars[i][chars[0].length - j - 1];
                }
            }
        }
        else {
            for (int i = 0; i < chars.length; i++) {
                for (int j = 0; j < foldLine; j++) {
                    newChars[i][j] = chars[i][j];
                    newChars[i][j] = newChars[i][j] || chars[i][chars[0].length - j -1];
                }
            }
        }
        return newChars;
    }

    private boolean[][] readFromLines(List<String> lines) {
        int maxX = 0;
        int maxY = 0;
        List<Coordinate> coordinates = new ArrayList<>();
        for (String line : lines) {
            if (!line.isEmpty()) {
                if (!line.startsWith("fold along ")) {
                    int x = Integer.parseInt(line.split(",")[0]);
                    int y = Integer.parseInt(line.split(",")[1]);

                    coordinates.add(new Coordinate(x, y));
                    if (x > maxX) {
                        maxX = x;
                    }

                    if (y > maxY) {
                        maxY = y;
                    }
                } else {
                    foldValues.add(line.substring(11, line.length()));
                }
            }
        }
        boolean[][] chars = createArray(coordinates, maxX, maxY+2);
        return chars;
    }

    private boolean[][] createArray(List<Coordinate> coordinates, int maxX, int maxY) {
        boolean[][] chars = new boolean[maxX + 1][maxY + 1];
        for (int i = 0; i <= maxX; i++) {
            for (int j = 0; j <= maxY; j++) {
                chars[i][j] = false;
            }
        }

        for (Coordinate coordinate : coordinates) {
            chars[coordinate.getX()][coordinate.getY()] = true;
        }

        return chars;
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
