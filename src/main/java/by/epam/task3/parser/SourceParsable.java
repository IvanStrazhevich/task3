package by.epam.task3.parser;

public interface SourceParsable<T,R> {
    R parseText(T data);
}

