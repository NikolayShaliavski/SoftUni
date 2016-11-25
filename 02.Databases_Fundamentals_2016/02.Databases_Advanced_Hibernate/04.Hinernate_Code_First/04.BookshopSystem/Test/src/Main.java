import java.lang.reflect.InvocationTargetException;

/**
 * Created by Nikolay Shalyavski on 18.11.2016 г..
 */
public class Main {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        A testA = new A();
        testA.invocation();
    }
}
