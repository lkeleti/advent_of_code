package day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

public class PolymerizationController {

    private String start;
    private char lastChar;

    public int findDifference() {
        List<String> lines = readFile(Path.of("src/main/resources/day14.txt"));
        Hashtable<String, String> decoder = readFromLines(lines);
        String decodedString = decodeCode(decoder);
        int difference = checkCharacters(decodedString);
        System.out.println(difference);
        //10 -> 2703
        //40 -> 2984946368465
        return 0;
    }

    public void part2() {
        List<String> lines = readFile(Path.of("src/main/resources/day14.txt"));
        Hashtable<String, String> decoder = readFromLines(lines);
        lastChar = start.charAt(start.length()-1);
        simulate2(decoder, start);
    }

    private long simulate2(Hashtable<String, String> decoder, String StringToDecode) {
        Hashtable<String, Long> codePairsCounter = new Hashtable<>();
        for (int i=0; i < StringToDecode.length()-1; i++){
            String key = StringToDecode.substring(i, i+2);
            if (!codePairsCounter.containsKey(key)) {
                codePairsCounter.put(key, 1L);
            }
            else {
                codePairsCounter.replace(key, codePairsCounter.get(key)+1L);
            }
        }

        for (int i = 0; i < 40; i++) {
            Hashtable<String, Long> tmpCodePairsCounter = new Hashtable<>();
            for (String cp : codePairsCounter.keySet()) {
                String newChar = decoder.get(cp);
                String newPair1 = cp.substring(0, 1) + newChar;
                String newPair2 = newChar + cp.substring(1, 2);
                if (!tmpCodePairsCounter.containsKey(newPair1)) {
                    tmpCodePairsCounter.put(newPair1, codePairsCounter.get(cp));
                }
                else {
                    Long tmpCounter1 = tmpCodePairsCounter.get(newPair1);
                    tmpCodePairsCounter.replace(newPair1,tmpCounter1+codePairsCounter.get(cp));
                }
                if (!tmpCodePairsCounter.containsKey(newPair2)) {
                    tmpCodePairsCounter.put(newPair2, codePairsCounter.get(cp));
                }
                else {
                    Long tmpCounter2 = tmpCodePairsCounter.get(newPair2);
                    tmpCodePairsCounter.replace(newPair2,tmpCounter2+codePairsCounter.get(cp));
                }
            }

            codePairsCounter = new Hashtable<>();
            for (String cp : tmpCodePairsCounter.keySet()) {
                if (!codePairsCounter.containsKey(cp)) {
                    codePairsCounter.put(cp, tmpCodePairsCounter.get(cp));
                } else {
                    Long tmpValue = codePairsCounter.get(cp);
                    codePairsCounter.replace(cp, tmpValue + tmpCodePairsCounter.get(cp));
                }
            }
        }
        System.out.println(codePairsCounter);
        countChars(codePairsCounter);
        return 0;
    }

    private long countChars(Hashtable<String, Long> pairs) {
        Hashtable<Character, Long> chars = new Hashtable<>();
        for (String pair: pairs.keySet()) {
            long count = pairs.get(pair);

            for (int i = 0; i <2; i++) {
                char newKey = pair.charAt(i);
                if (!chars.containsKey(newKey)) {
                    chars.put(newKey, count);
                } else {
                    long defCount = chars.get(newKey);
                    chars.replace(newKey, defCount+count);
                }
            }
        }

        for (char c: chars.keySet()) {
            chars.replace(c, chars.get(c)/2);
        }
        chars.replace(lastChar, chars.get(lastChar)+1);
        System.out.println(chars);
        long minValue = Long.MAX_VALUE;
        long maxValue = Long.MIN_VALUE;
        for (char c: chars.keySet()) {
            if (chars.get(c) > maxValue) {
                maxValue = chars.get(c);
            }

            if (chars.get(c) < minValue) {
                minValue = chars.get(c);
            }
        }
        System.out.println(maxValue-minValue);
        return maxValue-minValue;
    }

    private int checkCharacters(String decodedString) {
        Hashtable<Character, Integer> chars = new Hashtable<>();
        for (char c : decodedString.toCharArray()) {
            if (!chars.containsKey(c)) {
                chars.put(c, 1);
            } else {
                int newCount = chars.get(c) + 1;
                chars.replace(c, newCount);
            }
        }
        Collection<Integer> values = chars.values();
        int minValue = Integer.MAX_VALUE;
        int maxValue = 0;

        for (int i : values) {
            if (i > maxValue) {
                maxValue = i;
            }

            if (i < minValue) {
                minValue = i;
            }
        }
        return maxValue - minValue;
    }


    private String decodeCode(Hashtable<String, String> decoder) {
        StringBuilder sb = new StringBuilder();
        String codedString = start;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < codedString.length() - 1; j++) {
                String code = codedString.substring(j, j + 2);
                sb.append(codedString.substring(j, j + 1));
                sb.append(decoder.get(code));
            }
            sb.append(codedString.substring(codedString.length() - 1));
            codedString = sb.toString();
            sb.setLength(0);
        }
        return codedString;
    }

    private Hashtable<String, String> readFromLines(List<String> lines) {
        Hashtable<String, String> decoder = new Hashtable<>();
        for (String line : lines) {
            if (!line.contains(" -> ")) {
                if (!line.isEmpty()) {
                    start = line;
                }
            } else {
                String[] lineData = line.split(" -> ");
                decoder.put(lineData[0], lineData[1]);
            }
        }
        return decoder;
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
