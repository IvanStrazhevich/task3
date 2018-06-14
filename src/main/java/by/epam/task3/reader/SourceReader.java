package by.epam.task3.reader;

import by.epam.task3.exception.CompositeHandleException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SourceReader implements SourceReadable<ArrayList<String>> {
    private static Logger logger = LogManager.getLogger();

    public String readSource(String dataSource) throws CompositeHandleException {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<String> compositeDataList = new ArrayList<>();
        if (dataSource != null) {
            Path path = Paths.get(dataSource);
            try (Stream<String> lines = Files.lines(path)) {
                compositeDataList = (ArrayList) lines.collect(Collectors.toList());
            } catch (IOException e) {
                throw new CompositeHandleException(" Source file problem", e);
            }
            logger.debug(compositeDataList + " Source reader result");

            for (String line : compositeDataList
                    ) {
                stringBuffer.append(line);
            }
        } else {
            throw new CompositeHandleException("Null data source");
        }
        return stringBuffer.toString();
    }
}
