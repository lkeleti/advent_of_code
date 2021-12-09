package day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisplayController {

    public int countDigits() {
        List<String> lines = readFile(Path.of("src/main/resources/day08.txt"));
        List<Segment> segments = processData(lines);
        System.out.println(partOne(segments));
        int sum = 0;
        for (Segment segment: segments) {
            sum += decrypt(segment);
        }
        return sum;
    }
    private int partOne(List<Segment> segments) {
        int result = 0;
        for (Segment segment: segments) {
            for (String data : segment.datas) {
                switch (data.length()) {
                    case 2,3,4,7:
                        result++;
                        break;
                }
            }
        }
        return result;
    }

    private int decrypt (Segment segment) {
        String zeroDigit = "";
        String oneDigit = "";
        String twoDigit = "";
        String threeDigit = "";
        String fourDigit = "";
        String fiveDigit = "";
        String sixDigit = "";
        String sevenDigit = "";
        String eightDigit = "";
        String nineDigit = "";
        String[] sixLengthDigits = new String[3];
        int sixCounter = 0;
        String[] fiveLengthDigits = new String[3];
        int fiveCounter = 0;

        for (String s :segment.getDigits()) {
            switch (s.length()) {
                case 2:
                    oneDigit = s;
                    break;
                case 3:
                    sevenDigit = s;
                    break;
                case 4:
                    fourDigit = s;
                    break;
                case 5:
                    fiveLengthDigits[fiveCounter] = s;
                    fiveCounter++;
                    break;
                case 6:
                    sixLengthDigits[sixCounter] = s;
                    sixCounter++;
                    break;
                case 7:
                    eightDigit = s;
                    break;
            }
        }

        for (sixCounter = 0; sixCounter < 3; sixCounter++) {
            boolean hasIt = true;
            for (int i = 0; i < 4; i++) {
                if (!sixLengthDigits[sixCounter].contains(String.valueOf(fourDigit.charAt(i)))) {
                    hasIt = false;
                }
            }
            if (hasIt) {
                nineDigit = sixLengthDigits[sixCounter];
            } else {
                hasIt = true;
                for (int i = 0; i < 3; i++) {
                    if (!sixLengthDigits[sixCounter].contains(String.valueOf(sevenDigit.charAt(i)))) {
                        hasIt = false;
                    }
                }
                if (hasIt) {
                    zeroDigit = sixLengthDigits[sixCounter];
                } else {
                    sixDigit = sixLengthDigits[sixCounter];
                }
            }
        }
        for (fiveCounter = 0; fiveCounter < 3; fiveCounter++) {
            boolean hasIt = true;
            for (int i = 0; i < 3; i++) {
                if (!fiveLengthDigits[fiveCounter].contains(String.valueOf(sevenDigit.charAt(i)))) {
                    hasIt = false;
                }
            }
            if (hasIt) {
                threeDigit = fiveLengthDigits[fiveCounter];
            } else {
                int numberOfNotIn = 0;
                for (int i = 0; i < 6; i++) {
                    if (!fiveLengthDigits[fiveCounter].contains(String.valueOf(sixDigit.charAt(i)))) {
                        numberOfNotIn++;
                    }
                }
                if (numberOfNotIn == 1) {
                    fiveDigit = fiveLengthDigits[fiveCounter];
                } else {
                    twoDigit = fiveLengthDigits[fiveCounter];
                }
            }
        }

        /*System.out.println("0 " + zeroDigit);
        System.out.println("1 " + oneDigit);
        System.out.println("2 " + twoDigit);
        System.out.println("3 " + threeDigit);
        System.out.println("4 " + fourDigit);
        System.out.println("5 " + fiveDigit);
        System.out.println("6 " + sixDigit);
        System.out.println("7 " + sevenDigit);
        System.out.println("8 " + eightDigit);
        System.out.println("9 " + nineDigit);*/
        String number = "";
        for (String s: segment.datas) {
            if (s.equals(zeroDigit)) {
                number += "0";
            } else if (s.equals(oneDigit)) {
                number += "1";
            } else if (s.equals(twoDigit)) {
                number += "2";
            } else if (s.equals(threeDigit)) {
                number += "3";
            } else if (s.equals(fourDigit)) {
                number += "4";
            } else if (s.equals(fiveDigit)) {
                number += "5";
            } else if (s.equals(sixDigit)) {
                number += "6";
            } else if (s.equals(sevenDigit)) {
                number += "7";
            } else if (s.equals(eightDigit)) {
                number += "8";
            } else if (s.equals(nineDigit)) {
                number += "9";
            }
        }
        return Integer.parseInt(number);
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
            String[] splitedLine = line.split(" | ");
            List<String> digits = new ArrayList<>();
            List<String> datas = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                char tempArray[] = splitedLine[i].toCharArray();
                Arrays.sort(tempArray);
                String sortedLine = new String(tempArray);
                if (i < 10) {
                    digits.add(sortedLine);
                } else if (i > 10) {
                    datas.add(sortedLine);
                }
            }
            segments.add(new Segment(digits,datas));
        }
        return segments;
    }
}
