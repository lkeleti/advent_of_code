package day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DisplayController {

    public int countDigits() {
        List<String> lines = readFile(Path.of("src/main/resources/day08.txt"));
        List<Segment> segments = processData(lines);
        return 0;
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

    private List<Segment> processData(List<String> lines) {
        List<Segment> segments = new ArrayList<>();

        for (String line : lines) {
            String firstHalf[] = line.split("|")[0].split(" ");
            String secondHalf[] = line.split("|")[0].split(" ");
            List<String> digits = new ArrayList<>();
            List<String> datas = new ArrayList<>();
            for (String digit: firstHalf) {
                digits.add(digit);
            }
            for (String data: secondHalf) {
                datas.add(data);
            }
            segments.add(new Segment(digits,datas));
        }
        return segments;
    }
}
