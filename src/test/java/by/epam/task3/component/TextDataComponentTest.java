package by.epam.task3.component;

import by.epam.task3.composite.TextDataComposite;
import by.epam.task3.parser.ParserToParagraph;
import by.epam.task3.reader.SourceReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TextDataComponentTest {
    private SourceReader reader;
    private ParserToParagraph parser;
    private TextDataComposite textDataComponent;
    private static Logger logger;


    @BeforeMethod
    public void setUp() throws Exception {
        logger = LogManager.getLogger();
        reader = new SourceReader();
        parser = new ParserToParagraph();
        textDataComponent = (TextDataComposite) parser.parseText(reader.readSource("data/data.txt"));
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
    public void testAdd() throws Exception {
    }

    @Test
    public void testRemove() throws Exception {
    }

    @Test
    public void testGetChild() throws Exception {
    }
}