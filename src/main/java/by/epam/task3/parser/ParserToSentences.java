package by.epam.task3.parser;

import by.epam.task3.composite.DataLevel;
import by.epam.task3.composite.TextDataComponent;
import by.epam.task3.composite.TextDataComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParserToSentences implements SourceParsable<String,TextDataComponent> {
    private static final String SENTENCE_REG = "(?<=[.?!…])";
    private static Logger logger = LogManager.getLogger();
    private SourceParsable<String,TextDataComponent> nextParser = new ParserToLexemes();


    @Override
    public TextDataComponent parseText(String data) {
        String[] sentences = data.split(SENTENCE_REG);
        TextDataComponent textDataComponent = new TextDataComposite(DataLevel.SENTENCE);
        for (String sentence : sentences
                ) {
            logger.debug(sentence);
            textDataComponent.add(nextParser.parseText(sentence));
        }
        logger.debug(textDataComponent);
        return textDataComponent;
    }
}
