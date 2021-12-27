package day24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Alu {
    private Long x = 0L;
    private Long y = 0L;
    private Long z = 0L;
    private Long w = 0L;

    private List<ProgLine> program = new ArrayList<>();

    public int processData() {
        List<String> lines = readFile(Path.of("src/main/resources/day24.txt"));
        readFromLines(lines);
        for (Long i = 99999999999999L; i > 9999999999999L; i--) {
            run(i);
            if (z == 0L) {
                System.out.println("1");
                System.out.println("x: " + x);
                System.out.println("y: " + y);
                System.out.println("z: " + z);
                System.out.println("w: " + w);
                break;
            }
            x = 0L;
            y = 0L;
            z = 0L;
            w = 0L;
        }

        System.out.println("x: " + x);
        System.out.println("y: " + y);
        System.out.println("z: " + z);
        System.out.println("w: " + w);
        return 0;
    }

    private void run(Long startValue) {
        for (ProgLine pl : program) {
            switch (pl.getCommand()) {
                case "inp":
                    w = startValue;
                    break;
                case "add":
                    if (isNumber(pl.getOperand2())) {
                        add(pl.getOperand1().charAt(0), Long.parseLong(pl.getOperand2()));
                    } else {
                        add(pl.getOperand1().charAt(0), pl.getOperand2().charAt(0));
                    }
                    break;
                case "mul":
                    if (isNumber(pl.getOperand2())) {
                        mul(pl.getOperand1().charAt(0), Long.parseLong(pl.getOperand2()));
                    } else {
                        mul(pl.getOperand1().charAt(0), pl.getOperand2().charAt(0));
                    }
                    break;
                case "div":
                    div(pl.getOperand1().charAt(0), Long.parseLong(pl.getOperand2()));
                    break;
                case "mod":
                    mod(pl.getOperand1().charAt(0), Long.parseLong(pl.getOperand2()));
                    break;
                case "eql":
                    if (isNumber(pl.getOperand2())) {
                        eql(pl.getOperand1().charAt(0), Long.parseLong(pl.getOperand2()));
                    } else {
                        eql(pl.getOperand1().charAt(0), pl.getOperand2().charAt(0));
                    }
                    break;
            }
        }
    }

    private boolean isNumber(String operand) {
        try {
            Long.parseLong(operand);
            return true;
        } catch (NumberFormatException ne) {
            return false;
        }
    }

    private void readFromLines(List<String> lines) {
        for (String line : lines) {
            String[] datas = line.split(" ");
            if (datas.length > 2) {
                program.add(new ProgLine(datas[0], datas[1], datas[2]));
            } else {
                program.add(new ProgLine(datas[0], datas[1], ""));
            }
        }
    }

    private void inp(Long value, char register) {
        switch (register) {
            case 'x':
                x = value;
                break;
            case 'y':
                y = value;
                break;
            case 'z':
                z = value;
                break;
            case 'w':
                w = value;
                break;
        }
    }

    private void add(char register, Long value) {
        switch (register) {
            case 'x':
                x += value;
                break;
            case 'y':
                y += value;
                break;
            case 'z':
                z += value;
                break;
            case 'w':
                w += value;
                break;
        }
    }

    private void add(char register, char register1) {
        Long value = 0L;
        switch (register1) {
            case 'x':
                value = x;
                break;
            case 'y':
                value = y;
                break;
            case 'z':
                value = z;
                break;
            case 'w':
                value = w;
                break;
        }
        switch (register) {
            case 'x':
                add('x', value);
                break;
            case 'y':
                add('y', value);
                break;
            case 'z':
                add('z', value);
                break;
            case 'w':
                add('w', value);
                break;
        }
    }

    private void mul(char register, Long value) {
        switch (register) {
            case 'x':
                x *= value;
                break;
            case 'y':
                y *= value;
                break;
            case 'z':
                z *= value;
                break;
            case 'w':
                w *= value;
                break;
        }
    }

    private void mul(char register, char register1) {
        Long value = 0L;
        switch (register1) {
            case 'x':
                value = x;
                break;
            case 'y':
                value = y;
                break;
            case 'z':
                value = z;
                break;
            case 'w':
                value = w;
                break;
        }
        switch (register) {
            case 'x':
                mul('x', value);
                break;
            case 'y':
                mul('y', value);
                break;
            case 'z':
                mul('z', value);
                break;
            case 'w':
                mul('w', value);
                break;
        }
    }

    private void div(char register, Long value) {
        switch (register) {
            case 'x':
                x /= value;
                break;
            case 'y':
                y /= value;
                break;
            case 'z':
                z /= value;
                break;
            case 'w':
                w /= value;
                break;
        }
    }

    private void mod(char register, Long value) {
        switch (register) {
            case 'x':
                x %= value;
                break;
            case 'y':
                y %= value;
                break;
            case 'z':
                z %= value;
                break;
            case 'w':
                w %= value;
                break;
        }
    }

    private void eql(char register, Long value) {
        switch (register) {
            case 'x':
                x = x == value ? 1L : 0L;
                break;
            case 'y':
                y = y == value ? 1L : 0L;
                break;
            case 'z':
                z = z == value ? 1L : 0L;
                break;
            case 'w':
                w = w == value ? 1L : 0L;
                break;
        }
    }

    private void eql(char register, char register1) {
        Long value = 0L;
        switch (register1) {
            case 'x':
                value = x;
                break;
            case 'y':
                value = y;
                break;
            case 'z':
                value = z;
                break;
            case 'w':
                value = w;
                break;
        }
        switch (register) {
            case 'x':
                eql('x', value);
                break;
            case 'y':
                eql('y', value);
                break;
            case 'z':
                eql('z', value);
                break;
            case 'w':
                eql('w', value);
                break;
        }
    }

    private List<String> readFile(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file!", ioe);
        }
    }
}
