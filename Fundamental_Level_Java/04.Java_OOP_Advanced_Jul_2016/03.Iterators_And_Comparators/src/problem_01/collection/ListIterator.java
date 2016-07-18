package problem_01.collection;

import problem_01.interfaces.CustomIterable;

public class ListIterator<T> implements CustomIterable<T> {

    private T[] collection;
    private int index;

    public ListIterator(T... args) {
        this.setCollection(args);
        this.setIndex(0);
    }

    private void setCollection(T[] collection) {
        this.collection = collection;
    }

    private void setIndex(int index) {
        this.index = index;
    }

    @Override
    public boolean move() {
        if (this.hasNext()) {
            this.incrementIndex();
            return true;
        }
        return false;
    }

    @Override
    public boolean hasNext() {
        if (this.index < this.collection.length - 1) {
            return true;
        }
        return false;
    }

    @Override
    public T print() {
        if (this.collection.length == 0) {
            throw new IndexOutOfBoundsException("Invalid Operation!");
        }
        return this.collection[index];
    }

    private void incrementIndex() {
        this.index++;
    }
}
