package by.epam.task3.action;

import by.epam.task3.composite.TextDataComposite;
import by.epam.task3.parser.ParserToParagraph;
import by.epam.task3.reader.SourceReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CompositeAnalyzerTest {
    private SourceReader reader;
    private ParserToParagraph parser;
    private TextDataComposite textDataComponent;
    private static Logger logger;
    private CompositeAnalyzer analyzer;

    @BeforeMethod
    public void setUp() throws Exception {
        logger = LogManager.getLogger();
        reader = new SourceReader();
        parser = new ParserToParagraph();
        textDataComponent = (TextDataComposite) parser.parseText(reader.readSource("data/data.txt"));
        analyzer = new CompositeAnalyzer();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        reader = null;
        parser = null;
        textDataComponent = null;
        analyzer = null;
        logger = null;

    }

    @Test
    public void testSortParagraphsBySentenceNumber() throws Exception {
        String actual = analyzer.sortParagraphsBySentenceLength(textDataComponent);
        String expected = reader.readSource("data/data.txt");
        logger.info("Actual after interpret:" + actual);
        logger.info("Source text:" + expected);
        Assert.assertNotEquals(actual, expected);
    }

    @Test
    public void testSortSentencesByWordsQuantity() throws Exception {
        String actual = analyzer.sortSentencesByLexemeSize(textDataComponent);
        String expected = reader.readSource("data/data.txt");
        logger.info("Actual after interpret:" + actual);
        logger.info("Source text:" + expected);
        Assert.assertNotEquals(actual, expected);
    }

    @Test
    public void testSortSentencesByWordSize() throws Exception {
        String actual = analyzer.sortSentencesByWordSize(textDataComponent);
        String expected = reader.readSource("data/data.txt");
        logger.info("Actual after interpret:" + actual);
        logger.info("Source text:" + expected);
        Assert.assertNotEquals(actual, expected);
    }
}