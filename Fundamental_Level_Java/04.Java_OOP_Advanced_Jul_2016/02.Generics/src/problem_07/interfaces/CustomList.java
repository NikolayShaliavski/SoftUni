package problem_07.interfaces;

public interface CustomList<T> {

    void add(T element);

    T remove(int index);

    boolean contains(T element);

    void swap(int index1, int index2);

    int countGreaterThan(T element);

    T getMax();

    T getMin();

    T get(int index);

    void set(int index, T element);

    int size();
}
