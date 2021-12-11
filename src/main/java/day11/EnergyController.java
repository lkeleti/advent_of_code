package day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class EnergyController {
    private int[][] energyPoints;
    private boolean[][] flashStatus;
    private int maxRow;
    private int maxCol;

    public long countFlashes() {
        List<String> lines = readFile(Path.of("src/main/resources/day11.txt"));
        energyPoints = readFromLines(lines);
        maxRow = energyPoints[0].length;
        maxCol = energyPoints.length;
        flashStatus = new boolean[maxRow][maxCol];
        resetFlashes();
        long counter = 0;
        for (int i = 0; i < 195; i++) {
            incEnergy();
            counter += checkEnergyLevel();
            resetFlashes();
        }

        System.out.println(Arrays.deepToString(energyPoints));
        return counter;
    }

    public long findFirstSync() {
        List<String> lines = readFile(Path.of("src/main/resources/day11.txt"));
        energyPoints = readFromLines(lines);
        maxRow = energyPoints[0].length;
        maxCol = energyPoints.length;
        flashStatus = new boolean[maxRow][maxCol];
        resetFlashes();

        long counter = 0;
        long cyclCounter = 0;

        while (!allZeros()) {
            incEnergy();
            counter += checkEnergyLevel();
            resetFlashes();
            cyclCounter++;
        }

        System.out.println(Arrays.deepToString(energyPoints));
        return cyclCounter;

    }

    private boolean allZeros() {
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                if (energyPoints[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private long checkEnergyLevel() {
        long counter = 0;
        boolean was = false;
        do {
            was = false;
            for (int i = 0; i < maxRow; i++) {
                for (int j = 0; j < maxCol; j++) {
                    if (energyPoints[i][j] > 9) {
                        energyPoints[i][j] = 0;
                        flashStatus[i][j] = true;
                        counter++;
                        incNeighbor(i,j);
                        was = true;
                    }
                }
            }
        }
        while (was);
        return counter;
    }

    private void incNeighbor(int x, int y) {
        if (x >= 0 && x <maxRow-1) {
            //has right neighbor
            if (!flashStatus[x+1][y]) {
                energyPoints[x+1][y]++;
            }
        }
        if (x > 0 && x <= maxRow-1) {
            //has left neighbor
            if (!flashStatus[x-1][y]) {
                energyPoints[x-1][y]++;
            }
        }

        if (y >= 0 && y < maxCol-1) {
            //has bottom neighbor
            if (!flashStatus[x][y+1]) {
                energyPoints[x][y+1]++;
            }
        }
        if (y > 0 && y <= maxCol-1) {
            //has top neighbor
            if (!flashStatus[x][y-1]) {
                energyPoints[x][y-1]++;
            }
        }

        if (x >= 0 && x <maxRow-1 && y >= 0 && y < maxCol-1) {
            //has right-bottom neighbor
            if (!flashStatus[x+1][y+1]) {
                energyPoints[x+1][y+1]++;
            }
        }

        if (x >= 0 && x <maxRow-1 && y > 0 && y <= maxCol-1) {
            //has right-top neighbor
            if (!flashStatus[x+1][y-1]) {
                energyPoints[x+1][y-1]++;
            }
        }
        if (x > 0 && x <= maxRow-1 && y >= 0 && y < maxCol-1) {
            //has left-bottom neighbor
            if (!flashStatus[x-1][y+1]) {
                energyPoints[x-1][y+1]++;
            }
        }

        if (x > 0 && x <= maxRow-1 && y > 0 && y <= maxCol-1) {
            //has left-top neighbor
            if (!flashStatus[x-1][y-1]) {
                energyPoints[x-1][y-1]++;
            }
        }

    }

    private void incEnergy() {
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                energyPoints[i][j]++;
            }
        }
    }

    private void resetFlashes() {
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                flashStatus[i][j] = false;
            }
        }
    }

    private int[][] readFromLines(List<String> lines) {
        int[][] result = new int[lines.get(0).length()][lines.size()];
        for (int j = 0; j < lines.size(); j++) {
            for (int i = 0; i < lines.get(j).length(); i++) {
                result[j][i] = Integer.parseInt(String.valueOf(lines.get(j).charAt(i)));
            }
        }
        return result;
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
}
