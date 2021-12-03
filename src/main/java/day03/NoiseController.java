package day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class NoiseController {
    private int[] bits = new int[12];

    public int SecondExerciseA(Path path) {
        List<String> lines = readFile(path);
        for (int i =  0 ; i < lines.get(0).length(); i++) {
            lines = findOne(lines, i);
        }
        System.out.println(lines);
        return Integer.parseInt(lines.get(0),2);
    }

    public int SecondExerciseB(Path path) {
        List<String> lines = readFile(path);
        for (int i = 0; i < lines.get(0).length(); i++) {
            lines = findZeros(lines, i);
        }
        System.out.println(lines);
        return Integer.parseInt(lines.get(0),2);
    }

    private List<String> findOne(List<String> numbers, int position) {
        List<String> result = new ArrayList<>();
        int ones = 0;
        int zeros = 0;
        for (String line: numbers) {
            if (line.charAt(position) == '1') {
                ones++;
            }
            else {
                zeros++;
            }
        }

        for (String line: numbers) {
            if (ones >= zeros) {
                if (line.charAt(position) == '1') {
                    result.add(line);
                }
            }
            else {
                if (line.charAt(position) == '0') {
                    result.add(line);
                }
            }
        }
        return result;
    }

    private List<String> findZeros(List<String> numbers, int position) {
        List<String> result = new ArrayList<>();
        int ones = 0;
        int zeros = 0;
        for (String line: numbers) {
            if (line.charAt(position) == '1') {
                ones++;
            }
            else {
                zeros++;
            }
        }


        for (String line: numbers) {
            if (zeros <= ones) {
                if (line.charAt(position) == '0') {
                    result.add(line);
                }
            }
            else {
                if (line.charAt(position) == '1') {
                    result.add(line);
                }
            }
        }
        if (zeros + ones == 1 && result.size() == 0) {
            result.add(numbers.get(0));
        }
        return result;
    }

    public int readData(Path path) {
        List<String> lines = readFile(path);
        int half = 500;
        for (String line: lines) {
            for (int i = 0 ; i < line.length(); i++) {
                if (line.charAt(i) == '1' ) {
                    bits[i] += 1;
                }
            }
        }
        String binaryNumber = "";
        for (int i = 0; i < bits.length; i++) {
            if (bits[i] > half) {
                binaryNumber += "1";
            }
            else {
                binaryNumber += "0";
            }
        }
        int value = Integer.parseInt(binaryNumber,2);
        String inverseBinary = binaryNumber.replace("0","x").replace("1","0").replace("x","1");
        value *= Integer.parseInt(inverseBinary,2);
        return value;
    }

    private List<String> readFile(Path path) {
        List<String> lines;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file", ioe);
        }
        return lines;
    }
}
