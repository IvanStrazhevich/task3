package by.epam.task3.parser;

import by.epam.task3.composite.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ParserToChars implements SourceParsable {
    private static Logger logger = LogManager.getLogger();

    @Override
    public TextDataComponent parseText(String data) {
        TextDataComponent textDataComponent = new TextDataComposite(DataLevel.CHAR);
        char[] chars = data.toString().toCharArray();
        for (char element : chars
                ) {
            textDataComponent.add(new TextDataLeaf(LeafType.TEXT, element));
        }
        logger.info(textDataComponent);
        return textDataComponent;
    }
}
