package problem_07;

import problem_07.people.Citizen;
import problem_07.people.Person;
import problem_07.people.Rebel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfPeople = Integer.valueOf(reader.readLine());

        List<Person> buyers = new ArrayList<>();
        for (int i = 0; i < numberOfPeople; i++) {
            String[] params = reader.readLine().split("[\\s]+");
            tryCreatePerson(params, buyers);
        }

        String name = reader.readLine();
        while (!name.equals("End")) {

            tryBuyFood(name, buyers);
            name = reader.readLine();
        }
        int boughtFood = buyers.stream().
                mapToInt(buyer -> buyer.getFood()).sum();
        System.out.println(boughtFood);
    }

    private static void tryBuyFood(String name, List<Person> buyers) {
        for (Person buyer : buyers) {
            if (buyer.getName().equals(name)) {
                buyer.buyFood();
            }
        }
    }

    private static void tryCreatePerson(String[] params, List<Person> buyers) {
        Person person = null;
        String name = params[0];
        Integer age = Integer.valueOf(params[1]);

        if (params.length == 4) {
            String id = params[2];
            String birthday = params[3];
            person = new Citizen(name, age, id, birthday);
            buyers.add(person);
        } else if (params.length == 3) {
            String group = params[2];
            person = new Rebel(name, age, group);
            buyers.add(person);
        }
    }
}
