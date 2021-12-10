package day09;

import day08.Segment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmokeController {

    public int countSmallestPoints() {
        List<String> lines = readFile(Path.of("src/main/resources/day09.txt"));
        int[][] dataArray = processData(lines);
        List<Position> minimums = findMin(dataArray);
        return countCost(minimums, dataArray);
    }

    private int countCost(List<Position> minimums, int[][] dataArray) {
        int sum = 0;
        for (Position p : minimums) {
            sum += dataArray[p.getI()][p.getJ()] + 1;
        }
        return sum;
    }

    private List<Position> findMin(int[][] dataArray) {
        List<Position> minimums = new ArrayList<>();
        int iMax = dataArray.length;
        int jMax = dataArray[0].length;
        for (int i = 0; i < iMax; i++) {
            for (int j = 0; j < jMax; j++) {
                boolean isMin = true;
                if (i > 0 && i < iMax - 1) {
                    if (dataArray[i][j] >= dataArray[i - 1][j] || dataArray[i][j] >= dataArray[i + 1][j]) {
                        isMin = false;
                    }
                }
                if (i == 0 && dataArray[i][j] >= dataArray[i + 1][j]) {
                    isMin = false;
                }
                if (i == iMax - 1 && dataArray[i][j] >= dataArray[i - 1][j]) {
                    isMin = false;
                }
                if (j > 0 && j < jMax - 1) {
                    if (dataArray[i][j] >= dataArray[i][j - 1] || dataArray[i][j] >= dataArray[i][j + 1]) {
                        isMin = false;
                    }
                }
                if (j == 0 && dataArray[i][j] >= dataArray[i][j + 1]) {
                    isMin = false;
                }
                if (j == jMax - 1 && dataArray[i][j] >= dataArray[i][j - 1]) {
                    isMin = false;
                }

                if (isMin) {
                    minimums.add(new Position(i, j));
                }
            }
        }
        return minimums;
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

    private int[][] processData(List<String> lines) {
        int[][] dataArray = new int[lines.size()][lines.get(0).length()];

        for (int i = 0; i < lines.get(0).length(); i++) {
            for (int j = 0; j < lines.size(); j++) {
                dataArray[j][i] = Integer.parseInt(String.valueOf(lines.get(j).toCharArray()[i]));
            }
        }
        return dataArray;
    }
}
