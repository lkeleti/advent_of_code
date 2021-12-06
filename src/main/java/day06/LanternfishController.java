package day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LanternfishController {
    public int guessNumberOfFish() {
        List<String> lines = readFile(Path.of("src/main/resources/day06.txt"));
        List<Integer> fishes = processData(lines);
//256
        return simulateDays(fishes, 80);
    }

    private int simulateDays(List<Integer> fishes, int numberOfDays) {
        for (int i = 0; i < numberOfDays; i++) {
            List<Integer> newFishes = new ArrayList<>();
            for (int j = 0; j < fishes.size(); j++) {
                fishes.set(j, fishes.get(j)-1);
                if (fishes.get(j) == -1) {
                    fishes.set(j,6);
                    newFishes.add(8);
                }
            }
            fishes.addAll(newFishes);
        }
        writeToFile(fishes);
        return fishes.size();
    }

    private void writeToFile(List<Integer> fishes) {
        List<String> fishString = new ArrayList<>();
        String numbers = "";
        for (Integer fish: fishes) {
            fishString.add(fish.toString());
        }
        try {
            Files.write(Path.of("src/main/resources/day0601.txt"),fishString);
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot write file!", ioe);
        }
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

    private List<Integer> processData(List<String> lines) {
        List<Integer> fishes = new ArrayList<>();
        for (String line : lines) {
            String numbers[] = line.split(",");
            for (String number: numbers) {
                fishes.add(Integer.parseInt(number));
            }
        }
        return fishes;
    }
}
