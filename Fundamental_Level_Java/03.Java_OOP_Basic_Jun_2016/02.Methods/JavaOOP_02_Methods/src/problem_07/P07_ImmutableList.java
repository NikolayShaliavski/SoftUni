package problem_07;

import com.sun.org.apache.bcel.internal.classfile.ClassFormatException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class P07_ImmutableList {

    public static void main(String[] args) throws IOException {

        Class listClass = ImmutableList.class;
        Field[] fields = listClass.getDeclaredFields();
        if (fields.length < 1) {
            throw new ClassFormatException();
        }

        Method method = listClass.getDeclaredMethods()[0];
        System.out.println(method.getReturnType().getSimpleName());

    }

}
class ImmutableList {
    ArrayList<Integer> integers;

    public ImmutableList(ArrayList<Integer> integers) {
        this.integers = integers;
    }

    public ImmutableList immutableList() {
        return new ImmutableList(this.integers);
    }
}