package day16;

import day15.Position;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TransmissionController {

    private int sumOfVersions = 0;
    private String stringToDecode = "";
    private List<String> operations = new ArrayList<>();

    public long decodePacket() {
        List<String> lines = readFile(Path.of("src/main/resources/day16.txt"));
        readFromLines(lines);
        System.out.println(stringToDecode);
        interpreter();
        System.out.println(sumOfVersions);
        System.out.println(operations);
        return 0;
    }

    private void interpreter() {
        while (stringToDecode.length() > 8) {
            getPocket(false);
        }
    }

    private int getPocket(boolean subCall) {
        int subCounter = 0;
        getVersion();
        subCounter += 3;
        String typeId = getTypeId();
        subCounter += 3;

        if (typeId.equals("100")) {
            //literal
            subCounter += getLiteral();
        } else {
            if (typeId.equals("000")) {
                //sum
                operations.add("+");
            }
            if (typeId.equals("001")) {
            //product
                operations.add("*");
            }

            if (typeId.equals("010")) {
                //minimum
                operations.add("min");
            }

            if (typeId.equals("011")) {
                //maximum
                operations.add("max");
            }
            if (typeId.equals("101")) {
                //greater than
                operations.add("grt");
            }
            if (typeId.equals("110")) {
                //greater than
                operations.add("less");
            }
            if (typeId.equals("111")) {
                //greater than
                operations.add("equ");
            }


            //operator
            String lengthTypeId = stringToDecode.substring(0, 1);
            stringToDecode = stringToDecode.substring(1, stringToDecode.length());
            subCounter++;
            if (lengthTypeId.equals("0")) {
                //15bits= total length in bits of sub-packets

                int lengthOfSubPocket = Integer.parseInt(stringToDecode.substring(0, 15), 2);
                stringToDecode = stringToDecode.substring(15, stringToDecode.length());
                subCounter += 15;

                //operations.add("(");
                while (subCounter < lengthOfSubPocket) {
                    subCounter += getPocket(true);
                }
                //operations.add(")");

            } else {
                //11bits= number of subpackets
                int numberOfSubPackets = Integer.parseInt(stringToDecode.substring(1, 11), 2);
                stringToDecode = stringToDecode.substring(11, stringToDecode.length());
                subCounter += 11;

                //operations.add("(");
                for (int i = 0; i < numberOfSubPackets; i++) {
                    subCounter += getPocket(true);
                }
                //operations.add(")");
            }
        }
        return subCounter;
    }

    private int getLiteral() {
        String number = "";
        int result = 0;

        while (stringToDecode.substring(0, 1).equals("1")) {
            number += stringToDecode.substring(1, 5);
            stringToDecode = stringToDecode.substring(5, stringToDecode.length());
            result += 5;
        }
        //last part of the number
        number += stringToDecode.substring(1, 5);
        stringToDecode = stringToDecode.substring(5, stringToDecode.length());
        result += 5;
        operations.add(String.valueOf(Long.parseLong(number, 2)));
        //int decodedNumber = Integer.parseInt(number, 2);
        return result;
    }

    private String getTypeId() {
        String result = stringToDecode.substring(0, 3);
        stringToDecode = stringToDecode.substring(3, stringToDecode.length());
        return result;
    }

    private void getVersion() {
        sumOfVersions += Integer.parseInt(stringToDecode.substring(0, 3), 2);
        stringToDecode = stringToDecode.substring(3, stringToDecode.length());
    }

    private void readFromLines(List<String> lines) {
        for (String line : lines) {
            for (int i = 0; i < line.length(); i += 2) {
                int value = Integer.parseInt(line.substring(i, i + 2), 16);
                String bin = ("00000000" + Integer.toBinaryString(value));
                int start = bin.length() - 8;
                bin = bin.substring(start, start + 8);
                stringToDecode += bin;
            }
        }
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
