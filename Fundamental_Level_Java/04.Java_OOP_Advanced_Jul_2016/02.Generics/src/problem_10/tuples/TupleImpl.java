package problem_10.tuples;

import problem_10.interfaces.Tuple;

public class TupleImpl<T1, T2> implements Tuple<T1, T2> {

    private T1 element1;
    private T2 element2;

    public TupleImpl(T1 element1, T2 element2) {
        this.setElement1(element1);
        this.setElement2(element2);
    }

    @Override
    public T1 getElement1() {
        return this.element1;
    }

    @Override
    public void setElement1(T1 element1) {
        this.element1 = element1;
    }

    @Override
    public T2 getElement2() {
        return this.element2;
    }

    @Override
    public void setElement2(T2 element2) {
        this.element2 = element2;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s",
                this.element1,
                this.element2);
    }
}
