package by.epam.task3.parser;

import by.epam.task3.composite.DataLevel;
import by.epam.task3.composite.TextDataComponent;
import by.epam.task3.composite.TextDataComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//joining() посмореть
public class ParserToSentences implements SourceParsable {
    private static Logger logger = LogManager.getLogger();
    private SourceParsable nextParser = new ParserToLexemes();
    private static final String SENTENCE_REG = "(.*[\\p{Punct}[\\.\\!\\?]]*\\s*)";

    @Override
    public TextDataComponent parseText(String data) {
        TextDataComponent textDataComponent = new TextDataComposite(DataLevel.SENTENCE);
        Pattern patternSentence = Pattern.compile(SENTENCE_REG);
        Matcher matcherSentence = patternSentence.matcher(data);
        while (matcherSentence.find()) {
            String sentence = matcherSentence.group();
            logger.info(sentence);
            textDataComponent.add(nextParser.parseText(sentence));
        }
        logger.info(textDataComponent);
        return textDataComponent;
    }
}
