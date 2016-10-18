package problem_02;

public class GenericBox<T> {

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
}
