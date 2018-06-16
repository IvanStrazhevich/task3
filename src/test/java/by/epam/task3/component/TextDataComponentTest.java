package by.epam.task3.component;

import by.epam.task3.composite.TextDataComponent;
import by.epam.task3.composite.TextDataComposite;
import by.epam.task3.parser.ParserToText;
import by.epam.task3.parser.SourceParsable;
import by.epam.task3.reader.SourceReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TextDataComponentTest {
    private SourceReader reader;
    private SourceParsable<String,TextDataComponent> parser;
    private TextDataComposite textDataComponent;
    private static Logger logger;
    private static final String SOME_TEXT = "Some text.";


    @BeforeMethod
    public void setUp() throws Exception {
        logger = LogManager.getLogger();
        reader = new SourceReader();
        parser = new ParserToText();
        textDataComponent =  (TextDataComposite) parser.parseText(reader.readSource("data/data.txt"));
    }

    @AfterMethod
    public void tearDown() {
        reader = null;
        parser = null;
        textDataComponent = null;
        logger = null;
    }

    @Test
    public void testToString() throws Exception {
        String actual = textDataComponent.toString();
        String expected = reader.readSource("data/data.txt");
        logger.info("Actual after interpret:" + actual);
        logger.info("Source text:" + expected);
        Assert.assertNotEquals(actual, expected);
    }

    @Test
    public void testAdd() {
        String expected = textDataComponent.toString() + " Some text.";
        textDataComponent.add(parser.parseText(SOME_TEXT));
        String actual = textDataComponent.toString();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testRemove() {
        String expected = textDataComponent.toString();
        logger.info("before adding" + textDataComponent);
        textDataComponent.add(parser.parseText(SOME_TEXT));
        logger.info("after adding" + textDataComponent);
        textDataComponent.remove(parser.parseText(SOME_TEXT));
        logger.info("after deleting" + textDataComponent);
        String actual = textDataComponent.toString();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetChild(){
        String expected = (textDataComponent.getChild(0).getChild(0).getChild(0).getChild(4).toString());
        String actual = "It";
        Assert.assertEquals(actual, expected);
    }
}