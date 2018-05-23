package by.epam.task3.parser;

import by.epam.task3.composite.DataLevel;
import by.epam.task3.composite.TextDataComponent;
import by.epam.task3.composite.TextDataComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserToWords implements SourceParsable {
    private static Logger logger = LogManager.getLogger();
    private SourceParsable nextParser = new ParserToChars();
    private TextDataComponent textDataComponent;
    private static final String WORD = "((\\p{Punct})*\\w*(\\p{Punct})*\\s*)";

    @Override
    public TextDataComponent parseText(String data) {
        textDataComponent = new TextDataComposite(DataLevel.WORD);
        Pattern patternWord = Pattern.compile(WORD);
        Matcher matcherWord = patternWord.matcher(data);
        while (matcherWord.find()) {
            String word = matcherWord.group();
            textDataComponent.add(nextParser.parseText(word));
        }
        logger.info(textDataComponent);
        return textDataComponent;
    }
}
