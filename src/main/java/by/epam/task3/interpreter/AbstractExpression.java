package by.epam.task3.interpreter;

@FunctionalInterface
public interface AbstractExpression<T> {
    void interpret(Context<T> c);
}
