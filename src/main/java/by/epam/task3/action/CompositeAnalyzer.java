package by.epam.task3.action;

import by.epam.task3.composite.DataLevel;
import by.epam.task3.composite.TextDataComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

public class CompositeAnalyzer {
    private static Logger logger = LogManager.getLogger();

    public String sortParagraphsBySentenceLength(TextDataComponent component) {
        StringBuffer stringBuffer = new StringBuffer();
        component.selectList().sort((o1, o2) -> o2.selectList().size() - o1.selectList().size());
        logger.info(stringBuffer.append(component.toString()));
        return stringBuffer.toString();
    }

    public String sortSentencesByLexemeSize(TextDataComponent component) {
        StringBuffer stringBuffer = new StringBuffer();
        if (component.checkLevel().equals(DataLevel.LEXEM)) {
            LinkedList<TextDataComponent> temp = component.selectList();
            logger.info(temp);
            temp.sort((o1, o2) -> o2.toString().length() - o1.toString().length());
            logger.info(stringBuffer.append(component.toString()));
        } else {
            for (int i = 0; i < component.selectList().size(); i++) {
                stringBuffer.append(sortSentencesByLexemeSize(component.getChild(i)));
            }
        }
        return stringBuffer.toString();
    }

    public String sortSentencesByWordSize(TextDataComponent component) {
        StringBuffer stringBuffer = new StringBuffer();
        if (component.checkLevel().equals(DataLevel.LEXEM)) {
            for (int i = 0; i < component.selectList().size(); i++) {
                LinkedList<TextDataComponent> temp=new LinkedList<>();
                temp.add(component.getChild(i));
                logger.info(temp);
                temp.sort((o1, o2) -> o2.toString().length() - o1.toString().length());
            }
            logger.info(stringBuffer.append(component.toString()));
        } else {
            for (int i = 0; i < component.selectList().size(); i++) {
                stringBuffer.append(sortSentencesByWordSize(component.getChild(i)));
            }
        }
        return stringBuffer.toString();
    }
}

