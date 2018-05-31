package by.epam.task3.interpreter;

import java.util.ArrayDeque;

public class Context <T>{
    private ArrayDeque<T> values = new ArrayDeque<>();

    public T pop() {
        return values.pop();
    }

    public void push(T value) {
        values.push(value);
    }


}
