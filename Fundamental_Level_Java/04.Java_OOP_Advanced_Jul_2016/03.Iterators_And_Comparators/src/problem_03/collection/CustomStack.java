package problem_03.collection;

import problem_03.contracts.Stack;

import java.lang.reflect.Array;
import java.util.Iterator;

public class CustomStack<T> implements Stack<T> {

    private static final Integer START_SIZE = 16;

    private T[] arrayStack;
    private Class<T> classType;
    private int currentIndex;

    public CustomStack(Class<T> classType) {
        this.classType = classType;
        this.arrayStack = (T[]) Array.newInstance(classType, START_SIZE);
        this.currentIndex = 0;
    }

    @Override
    public void push(T... args) {
        for (int i = 0; i < args.length; i++) {
            this.arrayStack[currentIndex] = args[i];
            if (this.currentIndex == this.arrayStack.length) {
                this.increaseStackCapacity();
            }
            this.increaseIndex();
        }
    }

    @Override
    public T pop() {
        if (this.currentIndex == 0) {
            throw new UnsupportedOperationException("No elements");
        }
        T element = this.arrayStack[currentIndex - 1];
        this.arrayStack[currentIndex - 1] = null;
        this.decreaseIndex();
        if (this.currentIndex == this.arrayStack.length / 3) {
            this.decreaseStackCapacity();
        }
        return element;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> customIterator = new Iterator<T>() {
            int innerIndex = currentIndex - 1;
            @Override
            public boolean hasNext() {
                return innerIndex >= 0;
            }

            @Override
            public T next() {
                return arrayStack[innerIndex--];
            }
        };
        return customIterator;
    }

    private void increaseStackCapacity() {
        T[] newArrayStack = (T[]) Array.newInstance(classType, this.arrayStack.length * 2);
        for (int i = 0; i < this.arrayStack.length; i++) {
            newArrayStack[i] = this.arrayStack[i];
        }
        this.arrayStack = newArrayStack;
    }

    private void decreaseStackCapacity() {
        T[] newArrayStack = (T[]) Array.newInstance(classType, this.arrayStack.length / 2);
        for (int i = 0; i < newArrayStack.length; i++) {
            newArrayStack[i] = this.arrayStack[i];
        }
        this.arrayStack = newArrayStack;
    }

    private void increaseIndex() {
        this.currentIndex++;
    }

    private void decreaseIndex() {
        this.currentIndex--;
    }
}
