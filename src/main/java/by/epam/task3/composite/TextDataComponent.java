package by.epam.task3.composite;

public interface TextDataComponent {
    String toString();
    void add(TextDataComponent dataComponent);
    void remove(TextDataComponent dataComponent);
    TextDataComponent getChild(int index);
}
