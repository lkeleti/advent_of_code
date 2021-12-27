package day24;

public class ProgLine {
    private String command;
    private String operand1;
    private String operand2;

    public ProgLine(String command, String operand1, String operand2) {
        this.command = command;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public String getCommand() {
        return command;
    }

    public String getOperand1() {
        return operand1;
    }

    public String getOperand2() {
        return operand2;
    }
}
