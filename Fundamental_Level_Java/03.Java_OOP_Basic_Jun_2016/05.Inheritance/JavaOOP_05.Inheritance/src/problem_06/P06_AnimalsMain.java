package problem_06;

import problem_06.animals.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P06_AnimalsMain {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (!line.equals("Beast!")) {
            String type = line;
            String[] tokens = reader.readLine().split("[\\s]+");
            Animal animal = null;
            try {
                switch (type) {
                    case "Cat":
                        animal = new Cat(tokens[0], tokens[1], tokens[2]);
                        break;
                    case "Dog":
                        animal = new Dog(tokens[0], tokens[1], tokens[2]);
                        break;
                    case "Frog":
                        animal = new Frog(tokens[0], tokens[1], tokens[2]);
                        break;
                    case "Kitten":
                        animal = new Kitten(tokens[0], tokens[1]);
                        break;
                    case "Tomcat":
                        animal = new Tomcat(tokens[0], tokens[1]);
                        break;
                }
            } catch (IllegalArgumentException iaex) {
                System.out.println(iaex.getMessage());
                break;
            }
            if (animal != null) {
                System.out.println(animal.toString());
                System.out.println(animal.produceSound());
            }
            line = reader.readLine();
        }
    }

}
