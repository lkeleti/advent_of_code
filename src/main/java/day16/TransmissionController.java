package day16;

import day15.Position;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TransmissionController {

    private List<String> bits = new ArrayList<>();
    private int sumOfVersions = 0;
    private int pointer = 0;
    private String stringToDecode = "";

    public long decodePacket() {
        List<String> lines = readFile(Path.of("src/main/resources/day16.txt"));
        readFromLines(lines);
        System.out.println(bits);
        interpreter();
        System.out.println(sumOfVersions);
        return 0;
    }

    private void interpreter() {
        while (pointer < bits.size()) {
            stringToDecode = bits.get(pointer);
            pointer++;
            getPocket();
        }
    }

    private void getPocket() {
        getVersion();
        String typeId = getTypeId();
        if (typeId.equals("100")) {
            //literal
            int value = getLiteral();
        }
        else {
            //operator
            if (stringToDecode.length() < 1) {
                stringToDecode += bits.get(pointer);
                pointer++;
            }
            String lengthTypeId = stringToDecode.substring(0,1);
            stringToDecode = stringToDecode.substring(1,stringToDecode.length());
            if (lengthTypeId.equals("0")){
                //numbers
                if (stringToDecode.length() < 15) {
                    stringToDecode += bits.get(pointer);
                    pointer++;
                }
                if (stringToDecode.length() < 15) {
                    stringToDecode += bits.get(pointer);
                    pointer++;
                }

                int lengthOfSubPocket = Integer.parseInt(stringToDecode.substring(0,15),2);
                stringToDecode = stringToDecode.substring(15, stringToDecode.length());

                if (stringToDecode.length() < lengthOfSubPocket) {
                    int end = (lengthOfSubPocket - stringToDecode.length());
                    for (int i = 0; i <  end /8; i++)
                    stringToDecode += bits.get(pointer);
                    pointer++;
                }
                if (stringToDecode.length() < lengthOfSubPocket) {
                    stringToDecode += bits.get(pointer);
                    pointer++;
                }

                getVersion();
                getTypeId();

                int secondNumber = Integer.parseInt(stringToDecode.substring(0,lengthOfSubPocket-6),2);
                stringToDecode = stringToDecode.substring(lengthOfSubPocket-6,stringToDecode.length());
            }
            else {
                //SubPockets
                if (stringToDecode.length() < 11) {
                    stringToDecode += bits.get(pointer);
                    pointer++;
                }
                if (stringToDecode.length() < 11) {
                    stringToDecode += bits.get(pointer);
                    pointer++;
                }
                int numberOfSubPackets = Integer.parseInt(stringToDecode.substring(1,11),2);
                stringToDecode = stringToDecode.substring(11,stringToDecode.length());

                for (int i = 0; i < numberOfSubPackets; i++) {
                    getPocket();
                }
            }
        }
    }

    private int getLiteral() {
        String number = "";
        if (stringToDecode.length() < 5) {
            stringToDecode += bits.get(pointer);
            pointer++;
        }

        while (stringToDecode.substring(0,1).equals("1")) {
            if (stringToDecode.length() < 5) {
                stringToDecode += bits.get(pointer);
                pointer++;
            }
            number += stringToDecode.substring(1,5);
            stringToDecode = stringToDecode.substring(5,stringToDecode.length());
        }
        //last part of the number
        if (stringToDecode.length() < 5) {
            stringToDecode += bits.get(pointer);
            pointer++;
        }
        number += stringToDecode.substring(1,5);
        stringToDecode = stringToDecode.substring(5,stringToDecode.length());
        return Integer.parseInt(number,2);
    }

    private String getTypeId() {
        if (stringToDecode.length() < 3) {
            stringToDecode += bits.get(pointer);
            pointer++;
        }
        String result = stringToDecode.substring(0,3);
        stringToDecode = stringToDecode.substring(3,stringToDecode.length());
        return result;
    }

    private void getVersion() {
        if (stringToDecode.length() < 3) {
            stringToDecode += bits.get(pointer);
            pointer++;
        }
        sumOfVersions += Integer.parseInt(stringToDecode.substring(0,3),2);
        stringToDecode = stringToDecode.substring(3,stringToDecode.length());
    }

    private void readFromLines(List<String> lines) {
        for (String line : lines) {
            for (int i = 0; i < line.length(); i += 2) {
                int value = Integer.parseInt(line.substring(i, i + 2), 16);
                String bin = ("00000000" + Integer.toBinaryString(value));
                int start = bin.length() - 8;
                bin = bin.substring(start, start + 8);
                bits.add(bin);
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
