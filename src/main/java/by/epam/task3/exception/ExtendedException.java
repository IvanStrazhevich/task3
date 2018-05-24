package by.epam.task3.exception;

public class ExtendedException extends Exception {
    public ExtendedException() {
    }

    public ExtendedException(String message) {
        super(message);
    }

    public ExtendedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExtendedException(Throwable cause) {
        super(cause);
    }

}
