package by.epam.task3.interpreter;

import by.epam.task3.exception.CompositeHandleException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Scanner;

public class PolishNotationConverter {
    private static Logger logger = LogManager.getLogger();

    private int checkPriority(String operation) throws CompositeHandleException {
        int priority = 0;
        switch (operation) {
            case "(":
            case ")":
            case "[":
            case "]":
                priority = 0;
                break;
            case "~":
                priority = 1;
                break;
            case "*":
            case "/":
            case "%":
                priority = 2;
                break;
            case "+":
            case "-":
                priority = 3;
                break;
            case ">>":
            case "<<":
            case ">>>":
            case ">":
            case "<":
                priority = 4;
                break;
            case "&":
                priority = 5;
                break;
            case "^":
                priority = 6;
                break;
            case "|":
                priority = 7;
                break;
            default:
                throw new CompositeHandleException("UnknownElement in string " + "\"" + operation + "\"");
        }
        return priority;
    }

    public ArrayDeque<String> changeInfixToPostfixNotation(List<String> list) throws CompositeHandleException {
        ArrayDeque<String> output = new ArrayDeque<>();
        ArrayDeque<String> operation = new ArrayDeque<>();

        for (String s : list
                ) {
            Scanner scanner = new Scanner(s);
            if (scanner.hasNextInt()) {
                output.add(s);
            } else if (operation.isEmpty()) {
                operation.push(s);
            } else if (s.equals(")")) {
                while (!operation.peek().equals("(")) {
                    output.add(operation.pop());
                }
                operation.pop();
            } else if (!operation.isEmpty()
                    && checkPriority(operation.peek()) < checkPriority(s)
                    && !operation.peek().equals("(")) {
                output.add(operation.pop());
                operation.push(s);
            } else if (!operation.isEmpty()) {
                if (operation.peek().equals(">") && s.equals(">")
                        || operation.peek().equals(">>") && s.equals(">")
                        || operation.peek().equals("<") && s.equals("<")) {
                    operation.push(operation.pop() + s);
                } else {
                    operation.push(s);
                }
            } else {
                operation.push(s);
            }
        }
        if (!operation.isEmpty()) {
            output.add(operation.pop());
        }
        logger.debug(output);
        return output;
    }
}
