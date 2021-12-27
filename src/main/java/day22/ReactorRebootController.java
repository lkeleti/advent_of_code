package day22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReactorRebootController {

    private int minX;
    private int minY;
    private int minZ;

    public int processData() {
        List<String> lines = readFile(Path.of("src/main/resources/day22.txt"));
        List<StatusInfo> statusInfos = readFromLines(lines);
        boolean[][][] cuboids = getArrayDimensions(statusInfos);
        cuboids = changeStatus(cuboids, statusInfos);
        System.out.println(Arrays.deepToString(cuboids));
        return countTrues(cuboids);
    }

    private int countTrues(boolean[][][] cuboids) {
        int counter = 0;
        for (int i = 0; i < cuboids.length; i++) {
            for (int j = 0; j < cuboids[0].length; j++) {
                for (int k = 0; k < cuboids[0][0].length; k++) {
                    if (cuboids[i][j][k]) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

    private boolean[][][] changeStatus(boolean[][][] cuboids, List<StatusInfo> statusInfos) {
        for (StatusInfo si : statusInfos) {
            for (int i = si.getStartX() + minX; i < si.getEndX() + minX; i++) {
                for (int j = si.getStartY() + minY; j < si.getEndY() + minY; j++) {
                    for (int k = si.getStartZ() + minZ; k < si.getEndZ() + minZ; k++) {
                        cuboids[i][j][k] = si.isActive();
                    }
                }
            }
        }
        return cuboids;
    }

    private List<StatusInfo> readFromLines(List<String> lines) {
        List<StatusInfo> statusDatas = new ArrayList<>();
        for (String line : lines) {
            statusDatas.add(readDataFromOneLine(line));
        }
        return statusDatas;
    }

    private StatusInfo readDataFromOneLine(String line) {
        int spacePos = line.indexOf(" ");
        String active = line.substring(0, spacePos);
        String[] lineArray = line.substring(spacePos).split(",");
        int[] x = getCoords(lineArray[0]);
        int[] y = getCoords(lineArray[1]);
        int[] z = getCoords(lineArray[2]);
        return new StatusInfo((active.equals("on") ? true : false), x[0], x[1], y[0], y[1], z[0], z[1]);
    }

    private int[] getCoords(String line) {
        //x=10..12
        int[] result = new int[2];
        int start = line.indexOf("=");
        line = line.substring(start + 1);
        int end = line.indexOf("..");
        int one = Integer.parseInt(line.substring(0, end));
        int two = Integer.parseInt(line.substring(end + 2));
        result[0] = Math.min(one, two);
        result[1] = Math.max(one, two);
        return result;
    }

    private List<String> readFile(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file!", ioe);
        }
    }

    private boolean[][][] getArrayDimensions(List<StatusInfo> statusInfos) {
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int maxZ = Integer.MIN_VALUE;

        minX = Integer.MAX_VALUE;
        minY = Integer.MAX_VALUE;
        minZ = Integer.MAX_VALUE;

        for (StatusInfo si : statusInfos) {
            if (si.getStartX() < minX) {
                minX = si.getStartX();
            }
            if (si.getStartY() < minY) {
                minY = si.getStartY();
            }
            if (si.getStartZ() < minZ) {
                minZ = si.getStartZ();
            }

            if (si.getEndX() > maxX) {
                maxX = si.getEndX();
            }

            if (si.getEndY() > maxY) {
                maxY = si.getEndY();
            }

            if (si.getEndZ() > maxZ) {
                maxZ = si.getEndZ();
            }
        }

        minX = Math.abs(minX);
        minY = Math.abs(minY);
        minZ = Math.abs(minZ);

        maxX = Math.abs(maxX);
        maxY = Math.abs(maxY);
        maxZ = Math.abs(maxZ);

        boolean[][][] result = new boolean[maxX + minX][maxY + minY][maxZ + minZ];
        return result;
    }
}
