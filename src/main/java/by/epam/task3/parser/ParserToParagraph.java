package by.epam.task3.parser;

import by.epam.task3.composite.DataLevel;
import by.epam.task3.composite.TextDataComponent;
import by.epam.task3.composite.TextDataComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ParserToParagraph implements SourceParsable<TextDataComposite> {
    private static Logger logger = LogManager.getLogger();
    private SourceParsable nextParser = new ParserToSentences();
    private static final String PARAGRAPH_REGEX = "(?=\\s{4}.*)";

    @Override
    public TextDataComponent parseText(String data) {
        logger.debug(data);
        String[] paragraphs = data.split(PARAGRAPH_REGEX);
        TextDataComponent textDataComponent = new TextDataComposite(DataLevel.PARAGRAPH);
        for (String paragraph : paragraphs
                ) {
            logger.debug(paragraph);
            textDataComponent.add(nextParser.parseText(paragraph));
        }
        logger.debug(textDataComponent);
        return textDataComponent;
    }

}

