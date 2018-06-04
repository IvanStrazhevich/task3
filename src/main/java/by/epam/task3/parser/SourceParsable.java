package by.epam.task3.parser;

import by.epam.task3.composite.TextDataComponent;

public interface SourceParsable<T,R> {
    R parseText(T data);
}

