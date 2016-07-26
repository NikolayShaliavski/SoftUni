package problem_09.contracts;

/**
 * Created by Nikolay Shalyavski on 26.7.2016 г..
 */
public interface MyLinkedList<E extends Comparable<E>> extends Iterable<E> {

    void add(E element);
    boolean remove(E element);
    int getSize();
}
