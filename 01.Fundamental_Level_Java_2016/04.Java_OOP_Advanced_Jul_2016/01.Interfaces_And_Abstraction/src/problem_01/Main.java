package problem_01;

import problem_01.interfaces.Identifiable;
import problem_01.interfaces.Birthable;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Nick on 11.7.2016 г..
 */
public class Main {

    public static void main(String[] args) {
        Class[] citizenInterfaces = problem_01.models.Citizen.class.getInterfaces();
        if (Arrays.asList(citizenInterfaces).contains(Birthable.class)
                && Arrays.asList(citizenInterfaces).contains(Identifiable.class)) {
            Method[] methods = Birthable.class.getDeclaredMethods();
            System.out.println(methods.length);
            System.out.println(methods[0].getReturnType().getSimpleName());
            methods = Identifiable.class.getDeclaredMethods();
            System.out.println(methods.length);
            System.out.println(methods[0].getReturnType().getSimpleName());
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            int age = Integer.parseInt(scanner.nextLine());
            String id = scanner.nextLine();
            String birthdate = scanner.nextLine();
            Identifiable identifiable = new problem_01.models.Citizen(name,age,id,birthdate);
            Birthable birthable = new problem_01.models.Citizen(name,age,id,birthdate);
        }
    }

}
