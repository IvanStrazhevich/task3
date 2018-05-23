package by.epam.task3.reader;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SourceReaderTest {
    private SourceReader sourceReader;
    private static final String ORIGINAL_TEXT = "It has survived - not only (five) centuries," +
            " but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially" +
            " ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(^5|6&47)|3)|2)|1) " +
            "with the release of Letraset sheets containing Lorem Ipsum passages, and more recently " +
            "with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum." +
            " It is a long established fact that a reader will be distracted by the readable content of " +
            "a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 " +
            "Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using " +
            "(Content here), content here', making it look like readable English." +
            " It is a (^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when " +
            "looking at its layout." +
            " Bye.";

    @BeforeMethod
    public void setUp() {
        sourceReader = new SourceReader();
    }

    @AfterMethod
    public void tearDown() {
        sourceReader = null;
    }

    @Test
    public void testReadSource() throws Exception {
        String actual = sourceReader.readSource("data/data.txt");
        String expected = ORIGINAL_TEXT;
        Assert.assertEquals(actual, expected);
    }
}