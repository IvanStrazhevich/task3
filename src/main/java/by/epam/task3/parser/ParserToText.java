package by.epam.task3.parser;

import by.epam.task3.composite.DataLevel;
import by.epam.task3.composite.TextDataComponent;
import by.epam.task3.composite.TextDataComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ParserToText implements SourceParsable<String,TextDataComponent> {
    private static Logger logger = LogManager.getLogger();
    private SourceParsable<String,TextDataComponent> nextParser = new ParserToParagraph();

    @Override
    public TextDataComponent parseText(String data) {
        logger.debug(data);
        TextDataComponent textDataComponent = new TextDataComposite(DataLevel.TEXT);
            textDataComponent.add(nextParser.parseText(data));
        logger.debug(textDataComponent);
        return textDataComponent;
    }

}

