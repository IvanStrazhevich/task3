package by.epam.task3.composite;

import java.util.LinkedList;

public class TextDataLeaf implements TextDataComponent {
    private LeafType leafType;
    private char symbol;

    public TextDataLeaf(LeafType leafType, char symbol) {
        this.leafType = leafType;
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return Character.toString(symbol);
    }

    @Override
    public DataLevel checkLevel() {
        throw new UnsupportedOperationException();
    }

    @Override
    public LinkedList<TextDataComponent> selectList() {
        throw new UnsupportedOperationException();
    }


    @Override
    public void add(TextDataComponent dataComponent) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void remove(TextDataComponent dataComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TextDataComponent getChild(int index) {
        throw new UnsupportedOperationException();
    }

    public LeafType getLeafType() {
        return leafType;
    }

    public void setLeafType(LeafType leafType) {
        this.leafType = leafType;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextDataLeaf that = (TextDataLeaf) o;

        if (symbol != that.symbol) return false;
        return leafType == that.leafType;
    }

    @Override
    public int hashCode() {
        int result = leafType != null ? leafType.hashCode() : 0;
        result = 31 * result + (int) symbol;
        return result;
    }
}
