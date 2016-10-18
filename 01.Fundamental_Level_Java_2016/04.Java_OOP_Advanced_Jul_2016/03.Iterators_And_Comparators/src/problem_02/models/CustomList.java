package problem_02.models;

import java.util.Iterator;

public class CustomList<T> implements Iterable<T> {

    private T[] collection;
    private int index;

    public CustomList(T... args) {
        this.setCollection(args);
        this.setIndex(0);
    }

    private void setCollection(T[] collection) {
        this.collection = collection;
    }

    private void setIndex(int index) {
        this.index = index;
    }

    public boolean move() {
        if (this.hasNext()) {
            this.incrementIndex();
            return true;
        }
        return false;
    }

    public boolean hasNext() {
        if (this.index < this.collection.length - 1) {
            return true;
        }
        return false;
    }

    public T print() {
        if (this.collection.length == 0) {
            throw new UnsupportedOperationException("Invalid Operation!");
        }
        return this.collection[index];
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> customIterator = new Iterator<T>() {
            int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < collection.length;
            }

            @Override
            public T next() {
                return collection[currentIndex++];
            }
        };
        return customIterator;
    }

    private void incrementIndex() {
        this.index++;
    }
}