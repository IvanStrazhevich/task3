package by.epam.task3.composite;

import java.util.LinkedList;

public interface TextDataComponent extends Comparable{

    LinkedList<TextDataComponent> selectList();

    void add(TextDataComponent dataComponent);

    void remove(TextDataComponent dataComponent);

    TextDataComponent getChild(int index);

    DataLevel checkLevel();

    String toString();


}
