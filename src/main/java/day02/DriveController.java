package day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DriveController {

    public int readData(Path path) {
        List<String> lines = readFile(path);
        int horizontalPosition = 0;
        int depth = 0;
        for (String line : lines) {
            String direction = line.split(" ")[0];
            int value = Integer.parseInt(line.split(" ")[1]);

            switch (direction) {
                case "forward" :
                    horizontalPosition += value;
                    break;
                case "down" :
                    depth += value;
                    break;
                case "up":
                    depth -= value;
                    break;
                default: throw new IllegalArgumentException("Unknown command!");
            }
        }
        return horizontalPosition * depth;
    }

    public int readDataComplicated(Path path) {
        List<String> lines = readFile(path);
        int horizontalPosition = 0;
        int depth = 0;
        int aim = 0;
        for (String line : lines) {
            String direction = line.split(" ")[0];
            int value = Integer.parseInt(line.split(" ")[1]);

            switch (direction) {
                case "forward" :
                    horizontalPosition += value;
                    depth += (aim * value);
                    break;
                case "down" :
                    //depth += value;
                    aim += value;
                    break;
                case "up":
                    //depth -= value;
                    aim -= value;
                    break;
                default: throw new IllegalArgumentException("Unknown command!");
            }
        }
        return horizontalPosition * depth;
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
