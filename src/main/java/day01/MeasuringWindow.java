package day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MeasuringWindow {
    private List<Integer> listOfDepth = new ArrayList<>();

    public void readData(Path path) {
        List<String> lines = readFile(path);
        int counter = 1;
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;

        for (String line : lines) {
            switch (counter) {
                case 1:
                    a += Integer.parseInt(line);
                    break;
                case 2:
                    a += Integer.parseInt(line);
                    b += Integer.parseInt(line);
                    break;
                case 3:
                    a += Integer.parseInt(line);
                    b += Integer.parseInt(line);
                    c += Integer.parseInt(line);
                    listOfDepth.add(a);
                    a = 0;
                    break;
                case 4:
                    b += Integer.parseInt(line);
                    c += Integer.parseInt(line);
                    d += Integer.parseInt(line);
                    listOfDepth.add(b);
                    b = 0;
                    break;
                case 5:
                    a += Integer.parseInt(line);
                    c += Integer.parseInt(line);
                    d += Integer.parseInt(line);
                    listOfDepth.add(c);
                    c = 0;
                    break;
                case 6:
                    a += Integer.parseInt(line);
                    b += Integer.parseInt(line);
                    d += Integer.parseInt(line);
                    listOfDepth.add(d);
                    d = 0;
                    counter = 2;
                    break;
            }
            counter++;
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
            if (listOfDepth.get(i - 1) < listOfDepth.get(i)) {
                counter++;
            }
        }
        return counter;
    }
}

