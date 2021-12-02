package day01;

import java.nio.file.Path;

public class Sonar {
    public static void main(String[] args) {
        SonarController sonarController = new SonarController();
        sonarController.readData(Path.of("src/main/resources/sonarinput.txt"));
        System.out.println(sonarController.countLargerThanPrev());

        MeasuringWindow measuringWindow = new MeasuringWindow();
        measuringWindow.readData(Path.of("src/main/resources/sonarinput.txt"));
        System.out.println(measuringWindow.countLargerThanPrev());
    }
}
