package by.epam.task3.interpreter;

import java.util.ArrayDeque;

public class Context {
    private ArrayDeque<Integer> values = new ArrayDeque<>();

    public Integer pop() {
        return values.pop();
    }

    public void push(Integer value) {
        values.push(value);
    }
}
