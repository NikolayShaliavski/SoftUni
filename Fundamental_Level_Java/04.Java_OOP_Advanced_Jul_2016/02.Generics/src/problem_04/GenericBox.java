package problem_04;

public class GenericBox<T extends Comparable<T>> implements Comparable<T>{

    private T element;

    public GenericBox(T element) {
        this.setElement(element);
    }

    private void setElement(T element) {
        this.element = element;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.element.getClass().getName(), this.element);
    }

    @Override
    public int compareTo(T element) {
        return this.element.compareTo(element);
    }
}
