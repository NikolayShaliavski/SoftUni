package problem_06;

import com.sun.org.apache.bcel.internal.classfile.ClassFormatException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class P06_PrimeChecker {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberToCheck = Integer.valueOf(reader.readLine());

        boolean isPrime = true;
        for (int i = 2; i <= Math.abs(numberToCheck / 2); i++) {
            if (numberToCheck % i == 0) {
                isPrime = false;
                break;
            }
        }
        Number number = new Number(numberToCheck, isPrime);
        System.out.println(number.getNextPrime() + ", " + number.getPrime());

        Field[] fields = Number.class.getDeclaredFields();

        List<Field> filedsDeclared = Arrays.stream(fields)
                .filter(f -> f.getName().contains("prime") || f.getName().contains("number"))
                .collect(Collectors.toList());

        List<Constructor<?>> constructors = Arrays.stream(Number.class.getDeclaredConstructors())
                .filter(c -> c.getParameterCount() > 1)
                .collect(Collectors.toList());

        if (filedsDeclared.size() <= 1 || constructors.size() < 1) {
            throw new ClassFormatException();
        }

    }
}

class Number {

    private int number;
    private boolean prime;

    public Number(int number, boolean isPrime) {
        this.number = number;
        this.prime = isPrime;
    }

    public boolean getPrime() {
        return this.prime;
    }

    public int getNextPrime() {
        int nextPrime = 0;

        for (int i = this.number + 1; i <= this.number + 11; i++) {
            boolean findPrime = true;
            for (int j = 2; j <= Math.abs(i / 2); j++) {
                if (i % j == 0) {
                    findPrime = false;
                    break;
                }
            }
            if (findPrime) {
                nextPrime = i;
                break;
            }
        }
        return nextPrime;
    }
}