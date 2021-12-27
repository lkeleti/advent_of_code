package day20;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ImageController {
    private String imageEnhancementAlgorithm = "";
    private List<String> imageData = new ArrayList<>();

    public int countLightpixels() {
        List<String> lines = readFile(Path.of("src/main/resources/day20.txt"));
        readFromLines(lines);
        //System.out.println(imageEnhancementAlgorithm);
        System.out.println(imageData);

        imageData = iterateOverPixels(imageData);
        System.out.println(imageData);
        System.out.println(countOnes(imageData));
        imageData = putZeros(imageData);
        imageData = putZeros(imageData);

        imageData = iterateOverPixels(imageData);

        System.out.println(imageData);
        System.out.println(countOnes(imageData));
        //4942 too high
        //4863 too low
        return 0;
    }

    private List<String> putZeros(List<String> imageData) {
        List<String> newPixels = new ArrayList<>();
        for (String s:imageData) {
            newPixels.add("0"  + s + "0");
        }
        int lengthOfRow = newPixels.get(0).length();
        String zeros = "0".repeat(lengthOfRow);
        //newPixels.add(zeros);
        newPixels.add(zeros);

        //newPixels.add(0,zeros);
        newPixels.add(0,zeros);
        return newPixels;
    }

    private List<String> iterateOverPixels(List<String> imagePixels) {
        List<String> newPixels = new ArrayList<>();
        for (int row = 1; row < imagePixels.size()-1; row++) {
            String line = "";
            for (int col = 1; col < imagePixels.get(0).length()-1; col++) {
                String value = "";
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        value += imagePixels.get(row + i).charAt(col+j);
                    }
                }
                line += imageEnhancementAlgorithm.charAt(Integer.parseInt(value,2));
            }
            newPixels.add(line);
        }
        return newPixels;
    }

    private int countOnes(List<String> dataToProcess) {
        int counter = 0;
        for (String data: dataToProcess){
            for (char c: data.toCharArray()){
                if (c =='1') {
                    counter++;
                }
            }
        }
        return counter;
    }

    private void readFromLines(List<String> lines) {
        int counter = 0;
        for (String line : lines) {
            if (counter == 0) {
                //first line
                imageEnhancementAlgorithm = changeChars(line);
            }
            else if (counter > 1) {
                imageData.add("00" + changeChars(line) + "00");
            }
            counter++;
        }
        int lengthOfRow = imageData.get(0).length();
        String zeros = "0".repeat(lengthOfRow);
        imageData.add(zeros);
        imageData.add(zeros);
        imageData.add(0,zeros);
        imageData.add(0,zeros);
    }

    private String changeChars(String input) {
        String output = input;
        output = output.replace(".","0");
        output = output.replace("#","1");
        return output;
    }

    private List<String> readFile(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file!", ioe);
        }
    }
}
