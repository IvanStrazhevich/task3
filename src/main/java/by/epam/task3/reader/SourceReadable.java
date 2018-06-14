package by.epam.task3.reader;

import by.epam.task3.exception.CompositeHandleException;

public interface SourceReadable<T> {
    String readSource(String routeDataSource) throws CompositeHandleException;
}
