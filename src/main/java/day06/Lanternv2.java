package day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Lanternv2 {
    public long guessNumberOfFish() {
        List<String> lines = readFile(Path.of("src/main/resources/day06.txt"));
        long[] fishes = processData(lines);
        return simulateDays(fishes, 256);
    }

    private long simulateDays(long[] fishes, int cycle) {
        for (int i = 0; i < cycle; i++) {
            long tmp = fishes[0];
            fishes[0] = fishes[1];
            fishes[1] = fishes[2];
            fishes[2] = fishes[3];
            fishes[3] = fishes[4];
            fishes[4] = fishes[5];
            fishes[5] = fishes[6];
            fishes[6] = fishes[7] + tmp;
            fishes[7] = fishes[8];
            fishes[8] = tmp;
        }
        long total = 0;
        for (long i : fishes){
            total += i;
        }
        return total;
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

    private long[] processData(List<String> lines) {
        long[] fishes = new long[9];
        for (String line : lines) {
            String numbers[] = line.split(",");
            for (String number: numbers) {
                fishes[Integer.parseInt(number)] += 1;
            }
        }
        return fishes;
    }
//3,4,3,1,2
//5,3,2,2,1,1,4,1,5,5,1,3,1,5,1,2,1,4,1,2,1,2,1,4,2,4,1,5,1,3,5,4,3,3,1,4,1,3,4,4,1,5,4,3,3,2,5,1,1,3,1,4,3,2,2,3,1,3,1,3,1,5,3,5,1,3,1,4,2,1,4,1,5,5,5,2,4,2,1,4,1,3,5,5,1,4,1,1,4,2,2,1,3,1,1,1,1,3,4,1,4,1,1,1,4,4,4,1,3,1,3,4,1,4,1,2,2,2,5,4,1,3,1,2,1,4,1,4,5,2,4,5,4,1,2,1,4,2,2,2,1,3,5,2,5,1,1,4,5,4,3,2,4,1,5,2,2,5,1,4,1,5,1,3,5,1,2,1,1,1,5,4,4,5,1,1,1,4,1,3,3,5,5,1,5,2,1,1,3,1,1,3,2,3,4,4,1,5,5,3,2,1,1,1,4,3,1,3,3,1,1,2,2,1,2,2,2,1,1,5,1,2,2,5,2,4,1,1,2,4,1,2,3,4,1,2,1,2,4,2,1,1,5,3,1,4,4,4,1,5,2,3,4,4,1,5,1,2,2,4,1,1,2,1,1,1,1,5,1,3,3,1,1,1,1,4,1,2,2,5,1,2,1,3,4,1,3,4,3,3,1,1,5,5,5,2,4,3,1,4
}
