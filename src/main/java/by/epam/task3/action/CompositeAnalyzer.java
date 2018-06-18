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

    public String showSortedParagraphsBySentenceLength(TextDataComponent component) {
        return component.selectList().stream()
                .sorted((o1, o2) -> o2.selectList().size() - o1.selectList().size())
                .map(Object::toString)
                .collect(Collectors.joining(" "));
    }

    public String showSortedSentencesByLexemeSize(TextDataComponent component) {
        StringBuffer stringBuffer = new StringBuffer();
        if (component.checkLevel().equals(DataLevel.LEXEME)) {
            return component.selectList().stream()
                    .map(TextDataComponent::toString)
                    .sorted((o1, o2) -> o2.length() - o1.length())
                    .collect(Collectors.joining(" "));
        } else {
            for (int i = 0; i < component.selectList().size(); i++) {
                stringBuffer.append(showSortedSentencesByLexemeSize(component.getChild(i)));
            }
        }
        return stringBuffer.toString();
    }

    public String showSortedSentencesByWordSize(TextDataComponent component) {
        StringBuffer stringBuffer = new StringBuffer();
        if (component.checkLevel().equals(DataLevel.LEXEME)) {
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
                stringBuffer.append(showSortedSentencesByWordSize(component.getChild(i)));
            }
        }
        return stringBuffer.toString();
    }

    private int countSymbolAppearance(TextDataComponent lexeme, char symbol) {
        return lexeme.selectList().stream()
                .mapToInt(word -> (int) word.selectList()
                        .stream()
                        .filter(leaf -> leaf.toString().charAt(0) == (symbol))
                        .count())
                .sum();
    }

    public String showSortedLexemesInSentencesBySymbolQuantity(TextDataComponent component, char symbol) {
        StringBuffer stringBuffer = new StringBuffer();
        if (component.checkLevel().equals(DataLevel.LEXEME)) {
            ArrayList<TextDataComponent> temp = new ArrayList<>(component.selectList());
            logger.debug(temp);
            for (int i = 0; i < temp.size(); i++) {
                TextDataComponent lexeme = temp.get(i);
                int k = countSymbolAppearance(lexeme, symbol);
                Comparator<TextDataComponent> comparator;
                comparator = Comparator.comparing(comp -> k - CompositeAnalyzer.this.countSymbolAppearance(comp, symbol));
                comparator = comparator.thenComparing(Object::toString);
                temp.sort(comparator);
            }
            logger.debug(temp);
            return temp.stream()
                    .map(TextDataComponent::toString)
                    .collect(Collectors.joining(" "));
        } else {
            for (int i = 0; i < component.selectList().size(); i++) {

                stringBuffer.append(showSortedLexemesInSentencesBySymbolQuantity(component.getChild(i), symbol));
            }
        }
        return stringBuffer.toString();
    }

    //returns the map of all lexemes as a key and number of symbol appearance as a value
    private TreeMap<TextDataComponent, Integer> createSymbolQuantityInLexemesMap(TextDataComponent component, char symbol) {
        TreeMap<TextDataComponent, Integer> textMap = new TreeMap<>();
        if (component.checkLevel().equals(DataLevel.LEXEME)) {
            ArrayList<TextDataComponent> temp = new ArrayList<>(component.selectList());
            logger.debug(temp);
            TreeMap<TextDataComponent, Integer> lexemeMap = new TreeMap<>();
            for (TextDataComponent lexeme : temp) {
                int k = countSymbolAppearance(lexeme, symbol);
                lexemeMap.put(lexeme, k);
            }
            logger.debug(temp);
            return lexemeMap;
        } else {
            for (int i = 0; i < component.selectList().size(); i++) {
                textMap.putAll(createSymbolQuantityInLexemesMap(component.getChild(i), symbol));
            }
        }
        return textMap;
    }

    public String showTextLexemesSortedBySymbolQuantityThenAlphabetically(TextDataComponent component, char symbol) {
        // We are getting the map of all lexemes as a key and number of symbol appearance as a value
        TreeMap<TextDataComponent, Integer> lexemeMap = createSymbolQuantityInLexemesMap(component, symbol);

        // We are converting map to the list of lexemes, and sort it by symbol appearance, using map value,
        // then descending order, then reverse list to get ascending order
        LinkedList<TextDataComponent> keyList = new LinkedList<>();
        lexemeMap.forEach((TextDataComponent k, Integer v) -> keyList.add(k));
        keyList.sort(Comparator.comparing(lexemeMap::get).thenComparing(Comparator.comparing(Object::toString)));
        Collections.reverse(keyList);
        return keyList.stream()
                .map(TextDataComponent::toString)
                .collect(Collectors.joining(" "));
    }
}

