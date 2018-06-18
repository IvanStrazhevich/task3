package by.epam.task3.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

public class LexemeInterpreterTest {
    private static Logger logger;
    private LexemeInterpreter lexemeInterpreter;

    @DataProvider
    public Object[][] interpretLexeme() {
        return new Object[][]{
                {"13<<2", "52"},
                {"25>>3", "3"},
                {"~6&9|(3&4)", "9"},
                {"5|(1&2&(3|(4&(8^5|6&47)|3)|2)|1)", "5"},
                {"(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78", "78"},
                {"(4^5|1&2<<(2|5>>2&71))|1200", "1201"},
        };
    }

    @BeforeClass
    public void before() {
        logger = LogManager.getLogger();
        logger.info("Results calculation:");
        logger.info(13 << 2);
        logger.info(25 >> 3);
        logger.info(~6 & 9 | (3 & 4));
        logger.info(5 | (1 & 2 & (3 | (4 & (8 ^ 5 | 6 & 47) | 3) | 2) | 1));
        logger.info((~71 & (2 & 3 | (3 | (2 & 1 >> 2 | 2) & 2) | 10 & 2)) | 78);
        logger.info((4 ^ 5 | 1 & 2 << (2 | 5 >> 2 & 71)) | 1200);
    }

    @BeforeMethod
    public void setUp() {
        logger = LogManager.getLogger();
        lexemeInterpreter = new LexemeInterpreter();
    }

    @AfterMethod
    public void tearDown() {
        lexemeInterpreter = null;
        logger = null;

    }

    @Test(dataProvider = "interpretLexeme")
    public void testInterpretIfEqualsTrue(String lexeme, String expected) throws Exception {
        String actual = lexemeInterpreter.interpret(lexeme);
        logger.info("Actual after interpret:" + actual);
        logger.info("Expected result:" + expected);
        Assert.assertEquals(actual, expected);
    }
}