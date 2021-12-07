package day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrabController {

    public double guessSmallestCost() {
        List<String> lines = readFile(Path.of("src/main/resources/day07.txt"));
        int[] numbers = processData(lines);
        int medianTry = (int)median(numbers);
        return countCost(numbers, medianTry);
        //349 355592
    }

    private int countCost(int[] values, int medianTry) {
        int cost = 0;
        for (int value: values) {
            cost+= Math.abs(value-medianTry);
        }
        return cost;
    }

    private double median(int[] values) {
        // sort array
        Arrays.sort(values);
        double median;
        // get count of scores
        int totalElements = values.length;
        // check if total number of scores is even
        if (totalElements % 2 == 0) {
            int sumOfMiddleElements = values[totalElements / 2] +
                    values[totalElements / 2 - 1];
            // calculate average of middle elements
            median = ((double) sumOfMiddleElements) / 2;
        } else {
            // get the middle element
            median = (double) values[values.length / 2];
        }
        return median;
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

    private int[] processData(List<String> lines) {
        int arrayLength = lines.get(0).split(",").length;
        int[] numbers = new int[arrayLength];
        for (String line : lines) {
            String numbersString[] = line.split(",");
            for (int i = 0; i < numbersString.length; i++) {
                numbers[i] = (Integer.parseInt(numbersString[i]));
            }
        }
        return numbers;
    }
}
