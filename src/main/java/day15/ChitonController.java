package day15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChitonController {
    public long countCost() {
        List<String> lines = readFile(Path.of("src/main/resources/day15.txt"));
        int[][] labyrinth = readFromLines(lines);
        List<Position> tracking = new ArrayList<>();
        Position start = new Position(0,0,1);
        tracking.add(start);
        List<Position> t = findpaths(start, new Position(9,9,1), labyrinth,tracking);
        return 0;
    }

    private List<Position> findpaths(Position start, Position end, int[][] labyrinth, List<Position> tracking) {
        Position newStart;
        if (start == end) {
            return tracking;
        } else {
            if (start.getxPos() == 0 ) {
                newStart = right(start, labyrinth);
                if (!(isInTheTracking(newStart, tracking))) {
                    tracking.add(newStart);
                    findpaths(newStart, end, labyrinth, tracking);
                }
            }
            else {
                if (start.getxPos() == labyrinth[0].length-1) {
                    newStart = left(start, labyrinth);
                    if (!(isInTheTracking(newStart, tracking))) {
                        tracking.add(newStart);
                        findpaths(newStart, end, labyrinth, tracking);
                    }
                }
                else {
                    newStart = right(start, labyrinth);
                    if (!(isInTheTracking(newStart, tracking))) {
                        tracking.add(newStart);
                        findpaths(newStart, end, labyrinth, tracking);
                    }
                    newStart = left(start, labyrinth);
                    if (!(isInTheTracking(newStart, tracking))) {
                        tracking.add(newStart);
                        findpaths(newStart, end, labyrinth, tracking);
                    }
                }
            }

            if (start.getyPos() == 0) {
                newStart = down(start, labyrinth);
                if (!(isInTheTracking(newStart, tracking))) {
                    tracking.add(newStart);
                    findpaths(newStart, end, labyrinth, tracking);
                }
                else {
                    if (start.getyPos() == labyrinth.length-1) {
                        newStart = up(start, labyrinth);
                        if (!(isInTheTracking(newStart, tracking))) {
                            tracking.add(newStart);
                            findpaths(newStart, end, labyrinth, tracking);
                        }
                        else {
                            newStart = down(start, labyrinth);
                            if (!(isInTheTracking(newStart, tracking))) {
                                tracking.add(newStart);
                                findpaths(newStart, end, labyrinth, tracking);
                            }
                            newStart = up(start, labyrinth);
                            if (!(isInTheTracking(newStart, tracking))) {
                                tracking.add(newStart);
                                findpaths(newStart, end, labyrinth, tracking);
                            }
                        }
                    }
                }

            }
        }
        newStart = tracking.get(tracking.size()-2);
        findpaths(newStart, end, labyrinth, tracking);
        return tracking;
    }

    private Position right(Position start, int[][] labyrinth) {
        int posX = start.getxPos() + 1;
        int posY = start.getyPos();
        int cost = labyrinth[posX][posY];
        Position newStart = new Position(posX, posY, cost);
        return newStart;
    }

    private Position left(Position start, int[][] labyrinth) {
        int posX = start.getxPos() - 1;
        int posY = start.getyPos();
        int cost = labyrinth[posX][posY];
        Position newStart = new Position(posX, posY, cost);
        return newStart;
    }

    private Position down(Position start, int[][] labyrinth) {
        int posX = start.getxPos();
        int posY = start.getyPos()+1;
        int cost = labyrinth[posX][posY];
        Position newStart = new Position(posX, posY, cost);
        return newStart;
    }

    private Position up(Position start, int[][] labyrinth) {
        int posX = start.getxPos();
        int posY = start.getyPos()-1;
        int cost = labyrinth[posX][posY];
        Position newStart = new Position(posX, posY, cost);
        return newStart;
    }

    private boolean isInTheTracking(Position newStart, List<Position> tracking) {
        for (Position position : tracking) {
            if (position.equals(newStart)) {
                return true;
            }
        }
        return false;
    }


    private int[][] readFromLines(List<String> lines) {
        int[][] labyrinth = new int[lines.get(0).length()][lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                labyrinth[i][j] = Integer.parseInt(lines.get(i).substring(j, j + 1));
            }
        }
        return labyrinth;
    }

    private List<String> readFile(Path path) {
        List<String> lines;
        try {
            lines = Files.readAllLines(path);
            return lines;
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file!", ioe);
        }
    }
}
