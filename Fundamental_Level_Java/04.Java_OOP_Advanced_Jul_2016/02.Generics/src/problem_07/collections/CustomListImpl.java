package problem_07.collections;

import problem_07.interfaces.CustomList;

import java.util.Iterator;

public class CustomListImpl<T extends Comparable<T>> implements CustomList<T>, Iterable<T>{

    private static final Integer DEFAULT_ARRAY_SIZE = 16;

    private T[] collection;
    private int count;
    //private Class<?> classType;

    public CustomListImpl() throws ClassNotFoundException {
        //this.classType = Class.forName("java.lang.String");
        //this.collection = (T[]) Array.newInstance(this.classType, DEFAULT_ARRAY_SIZE);
        this.collection = (T[]) new String[DEFAULT_ARRAY_SIZE];
        this.collection.getClass().getName();
        this.count = 0;
    }

    @Override
    public void add(T element) {
        if (this.count == this.collection.length) {
            //T[] newCollection = (T[]) Array.newInstance(this.classType, this.collection.length * 2);
            T[] newCollection = (T[]) new String[this.collection.length * 2];
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
        //T[] newCollection = (T[]) Array.newInstance(this.classType, this.count - 1);
        T[] newCollection = (T[]) new String[this.count - 1];
        if (newCollection.length == 0) {
            removed = this.collection[0];
            this.collection = newCollection;
            this.count = 0;
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
    public T get(int index) {
        if (index < 0 || index >= this.count) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        return this.collection[index];
    }

    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= this.count) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        this.collection[index] = element;
    }

    @Override
    public int size() {
        return this.count;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
//        for (T element : this.collection) {
//            builder.append(element).
//                    append(System.lineSeparator());
//        }
        Iterator<T> iterator = this.iterator();

        while (iterator.hasNext()) {
            builder.append(iterator.next()).
                    append(System.lineSeparator());
        }
        return builder.toString();
    }

    @Override
    public Iterator<T> iterator() {

        Iterator<T> iterator = new Iterator<T>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                if (index < count) {
                    return true;
                }
                return false;
            }

            @Override
            public T next() {
                return collection[index++];
            }
        };
        return iterator;
    }
}
