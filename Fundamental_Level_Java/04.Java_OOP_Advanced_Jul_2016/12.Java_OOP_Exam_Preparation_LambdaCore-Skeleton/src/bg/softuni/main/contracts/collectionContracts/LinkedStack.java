package bg.softuni.main.contracts.collectionContracts;

/**
 * Custom collection based on java.util.LinkedList and java.util.Stack
 * inside the class elements are stored in LinkedList but behaviour of
 * custom collection is like Stack's behaviour - LIFO
 *
 * @param <T>
 */
public interface LinkedStack<T> extends Iterable<T> {

    /**
     * Returns current size of custom collection
     *
     * @return count of elements in custom stack
     */
    int size();

    /**
     * Add item to the top of custom stack
     *
     * @param item element to add
     * @return pushed item to LStack
     */
    T push(T item);

    /**
     * Removes the element from the top of custom stack
     *
     * @return removed element
     */
    T pop();

    /**
     * Checks the element on the top of custom stack and returns it
     * without removing it
     *
     * @return the element on the top of this stack
     */
    T peek();

    /**
     * Checks if custom stack contains any elements
     *
     * @return true if stack size is greater than zero or false if it isn't
     */
    boolean isEmpty();
}
