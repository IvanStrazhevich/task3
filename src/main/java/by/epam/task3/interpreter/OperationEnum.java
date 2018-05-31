package by.epam.task3.interpreter;

public enum OperationEnum {
    XOR("^"), NOT("~"), RIGHT_SHIFT(">>"), ZERO_RIGHT_SHIFT(">>>"), B_OR("|"), B_AND("&"),
    PLUS("+"), MINUS("-"), DIVIDE("/"), MULTIPLY("*");
    String string;

    OperationEnum(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
