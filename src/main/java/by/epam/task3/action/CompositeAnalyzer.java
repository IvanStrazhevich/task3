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
        if (component.checkLevel().equals(DataLevel.LEXEME)) {
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
                stringBuffer.append(sortSentencesByWordSize(component.getChild(i)));
            }
        }
        return stringBuffer.toString();
    }

    private int countSymbolAppearance(TextDataComponent textDataComponent, char symbol) {
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
        if (component.checkLevel().equals(DataLevel.LEXEME)) {
            ArrayList<TextDataComponent> temp = new ArrayList<>();
            temp.addAll(component.selectList());
            logger.debug(temp);
            for (int i = 0; i < temp.size(); i++) {
                TextDataComponent lexeme = temp.get(i);
                int k = countSymbolAppearance(lexeme, symbol);
                Comparator<TextDataComponent> comparator;
                comparator = Comparator.comparing(component1 -> k - CompositeAnalyzer.this.countSymbolAppearance(component1, symbol));
                comparator = comparator.thenComparing(Object::toString);
                temp.sort(comparator);
            }
            logger.debug(temp);
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

    private TreeMap<TextDataComponent, Integer> createSymbolInLexemesMap(TextDataComponent component, char symbol) {
        TreeMap<TextDataComponent, Integer> textMap = new TreeMap<>();
        if (component.checkLevel().equals(DataLevel.LEXEME)) {
            ArrayList<TextDataComponent> temp = new ArrayList<>();
            temp.addAll(component.selectList());
            logger.debug(temp);
            int k = 0;
            TreeMap<TextDataComponent, Integer> lexemeMap = new TreeMap<>();
            for (TextDataComponent lexeme : temp) {
                k = countSymbolAppearance(lexeme, symbol);
                lexemeMap.put(lexeme, k);
            }
            logger.debug(temp);
            return lexemeMap;
        } else {
            for (int i = 0; i < component.selectList().size(); i++) {
                textMap.putAll(createSymbolInLexemesMap(component.getChild(i), symbol));
            }
        }
        return textMap;
    }

    public String sortTextLexemesBySymbolQuantityThenAlfabetically(TextDataComponent component, char symbol) {
        StringBuffer stringBuffer = new StringBuffer();
        TreeMap<TextDataComponent, Integer> lexemeMap = createSymbolInLexemesMap(component, symbol);
        NavigableMap<TextDataComponent,Integer> decendingMap = lexemeMap.descendingMap();
        LinkedList<TextDataComponent> keyList = new LinkedList<>();
        decendingMap.forEach((TextDataComponent k,Integer v)->{
           keyList.add(k);
        });
        keyList.sort(Comparator.comparing(decendingMap::get).thenComparing(Comparator.comparing(Object::toString)));
        Collections.reverse(keyList);
        for (TextDataComponent lexeme: keyList
             ) {
            stringBuffer.append(lexeme + " ");
        }
        logger.info(stringBuffer);
        return stringBuffer.toString();
    }
}

