package by.epam.task3.parser;

import by.epam.task3.composite.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ParserToWords implements SourceParsable<String, TextDataComponent> {
    private static Logger logger = LogManager.getLogger();
    private SourceParsable<String, TextDataComponent> nextParser = new ParserToChars();

    @Override
    public TextDataComponent parseText(String data) {
        List<String> words = TextFromPunctSplitter.splitTextFromPuncts(data);
        logger.debug((words));
        TextDataComponent textDataComponent = new TextDataComposite(DataLevel.WORD);
        for (String word : words) {
                textDataComponent.add(nextParser.parseText(word));
        }
        logger.debug(textDataComponent);
        return textDataComponent;
    }
}
