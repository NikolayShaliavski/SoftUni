package problem_03.contracts;

public interface Stack<T> extends Iterable<T> {

    void push(T... args);
    T pop();
}
