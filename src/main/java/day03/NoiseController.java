package day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class NoiseController {
    private int[] bits = new int[12];

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
