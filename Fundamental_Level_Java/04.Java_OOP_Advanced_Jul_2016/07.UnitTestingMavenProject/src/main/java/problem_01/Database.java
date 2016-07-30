package problem_01;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;

public class Database {

    private static final Integer DEFAULT_CAPACITY = 16;

    private Integer[] data;
    private Integer index;

    public Database() {
        this.data = new Integer[DEFAULT_CAPACITY];
        this.index = 0;
    }

    public Database(Integer... args) {
        this.data = Arrays.copyOf(args, DEFAULT_CAPACITY);
        this.index = args.length;
    }

    public Integer[] getData() {
        return this.data;
    }
    public void add(Integer element) throws OperationNotSupportedException {

        if (element == null) {
            throw new OperationNotSupportedException();
        }
        this.data[index] = element;
        index++;
    }

    public Integer remove() throws OperationNotSupportedException {

        if (this.index == 0) {
            throw new OperationNotSupportedException();
        }
        int removedElement = this.data[--index];
        this.data[index] = null;
        return removedElement;
    }

    public Integer[] fetch() {
        Integer[] newArr = Arrays.copyOf(this.data, this.index);
        return newArr;
    }
}
