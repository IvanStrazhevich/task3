package by.epam.task3.reader;

import by.epam.task3.exception.ExtendedException;

public interface SourceReadable<T> {
    String readSource(String routeDataSource) throws ExtendedException;
}
