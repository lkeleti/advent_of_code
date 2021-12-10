package day10;

import day08.Segment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class NavigationController {

    public int countCorrupted() {
        List<String> lines = readFile(Path.of("src/main/resources/day10.txt"));
        int sum = findMismatch(lines);
        return sum;
    }

    private int findMismatch(List<String> lines) {
        int sum = 0;
        for (String line : lines) {
            List<Character> signs = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                char actChar = line.charAt(i);
                if (actChar == ')') {
                    if (signs.get(signs.size()-1) == '(') {
                        signs.remove(signs.size() - 1);
                    } else {
                        System.out.println(signs.get(signs.size()) - 1);
                        break;
                    }
                }
                if (actChar == ']') {
                    if (signs.get(signs.size()-1)  == '[') {
                        signs.remove(signs.size() - 1);
                    } else {
                        System.out.println(signs.get(signs.size()) - 1);
                        break;
                    }
                }
                if (actChar == '}') {
                    if (signs.get(signs.size()-1) == '{') {
                        signs.remove(signs.size() - 1);
                    } else {
                        System.out.println(signs.get(signs.size()) - 1);
                        break;
                    }
                }
                if (actChar == '>') {
                    if (signs.get(signs.size()-1) == '<') {
                        signs.remove(signs.size() - 1);
                    } else {
                        System.out.println(signs.get(signs.size()) - 1);
                        break;
                    }
                }
                signs.add(actChar);
            }
            System.out.println(line);
/*    ): 3 points.
      ]: 57 points.
      }: 1197 points.
      >: 25137 points.

      26397
*/

            if (line.charAt(0) == '(') {
                sum += 3;
            }

            if (line.charAt(0) == '[') {
                sum += 57;
            }

            if (line.charAt(0) == '{') {
                sum += 1197;
            }
            if (line.charAt(0) == '<') {
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