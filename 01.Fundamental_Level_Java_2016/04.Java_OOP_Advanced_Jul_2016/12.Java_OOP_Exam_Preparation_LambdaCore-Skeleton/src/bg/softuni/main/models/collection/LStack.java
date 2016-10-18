package bg.softuni.main.models.collection;

import bg.softuni.main.contracts.collectionContracts.LinkedStack;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Implementation of custom LinkedStack
 *
 * @param <T>
 * @see LinkedStack
 */
public class LStack<T> implements LinkedStack<T> {

    private LinkedList<T> innerList;

    /**
     * Creates an empty custom stack
     */
    public LStack() {
        this.innerList = new LinkedList<>();
    }

    @Override
    public int size() {
        return this.innerList.size();
    }

    @Override
    public T push(T item) {
        if (item == null) {
            throw new UnsupportedOperationException();
        }
        this.innerList.addLast(item);
        return item;
    }

    @Override
    public T pop() {
        T removedItem = this.innerList.removeLast();
        return removedItem;
    }

    @Override
    public T peek() {
        T peekedItem = this.innerList.getLast();
        return peekedItem;
    }

    @Override
    public boolean isEmpty() {
        return this.innerList.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return this.innerList.iterator();
    }
}