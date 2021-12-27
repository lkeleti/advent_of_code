package day21;

import day22.StatusInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private Player player1;
    private Player player2;

    public int processData() {
        List<String> lines = readFile(Path.of("src/main/resources/day21.txt"));
        readFromLines(lines);
        simulate();
        return 0;
    }

    private void simulate() {
        int dice = 1;
        int counter = 0;
        boolean end = false;
        while (!end) {
            dice = throwing(player1, dice);
            counter += 3;
            if (player1.getScore() >= 1000) {
                end = true;
            }
            else {
                dice = throwing(player2, dice);
                counter += 3;
                if (player2.getScore() >= 1000) {
                    end = true;
                }
            }
        }
        System.out.println(player1.getScore());
        System.out.println(player2.getScore());
        System.out.println(counter);
        System.out.println(Math.min(player1.getScore(),player2.getScore())*counter);
    }

    private int throwing(Player player, int dice) {
        int counter = dice;
        for (int i = 0; i < 2; i++) {
            dice++;
            if (dice == 101) {
                dice = 1;
            }
            counter += dice;
        }
        player.setPosition(counter);
        return (dice+1);
    }

    private void readFromLines(List<String> lines) {
        for (String line:lines) {
            if (line.contains("Player 1 starting position: ")) {
                player1 = new Player(Integer.parseInt(line.substring(28, line.length())));
            }
            if (line.contains("Player 2 starting position: ")) {
                player2 = new Player(Integer.parseInt(line.substring(28, line.length())));
            }
        }
    }

    private List<String> readFile(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file!", ioe);
        }
    }
}
