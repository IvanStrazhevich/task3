package by.epam.task3.action;

import by.epam.task3.composite.DataLevel;
import by.epam.task3.composite.TextDataComponent;
import by.epam.task3.parser.TextFromPunctSplitter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    private int countAppearance(TextDataComponent textDataComponent, char symbol) {
        int k = 0;
        for (TextDataComponent word : textDataComponent.selectList()
                ) {
            for (TextDataComponent leaf : word.selectList()) {
                if (leaf.toString().charAt(0) == (symbol)) {
                    k++;
                }
            }
        }
        return k;
    }

    public String sortLexemesBySymbolQuantity(TextDataComponent component, char symbol) {
        StringBuffer stringBuffer = new StringBuffer();
        if (component.checkLevel().equals(DataLevel.LEXEM)) {
            ArrayList<TextDataComponent> temp = new ArrayList<>();
            temp.addAll(component.selectList());
            logger.info(temp);
            for (int i = 0; i < temp.size(); i++) {
                TextDataComponent lexeme = temp.get(i);
                int k = countAppearance(lexeme, symbol);
                Comparator<TextDataComponent> comparator;
                comparator = Comparator.comparing(component1 -> k - CompositeAnalyzer.this.countAppearance(component1, symbol));
                comparator = comparator.thenComparing(Object::toString);
                temp.sort(comparator);
                //temp.sort((o1, o2) -> k - countAppearance(o2, symbol));
            }
            logger.info(temp);
            return temp.stream()
                    .map(TextDataComponent::toString)
                    .collect(Collectors.joining(" "));

        } else {
            for (int i = 0; i < component.selectList().size(); i++) {
               stringBuffer.append(sortLexemesBySymbolQuantity(component.getChild(i), symbol));
            }
        }
        return stringBuffer.toString();
    }
}

