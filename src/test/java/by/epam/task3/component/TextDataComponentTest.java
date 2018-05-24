package by.epam.task3.component;

import by.epam.task3.composite.DataLevel;
import by.epam.task3.composite.TextDataComposite;
import by.epam.task3.parser.ParserToParagraph;
import by.epam.task3.reader.SourceReader;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TextDataComponentTest {
    SourceReader reader;
    ParserToParagraph parser;
    TextDataComposite textDataComponent;


    @BeforeMethod
    public void setUp() throws Exception {
        reader = new SourceReader();
        parser = new ParserToParagraph();
        textDataComponent = (TextDataComposite) parser.parseText(reader.readSource("data/data.txt"));
    }

    @AfterMethod
    public void tearDown() throws Exception {
        reader = null;
        parser = null;
        textDataComponent = null;
    }

    @Test
    public void testToString() throws Exception {
        String actual = textDataComponent.toString();
        String expected = reader.readSource("data/data.txt");
        Assert.assertEquals(actual, expected);
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