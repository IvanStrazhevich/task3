package by.epam.task3.action;

import by.epam.task3.composite.DataLevel;
import by.epam.task3.composite.TextDataComponent;
import by.epam.task3.parser.TextFromPunctSplitter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class CompositeAnalyzer {
    private static Logger logger = LogManager.getLogger();

    public String sortParagraphsBySentenceLength(TextDataComponent component) {
        return component.selectList().stream()
                .sorted((o1, o2) -> o2.selectList().size() - o1.selectList().size())
                .map(Object::toString)
                .collect(Collectors.joining(" "));
    }

    public String sortSentencesByLexemeSize(TextDataComponent component) {
        StringBuffer stringBuffer = new StringBuffer();
        if (component.checkLevel().equals(DataLevel.LEXEM)) {
            return component.selectList().stream()
                    .map(TextDataComponent::toString)
                    .sorted((o1, o2) -> o2.length() - o1.length())
                    .collect(Collectors.joining(" "));
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
            ArrayList<TextDataComponent> temp = new ArrayList<>(component.selectList());
            ArrayList<String> sentenceByWordsTemp = new ArrayList<>();
            for (TextDataComponent lexeme : temp
                    ) {
                sentenceByWordsTemp.addAll(TextFromPunctSplitter.splitTextFromPuncts(lexeme.toString()));
            }
            sentenceByWordsTemp.sort((o1, o2) -> o2.length() - o1.length());
            return sentenceByWordsTemp.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(" "));
        } else {
            for (int i = 0; i < component.selectList().size(); i++) {
                stringBuffer.append(sortSentencesByWordSize(component.getChild(i)));
            }
        }
        return stringBuffer.toString();
    }
}

