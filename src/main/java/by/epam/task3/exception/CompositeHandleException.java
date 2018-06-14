package by.epam.task3.exception;

public class CompositeHandleException extends Exception {
    public CompositeHandleException() {
    }

    public CompositeHandleException(String message) {
        super(message);
    }

    public CompositeHandleException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompositeHandleException(Throwable cause) {
        super(cause);
    }

}
