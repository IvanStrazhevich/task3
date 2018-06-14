package by.epam.task3.composite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class TextDataComposite implements TextDataComponent {
    private LinkedList<TextDataComponent> components;
    private DataLevel level;

    public TextDataComposite(DataLevel level) {
        this.level = level;
        this.components = new LinkedList<>();
    }

    @Override
    public String toString() {
        ArrayList<String> strings = new ArrayList<>();
        if (level == DataLevel.LEXEME || level == DataLevel.SENTENCE
                || level == DataLevel.PARAGRAPH || level == DataLevel.TEXT) {

            for (TextDataComponent textDataComponent : components) {
                strings.add(textDataComponent.toString());
            }
            return strings.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(" "));
        } else {
            for (TextDataComponent textDataComponent : components) {
                strings.add(textDataComponent.toString());
            }
            return strings.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining());
        }
    }

    @Override
    public DataLevel checkLevel() {
        return level;
    }

    @Override
    public LinkedList<TextDataComponent> selectList() {
        return components;
    }

    @Override
    public void add(TextDataComponent dataComponent) {
        components.add(dataComponent);
    }

    @Override
    public void remove(TextDataComponent dataComponent) {
        components.remove(dataComponent);
    }

    @Override
    public TextDataComponent getChild(int index) {
        return components.get(index);
    }

    public void setLevel(DataLevel level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextDataComposite composite = (TextDataComposite) o;

        if (components != null ? !components.equals(composite.components) : composite.components != null) return false;
        return level == composite.level;
    }

    @Override
    public int hashCode() {
        int result = components != null ? components.hashCode() : 0;
        result = 31 * result + (level != null ? level.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Object o) {
        return this.toString().compareTo(o.toString());
    }
}
