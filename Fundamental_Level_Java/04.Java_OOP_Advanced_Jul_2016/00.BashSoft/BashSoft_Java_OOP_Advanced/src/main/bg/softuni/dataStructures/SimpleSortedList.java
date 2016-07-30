package main.bg.softuni.dataStructures;

import main.bg.softuni.contracts.SimpleOrderedBag;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class SimpleSortedList<E extends Comparable<E>> implements SimpleOrderedBag<E> {

    private static final int DEFAULT_SIZE = 16;

    private E[] innerColleciton;
    private int size;
    private Comparator<E> comparator;

    public SimpleSortedList(Class<E> type,
                            Comparator<E> comparator,
                            int capacity) {
        this.initializeInnerCollection(type, capacity);
        this.comparator = comparator;
    }

    public SimpleSortedList(Class<E> type,
                            int capacity) {
        this(type, Comparable::compareTo, capacity);
    }

    public SimpleSortedList(Class<E> type,
                            Comparator<E> comparator) {
        this(type, comparator, DEFAULT_SIZE);
    }

    public SimpleSortedList(Class<E> type) {
        this(type, Comparable::compareTo, DEFAULT_SIZE);
    }

    @Override
    public void add(E element) {

        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (this.size() >= this.innerColleciton.length) {
            this.resize();
        }
        this.innerColleciton[this.size()] = element;
        this.size++;
        //Arrays.sort(this.innerColleciton, 0, this.size(), this.comparator);
        //this.sortInnerCollectionWithSelectionSort();
        this.sortInnerCollectionWithBubbleSort();
    }

    private void resize() {
        E[] newCollection = Arrays.copyOf(
                this.innerColleciton,
                this.innerColleciton.length * 2);
        this.innerColleciton = newCollection;
    }

    @Override
    public void addAll(Collection<E> elements) {

        if ((this.size() + elements.size()) >= this.innerColleciton.length) {
            this.multiResize(elements);
        }
        for (E element : elements) {
            if (element == null) {
                throw new IllegalArgumentException();
            }
            this.innerColleciton[this.size()] = element;
            this.size++;
        }
        //Arrays.sort(this.innerColleciton, 0, this.size(), this.comparator);
        //this.sortInnerCollectionWithBubbleSort();
        this.sortInnerCollectionWithSelectionSort();
    }

    private void multiResize(Collection<E> elements) {

        int newSize = this.innerColleciton.length * 2;
        while (this.size() + elements.size() >= newSize) {
            newSize *= 2;
        }
        E[] newCollection = Arrays.copyOf(this.innerColleciton, newSize);
        this.innerColleciton = newCollection;
    }

    @Override
    public boolean remove(E element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        boolean hasBeenRemoved = false;
        int indexToRemove = 0;

        for (int i = 0; i < this.size(); i++) {
            if (this.innerColleciton[i].compareTo(element) == 0) {
                indexToRemove = i;
                this.innerColleciton[i] = null;
                hasBeenRemoved = true;
                break;
            }
        }
        if (hasBeenRemoved) {
            for (int i = indexToRemove; i < this.size() - 1; i++) {
                this.innerColleciton[i] = this.innerColleciton[i + 1];
            }
            this.innerColleciton[this.size() - 1] = null;
            this.size--;
        }
        return hasBeenRemoved;
    }

    @Override
    public int capacity() {
        return this.innerColleciton.length;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String joinWith(String joiner) {
        if (joiner == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder output = new StringBuilder();
        for (E element : this) {
            output.append(element);
            output.append(joiner);
        }
        output.setLength(output.length() - joiner.length());
        return output.toString();
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> iterator = new Iterator<E>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return this.index < size();
            }

            @Override
            public E next() {
                return innerColleciton[this.index++];
            }
        };
        return iterator;
    }

    @SuppressWarnings("unchecked")
    private void initializeInnerCollection(Class<E> type, int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative!");
        }
        this.innerColleciton = (E[]) Array.newInstance(type, capacity);
        //this.innerColleciton = (E[]) new Object[capacity];
    }

    //bubble sort algorithm
    private void sortInnerCollectionWithBubbleSort() {
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 1; i < this.size(); i++) {
                E left = this.innerColleciton[i - 1];
                E right = this.innerColleciton[i];

                if (this.comparator.compare(left, right) > 0) {
                    this.innerColleciton[i - 1] = right;
                    this.innerColleciton[i] = left;
                    swapped = true;
                }
            }
        }
    }

    //selection sort algorithm
    private void sortInnerCollectionWithSelectionSort() {
        for (int i = 0; i < this.size(); i++) {
            int indexToSwap = 0;
            E minElement = this.innerColleciton[i];

            for (int j = i + 1; j < this.size(); j++) {
                if (this.comparator.compare(this.innerColleciton[j], minElement) < 0) {
                    minElement = this.innerColleciton[j];
                    indexToSwap = j;
                }
            }
            if (indexToSwap != 0) {
                this.innerColleciton[indexToSwap] = this.innerColleciton[i];
                this.innerColleciton[i] = minElement;
            }
        }
    }
}
