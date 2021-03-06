package day03;

import day02.DriveController;
import org.w3c.dom.ls.LSOutput;

import java.nio.file.Path;

public class Noise {
    public static void main(String[] args) {
        NoiseController noiseController = new NoiseController();
        System.out.println(noiseController.readData(Path.of("src/main/resources/noiseinput.txt")));

        int oxygen = noiseController.SecondExerciseA(Path.of("src/main/resources/noiseinput.txt"));
        int co2 = noiseController.SecondExerciseB(Path.of("src/main/resources/noiseinput.txt"));

        System.out.println(oxygen*co2);

    }
}
