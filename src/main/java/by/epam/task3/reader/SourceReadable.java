package by.epam.task3.reader;

import by.epam.task3.exception.CompositeHandleException;

public interface SourceReadable<T> {
    T readSource(T routeDataSource) throws CompositeHandleException;
}
