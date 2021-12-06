package day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class HydrothermalController {
    public int findLeastTwo(boolean needDiagonal) {
        List<String> lines = readFile(Path.of("src/main/resources/coordinatesinput.txt"));
        List<Line> linesOfHydro = processData(lines);
        int x = findMaxX(linesOfHydro) + 1;
        int y = findMaxY(linesOfHydro) + 1;
        int[][] table = new int[x][y];
        table = fillTableWithZeros(table);
        table = drawLines(linesOfHydro, table, needDiagonal);
        int num = countMoreThanTwo(table);
        //drawTable(table);
        return num;
    }

    private void drawTable(int[][] table) {
        for (int i = 0; i < table[0].length; i++) {
            String line = "";
            for (int j = 0; j < table.length; j++) {
                line += table[i][j];
            }
            System.out.println(line);
        }
    }

    private int countMoreThanTwo(int[][] table) {
        int count = 0;
        for (int i = 0; i < table[0].length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (table[i][j] > 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private int[][] drawLines(List<Line> linesOfHydro, int[][] table, boolean needDiagonal) {
        for (Line line : linesOfHydro) {
            if (line.isHorisontal()) {
                table = drawHorizontal(table, line);
            } else {
                if (line.isVertical()) {
                    table = drawVertical(table, line);
                } else {
                    if (needDiagonal) {
                        table = drawDiagonal(table, line);
                    }
                }
            }
        }
        return table;
    }

    private int[][] drawDiagonal(int[][] table, Line line) {
        int x1 = line.getStart().getxCoord();
        int x2 = line.getEnd().getxCoord();
        int y1 = line.getStart().getyCoord();
        int y2 = line.getEnd().getyCoord();

        int dx = x2 - x1;
        int dy = y2 - y1;

        if (x1 < x2) {
            for (int x = x1; x < x2 + 1; x++) {
                int y = y1 + dy * (x - x1) / dx;
                table[x][y] += 1;
            }

        }

        if (x1 > x2) {
            for (int x = x2; x < x1 + 1; x++) {
                int y = y1 + dy * (x - x1) / dx;
                table[x][y] += 1;
            }

        }
        return table;
    }

    private int[][] drawVertical(int[][] table, Line line) {
        int x1 = line.getStart().getxCoord();
        int x2 = line.getEnd().getxCoord();
        int y = line.getStart().getyCoord();
        int start = Math.min(x1, x2);
        int end = Math.max(x1, x2);
        for (int i = start; i < end + 1; i++) {
            table[i][y] += 1;
        }
        return table;
    }

    private int[][] drawHorizontal(int[][] table, Line line) {
        int y1 = line.getStart().getyCoord();
        int y2 = line.getEnd().getyCoord();
        int x = line.getStart().getxCoord();
        int start = Math.min(y1, y2);
        int end = Math.max(y1, y2);
        for (int i = start; i < end + 1; i++) {
            table[x][i] += 1;
        }
        return table;
    }


    private int[][] fillTableWithZeros(int[][] table) {
        for (int i = 0; i < table[0].length; i++) {
            for (int j = 0; j < table.length; j++) {
                table[i][j] = 0;
            }
        }
        return table;
    }

    private int findMaxX(List<Line> linesOfHydro) {
        int maxX = 0;
        for (Line line : linesOfHydro) {
            if (line.getStart().getxCoord() > maxX) {
                maxX = line.getStart().getxCoord();
            }
            if (line.getEnd().getxCoord() > maxX) {
                maxX = line.getEnd().getxCoord();
            }
        }
        return maxX;
    }

    private int findMaxY(List<Line> linesOfHydro) {
        int maxY = 0;
        for (Line line : linesOfHydro) {
            if (line.getStart().getyCoord() > maxY) {
                maxY = line.getStart().getyCoord();
            }
            if (line.getEnd().getyCoord() > maxY) {
                maxY = line.getEnd().getyCoord();
            }
        }
        return maxY;
    }

    private List<String> readFile(Path path) {
        List<String> lines;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file!", ioe);
        }
        return lines;
    }

    private List<Line> processData(List<String> lines) {
        List<Line> linesOfHydro = new ArrayList<>();
        for (String line : lines) {
            String half[] = line.split(" -> ");
            linesOfHydro.add(new Line(getPointFromString(half[0]), getPointFromString(half[1])));
        }
        return linesOfHydro;
    }

    private Point getPointFromString(String line) {
        String lineArray[] = line.split(",");
        int x = Integer.parseInt(lineArray[0]);
        int y = Integer.parseInt(lineArray[1]);
        return new Point(x, y);
    }
}
