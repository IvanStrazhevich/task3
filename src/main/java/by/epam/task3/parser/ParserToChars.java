package by.epam.task3.parser;

import by.epam.task3.composite.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ParserToChars implements SourceParsable<String, TextDataComponent> {
    private static Logger logger = LogManager.getLogger();
    private static final String PUNCT = "\\p{Punct}+";

    @Override
    public TextDataComponent parseText(String data) {
        TextDataComponent textDataComponent = new TextDataComposite(DataLevel.CHAR);
        char[] chars = data.toCharArray();
        for (char element : chars
                ) {
            textDataComponent.add(new TextDataLeaf(LeafType.TEXT, element));
        }
        Pattern patternPunct = Pattern.compile(PUNCT);

        for (TextDataComponent c : textDataComponent.selectList()
                ) {
            Matcher matcherPunct = patternPunct.matcher(c.toString());
            while (matcherPunct.find()) {
                TextDataLeaf leaf = (TextDataLeaf) c;
                leaf.setLeafType(LeafType.PUNCT);
                c = leaf;
            }
            logger.debug(c.toString() + " " + ((TextDataLeaf) c).getLeafType());
        }
        logger.debug(textDataComponent);
        return textDataComponent;
    }
}
