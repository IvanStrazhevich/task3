package by.epam.task3.interpreter;

import by.epam.task3.exception.ExtendedException;
import by.epam.task3.parser.TextFromPunctSplitter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Scanner;


public class LexemeInterpreter {

    private static Logger logger = LogManager.getLogger();
    private PolishNotationConverter polishConverter = new PolishNotationConverter();

    public String interpret(String lexeme) throws ExtendedException {
        ArrayDeque<String> polish = polishConverter.changeInfixToPostfixNotation(
                TextFromPunctSplitter.splitTextFromPuncts(lexeme));
        Context<Integer> context = new Context<>();
        logger.debug("Source lexeme: " + lexeme);
        logger.debug("Converted to polish notation: " + polish);
        for (String s : polish
                ) {
            Scanner scanner = new Scanner(s);
            AbstractExpression<Integer> expression;
            if (scanner.hasNextInt()) {
                logger.debug(s);
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
                    case ">>>":                                 //ask about this strange behavior
                        expression = (Context<Integer> c) -> {
                            int a, b;
                            a = c.pop();
                            b = c.pop();
                            c.push(b >>> a);
                        };
                        break;
                    case "<<":                                  //ask about this strange behavior
                        expression = (Context<Integer> c) -> {
                            int a, b;
                            a = c.pop();
                            b = c.pop();
                            c.push(b << a);
                        };
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
}
