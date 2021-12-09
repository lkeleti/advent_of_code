package day09;

import day08.Segment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmokeController {

    public int countSmallestPoints() {
        List<String> lines = readFile(Path.of("src/main/resources/day09.txt"));
        int[][] dataArray = processData(lines);
        List<Position> minimums = findMin(dataArray);
        System.out.println(minimums.size());
        return 0;
    }

    private List<Position> findMin(int[][] dataArray) {
        List<Position> minimums = new ArrayList<>();
        for (int i = 0; i < dataArray.length; i++) {
            int localMinimum = dataArray[i][0];
            for (int j = 0; j < dataArray[0].length; j++) {
                if (dataArray[i][j] < localMinimum) {
                    localMinimum = dataArray[i][j];
                } else {
                    //check down
                    for (int k = j; k < dataArray[0].length; k++) {
                        if (dataArray[i][k] < localMinimum) {
                            localMinimum = dataArray[i][k];
                        } else {
                            minimums.add(new Position(i , k - 1));
                            localMinimum = Integer.MAX_VALUE;
                        }
                    }
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
