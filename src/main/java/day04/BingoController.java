package day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BingoController {
    private List<Integer> choosenNumbers = new ArrayList<>();
    private Cell[][] table = new Cell[5][5];
    private List<Cell[][]> tables = new ArrayList<>();

    public void findWinner() {
        List<String> lines = readFile(Path.of("src/main/resources/bingoinput.txt"));
        getDataFromLines(lines);

        for (Integer num: choosenNumbers) {
            System.out.println(num);
            findNumber(num);
            int result = checkRows(num);
            if (result != -1) {
                int tableSum = sumTable(result);
                System.out.println(tableSum);
                System.out.println("Winner: " + result + " table and num is " + num + " result: " + tableSum*num);
                tables.remove(result);
            }

            result = checkCols(num);
            if (result != -1) {
                int tableSum = sumTable(result);
                System.out.println(tableSum);
                System.out.println("Winner: " + result + " table and num is " + num + " result: " + tableSum*num);
                tables.remove(result);
            }
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

    private void getDataFromLines(List<String> lines) {
        getChoosenNumbers(lines.get(0));

        int tablesCounter = -1;
        int rowCounter = 0;
        for (int i = 1; i < lines.size(); i++) {
            if (lines.get(i).isBlank()) {
                tables.add(new Cell[5][5]);
                tablesCounter++;
                rowCounter = 0;
            } else {
                int num1 = Integer.parseInt(lines.get(i).substring(0, 2).trim());
                int num2 = Integer.parseInt(lines.get(i).substring(3, 5).trim());
                int num3 = Integer.parseInt(lines.get(i).substring(6, 8).trim());
                int num4 = Integer.parseInt(lines.get(i).substring(9, 11).trim());
                int num5 = Integer.parseInt(lines.get(i).substring(12, 14).trim());
                tables.get(tablesCounter)[rowCounter][0] = new Cell(num1);
                tables.get(tablesCounter)[rowCounter][1] = new Cell(num2);
                tables.get(tablesCounter)[rowCounter][2] = new Cell(num3);
                tables.get(tablesCounter)[rowCounter][3] = new Cell(num4);
                tables.get(tablesCounter)[rowCounter][4] = new Cell(num5);
                rowCounter++;
            }
        }
        System.out.println(tables);
    }

    private void getChoosenNumbers(String line) {
        String[] numbersInString = line.split(",");
        for (String data : numbersInString) {
            choosenNumbers.add(Integer.parseInt(data));
        }
    }

    private int checkCols(int num) {
        for (int i = 0; i < tables.size(); i++) {
            boolean wasNum = false;
            for (int row = 0; row < 5; row++){
                int colCounter = 0;
                for (int col = 0; col < 5; col++){
                    if (tables.get(i)[row][col].isStatus()) {
                        if (tables.get(i)[row][col].getValue() == num) {
                            wasNum = true;
                        }
                        colCounter++;
                    }
                }
                if (colCounter == 5 && wasNum) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int checkRows(int num) {
        for (int i = 0; i < tables.size(); i++) {
            boolean wasNum = false;
            for (int col = 0; col < 5; col++){
                int rowCounter = 0;
                for (int row = 0; row < 5; row++){
                    if (tables.get(i)[row][col].isStatus()) {
                        if (tables.get(i)[row][col].getValue() == num) {
                            wasNum = true;
                        }
                        rowCounter++;
                    }
                }
                if (rowCounter == 5 && wasNum) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void findNumber(int num) {
        for (int i = 0; i < tables.size(); i++) {
            for (int col = 0; col < 5; col++){
                for (int row = 0; row < 5; row++){
                    if (tables.get(i)[row][col].getValue() == num) {
                        tables.get(i)[row][col].setStatus(true);
                    }
                }
            }
        }
    }

    private int sumTable(int num) {
        int summa = 0;
        for (int col = 0; col < 5; col++){
            for (int row = 0; row < 5; row++){
                if (!tables.get(num)[row][col].isStatus()) {
                    summa += tables.get(num)[row][col].getValue();
                }
            }
        }
        return summa;
    }
}
