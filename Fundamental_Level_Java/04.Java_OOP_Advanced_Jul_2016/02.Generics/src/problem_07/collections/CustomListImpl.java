package problem_07.collections;

import problem_07.interfaces.CustomList;

import java.lang.reflect.Array;

public class CustomListImpl<T extends Comparable> implements CustomList<T> {

    private static final Integer DEFAULT_ARRAY_SIZE = 16;

    private T[] collection;
    private int count;
    private Class<?> classType;

    public CustomListImpl() throws ClassNotFoundException {
        this.classType = Class.forName("java.lang.String");
        this.collection = (T[]) Array.newInstance(this.classType, 3);
        this.count = 0;
    }

    @Override
    public void add(T element) {
        if (this.count == this.collection.length) {
            T[] newCollection = (T[]) Array.newInstance(this.classType, this.collection.length * 2);
            for (int i = 0; i < this.count; i++) {
                newCollection[i] = this.collection[i];
            }
            this.collection = newCollection;
        }
        this.collection[count] = element;
        this.count++;
    }

    @Override
    public T remove(int index) {
        if (this.collection.length == 0 || index >= this.count) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        T removed = null;
        T[] newCollection = (T[]) Array.newInstance(this.classType, this.count - 1);
        if (newCollection.length == 0) {
            removed = this.collection[0];
            this.collection = newCollection;
            return removed;
        }
        for (int i = 0; i < index; i++) {
            newCollection[i] = this.collection[i];
        }
        for (int i = index + 1; i < this.count; i++) {
            newCollection[i - 1] = this.collection[i];
        }
        removed = this.collection[index];
        this.collection = newCollection;
        this.count--;
        return removed;
    }

    @Override
    public boolean contains(T element) {
        for (int i = 0; i < this.count; i++) {
            if (this.collection[i].compareTo(element) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void swap(int index1, int index2) {
        if (index1 < 0 || index1 > this.count ||
                index2 < 0 || index2 > this.count) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        T temp = this.collection[index1];
        this.collection[index1] = this.collection[index2];
        this.collection[index2] = temp;
    }

    @Override
    public int countGreaterThan(T element) {
        int count = 0;
        for (int i = 0; i < this.count; i++) {
            if (this.collection[i].compareTo(element) > 0) {
                count++;
            }
        }
        return count;
    }

    @Override
    public T getMax() {
        T maxElement = this.collection[0];
        for (int i = 0; i < this.count; i++) {
            if (this.collection[i].compareTo(maxElement) > 0) {
                maxElement = this.collection[i];
            }
        }
        return maxElement;
    }

    @Override
    public T getMin() {
        T minElement = this.collection[0];
        for (int i = 0; i < this.count; i++) {
            if (this.collection[i].compareTo(minElement) < 0) {
                minElement = this.collection[i];
            }
        }
        return minElement;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.count; i++) {
            builder.append(this.collection[i]).
                    append(System.lineSeparator());
        }
        return builder.toString();
    }
}
