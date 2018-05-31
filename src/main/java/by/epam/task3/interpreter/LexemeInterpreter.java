package by.epam.task3.interpreter;

import by.epam.task3.exception.ExtendedException;
import by.epam.task3.parser.DigitsFromOperationSplitter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Scanner;


public class LexemeInterpreter {

    private static Logger logger = LogManager.getLogger();
    private AbstractExpression<Integer> expression;
    private PolishNotationConverter polishConverter = new PolishNotationConverter();
    private DigitsFromOperationSplitter splitter = new DigitsFromOperationSplitter();


    public String interpret(String lexeme) throws ExtendedException {
        ArrayDeque<String> polish = polishConverter.changeInfixToPostfixNotation(
                splitter.splitDigitsFromOperations(lexeme));
        Context<Integer> context = new Context<>();
        logger.debug("Source lexeme: " + lexeme);
        logger.debug("Converted to polish notation: " + polish);
        for (String s : polish
                ) {
            Scanner scanner = new Scanner(s);
            if (scanner.hasNextInt()) {
                expression = (c) -> c.push(scanner.nextInt());
            } else {
                logger.debug(s);
                switch (s) {
                    case "~":
                        expression = (c) -> c.push(~c.pop());
                        break;
                    case "^":
                        expression = (c) -> c.push(c.pop() ^ c.pop());
                        break;
                    case "|":
                        expression = (c) -> c.push(c.pop() | c.pop());
                        break;
                    case "&":
                        expression = (c) -> c.push(c.pop() & c.pop());
                        break;
                    case ">>":
                        expression = (c) -> c.push(c.pop() >> c.pop());
                        break;
                    case "<<":
                        expression = (c) -> c.push(c.pop() << c.pop());
                        break;
                    default:
                        throw new ExtendedException("No such symbol to interpret" + s);
                }
            }
            expression.interpret(context);
        }
        return context.pop().toString();
    }

    public void setPolishConverter(PolishNotationConverter polishConverter) {
        this.polishConverter = polishConverter;
    }

    public void setSplitter(DigitsFromOperationSplitter splitter) {
        this.splitter = splitter;
    }
}
