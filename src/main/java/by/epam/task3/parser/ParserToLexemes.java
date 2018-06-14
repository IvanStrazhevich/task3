package by.epam.task3.parser;

import by.epam.task3.composite.*;
import by.epam.task3.exception.CompositeHandleException;
import by.epam.task3.interpreter.LexemeInterpreter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserToLexemes implements SourceParsable<String, TextDataComponent> {
    private static Logger logger = LogManager.getLogger();
    private static final String LEXEME_DIVIDER = "(?>\\s)";
    private static final String NOT_WORD = "(\\p{Punct}*?\\d+?\\p{Punct}*?)+?\\s*";
    private SourceParsable<String, TextDataComponent> nextParser = new ParserToWords();
    private LexemeInterpreter interpreter = new LexemeInterpreter();


    @Override
    public TextDataComponent parseText(String data) {
        String[] lexemes = data.split(LEXEME_DIVIDER);
        TextDataComponent textDataComponent = new TextDataComposite(DataLevel.LEXEME);
        Pattern pattern = Pattern.compile(NOT_WORD);
        for (String lexeme : lexemes
                ) {
            Matcher matcher = pattern.matcher(lexeme);
            if (matcher.matches()) {
                try {
                    textDataComponent.add(nextParser.parseText(interpreter.interpret(lexeme)));
                } catch (CompositeHandleException e) {
                    logger.error("Lexeme interpreter error", e);
                }
            } else {
                textDataComponent.add(nextParser.parseText(lexeme));
            }
        }
        logger.debug(textDataComponent);
        return textDataComponent;
    }
}
