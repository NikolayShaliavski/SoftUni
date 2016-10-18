package problem_04;

import java.util.Iterator;

public class Lake implements Iterable<Integer> {

    private int[] lakeArray;

    public Lake(int... args) {
        this.setLakeArray(args);
    }

    private void setLakeArray(int[] lakeArray) {
        this.lakeArray = lakeArray;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new FrogIterator();
    }

    private class FrogIterator implements Iterator<Integer> {

        int currentIndex = 0;
        boolean isEven = true;

        @Override
        public boolean hasNext() {
            if (this.currentIndex >= lakeArray.length && this.isEven) {
                this.currentIndex = 1;
                this.isEven = false;
            }
            if (this.currentIndex < lakeArray.length) {
                return true;
            }
            return false;
        }

        @Override
        public Integer next() {
            int currentNumber = lakeArray[this.currentIndex];
            this.currentIndex += 2;
            return currentNumber;
        }
    }
}
