package day10;

import day08.Segment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NavigationController {

    public int countCorrupted() {
        List<String> lines = readFile(Path.of("src/main/resources/day10.txt"));
        int sum = findMismatch(lines);
        System.out.println(partTwo(lines));
        return sum;
    }

    private long partTwo(List<String> lines) {
        List<String> errors = new ArrayList<>();
        for (String line : lines) {
            int prevLength = 0;
            do {
                prevLength = line.length();
                line = line.replace("()", "");
                line = line.replace("[]", "");
                line = line.replace("{}", "");
                line = line.replace("<>", "");
            }
            while (prevLength != line.length());
            if (!(line.contains(")") || line.contains("]") || line.contains("}") || line.contains(">"))) {
                errors.add(line);
            }
        }
        long sum = 0;
        long[] scores = new long[errors.size()];
        int j = 0;
        for (String error : errors) {
            sum = 0;
            for (int i = error.length()-1; i >= 0; i--) {
                sum *= 5;
                switch (error.charAt(i)) {
                    case '(':
                        sum += 1;
                        break;
                    case '[':
                        sum += 2;
                        break;
                    case '{':
                        sum += 3;
                        break;
                    case '<':
                        sum += 4;
                        break;
                }
            }
            scores[j] =sum;
            j++;
        }
        Arrays.sort(scores);
        int mid = scores.length /2;
        return scores[mid];
    }

    private int findMismatch(List<String> lines) {
        int sum = 0;
        List<Character> errors = new ArrayList<>();
        for (String line : lines) {
            List<Character> signs = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                char actChar = line.charAt(i);
                boolean was = false;
                if (actChar == ')') {
                    if (signs.get(signs.size() - 1) == '(') {
                        signs.remove(signs.size() - 1);
                        was = true;
                    } else {
                        errors.add(actChar);
                        break;
                    }
                }
                if (actChar == ']') {
                    if (signs.get(signs.size() - 1) == '[') {
                        signs.remove(signs.size() - 1);
                        was = true;
                    } else {
                        errors.add(actChar);
                        break;
                    }
                }
                if (actChar == '}') {
                    if (signs.get(signs.size() - 1) == '{') {
                        signs.remove(signs.size() - 1);
                        was = true;
                    } else {
                        errors.add(actChar);
                        break;
                    }
                }
                if (actChar == '>') {
                    if (signs.get(signs.size() - 1) == '<') {
                        signs.remove(signs.size() - 1);
                        was = true;
                    } else {
                        errors.add(actChar);
                        break;
                    }
                }
                if (!was) {
                    signs.add(actChar);
                }
            }
/*    ): 3 points.
      ]: 57 points.
      }: 1197 points.
      >: 25137 points.

      26397
*/
        }
        for (char error : errors) {
            if (error == ')') {
                sum += 3;
            }

            if (error == ']') {
                sum += 57;
            }

            if (error == '}') {
                sum += 1197;
            }
            if (error == '>') {
                sum += 25137;
            }
        }
        return sum;
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