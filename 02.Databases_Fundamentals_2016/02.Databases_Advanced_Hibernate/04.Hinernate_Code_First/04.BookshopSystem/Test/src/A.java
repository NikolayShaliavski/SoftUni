import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Nikolay Shalyavski on 18.11.2016 г..
 */
public class A extends B {

    public void invocation() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //super.invocation();
        Class superSuperClass = this.getClass().getSuperclass().getSuperclass();
        Method method = superSuperClass.getDeclaredMethod("test");
        method.setAccessible(true);
        method.invoke(this);
        //superSuperClass.getDeclaredMethod("test").invoke(this);
        System.out.println();
    }
}
