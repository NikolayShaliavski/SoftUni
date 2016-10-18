package problem_11.tuples;

public class ThreeupleImpl<T1, T2, T3> implements problem_11.interfaces.Threeuple<T1, T2, T3> {

    private T1 element1;
    private T2 element2;
    private T3 element3;

    public ThreeupleImpl(T1 element1,
                         T2 element2,
                         T3 element3) {
        this.setElement1(element1);
        this.setElement2(element2);
        this.setElement3(element3);
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
    public T3 getElement3() {
        return this.element3;
    }

    @Override
    public void setElement3(T3 element3) {
        this.element3 = element3;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s -> %s",
                this.element1,
                this.element2,
                this.element3);
    }
}
