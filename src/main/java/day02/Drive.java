package day02;

import java.nio.file.Path;

public class Drive {
    public static void main(String[] args) {
        DriveController driveController = new DriveController();
        System.out.println(driveController.readData(Path.of("src/main/resources/driveinput.txt")));

        System.out.println(driveController.readDataComplicated(Path.of("src/main/resources/driveinput.txt")));
    }
}
