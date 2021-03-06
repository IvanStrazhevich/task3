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
    private static final char SYMBOL = 'e';
    private SourceReader reader;
    private ParserToParagraph parser;
    private TextDataComposite textDataComponent;
    private static Logger logger;
    private CompositeAnalyzer analyzer;
    private String expected;

    @BeforeMethod
    public void setUp() throws Exception {
        logger = LogManager.getLogger();
        reader = new SourceReader();
        parser = new ParserToParagraph();
        textDataComponent = (TextDataComposite) parser.parseText(reader.readSource("data/data.txt"));
        analyzer = new CompositeAnalyzer();
        expected = reader.readSource("data/data.txt");
    }

    @AfterMethod
    public void tearDown() {
        reader = null;
        parser = null;
        textDataComponent = null;
        analyzer = null;
        logger = null;

    }

    @Test
    public void testShowSortedParagraphsBySentenceNumber() {
        String actual = analyzer.showSortedParagraphsBySentenceLength(textDataComponent);
        logger.info("Actual sort paragraphs by sentence quantity: " + actual);
        logger.debug("Source text:" + expected);
        Assert.assertNotEquals(actual, expected);
    }

    @Test
    public void testShowSortedSentencesByLexemesSize() {
        String actual = analyzer.showSortedSentencesByLexemeSize(textDataComponent);
        logger.info("Actual sort lexemes in sentence by length: " + actual);
        logger.debug("Source text:" + expected);
        Assert.assertNotEquals(actual, expected);
    }

    @Test
    public void testShowSortedSentencesByWordSize() {
        String actual = analyzer.showSortedSentencesByWordSize(textDataComponent);
        logger.info("Actual sort words in sentence by length: " + actual);
        logger.debug("Source text:" + expected);
        Assert.assertNotEquals(actual, expected);
    }

    @Test
    public void testShowSortedLexemesBySymbolQuantityInTextBySentences() {
        String actual = analyzer.showSortedLexemesInSentencesBySymbolQuantity(textDataComponent, SYMBOL);
        logger.info("Actual sort lexemes in sentences by symbol " + SYMBOL + " appearance: " + actual);
        logger.debug("Source text:" + expected);
        Assert.assertNotEquals(actual, expected);
    }

    @Test
    public void testShowSortedLexemesBySymbolQuantityInText() {
        String actual = analyzer.showTextLexemesSortedBySymbolQuantityThenAlphabetically(textDataComponent, SYMBOL);
        logger.info("Actual sort text lexemes by symbol " + SYMBOL + " appearance: " + actual);
        logger.debug("Source text:" + expected);
        Assert.assertNotEquals(actual, expected);
    }
}