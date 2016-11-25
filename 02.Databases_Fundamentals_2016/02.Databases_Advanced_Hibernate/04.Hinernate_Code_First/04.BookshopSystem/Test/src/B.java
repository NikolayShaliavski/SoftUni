import java.lang.reflect.InvocationTargetException;

/**
 * Created by Nikolay Shalyavski on 18.11.2016 г..
 */
public class B extends C {

    protected void test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("B");
    }
}
