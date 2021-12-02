package day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SonarController {
    private List<Integer> listOfDepth = new ArrayList<>();

    public void readData(Path path) {
        List<String> lines = readFile(path);
        for (String line : lines) {
            listOfDepth.add(Integer.parseInt(line));
        }
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

    public int countLargerThanPrev() {
        int counter = 0;
        for (int i = 1; i < listOfDepth.size(); i++) {
            if(listOfDepth.get(i-1) < listOfDepth.get(i)) {
                counter++;
            }
        }
        return counter;
    }
}
