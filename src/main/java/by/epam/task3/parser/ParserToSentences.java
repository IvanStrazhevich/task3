package by.epam.task3.parser;

import by.epam.task3.composite.DataLevel;
import by.epam.task3.composite.TextDataComponent;
import by.epam.task3.composite.TextDataComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//joining() посмореть
public class ParserToSentences implements SourceParsable {
    private static Logger logger = LogManager.getLogger();
    private SourceParsable nextParser = new ParserToLexemes();
    private static final String SENTENCE_REG = "(?<=[.?!…])";

    @Override
    public TextDataComponent parseText(String data) {
        String[] sentences = data.split(SENTENCE_REG);
        TextDataComponent textDataComponent = new TextDataComposite(DataLevel.SENTENCE);
        for (String sentence : sentences
                ) {
            logger.info(sentence);
            textDataComponent.add(nextParser.parseText(sentence));
        }
        logger.info(textDataComponent);
        return textDataComponent;
    }
}
