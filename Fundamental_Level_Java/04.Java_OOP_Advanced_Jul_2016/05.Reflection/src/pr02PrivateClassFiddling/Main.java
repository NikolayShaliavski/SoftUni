package pr02PrivateClassFiddling;

import pr02PrivateClassFiddling.com.peshoslav.BlackBoxInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws ReflectiveOperationException, IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Constructor ctor = BlackBoxInt.class.getDeclaredConstructor();
        ctor.setAccessible(true);
        BlackBoxInt blackBoxInt = (BlackBoxInt) ctor.newInstance();

        String command = reader.readLine();
        while (!command.equals("END")) {

            String[] params = command.split("_");
            String methodName = params[0];
            int value = Integer.valueOf(params[1]);

            Method method = blackBoxInt.getClass().getDeclaredMethod(methodName, int.class);
            method.setAccessible(true);
            method.invoke(blackBoxInt, value);

            Field field = blackBoxInt.getClass().getDeclaredField("innerValue");
            field.setAccessible(true);

            System.out.println(field.getInt(blackBoxInt));
            command = reader.readLine();
        }
    }
}
