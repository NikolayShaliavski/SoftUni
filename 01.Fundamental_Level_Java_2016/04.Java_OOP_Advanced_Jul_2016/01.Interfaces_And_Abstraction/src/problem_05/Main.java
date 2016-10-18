package problem_05;

import problem_05.inhabitants.AbstractInhabitant;
import problem_05.inhabitants.Citizen;
import problem_05.inhabitants.Pet;
import problem_05.inhabitants.Robot;
import problem_05.interfaces.Inhabitant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Inhabitant> inhabitants = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine();
        while (!line.equals("End")) {
            String[] inhabitantParams = line.split("[\\s]+");
            try {

                AbstractInhabitant inhabitant = tryCreateInhabitant(inhabitantParams);
                if (inhabitant == null) {
                    throw new NullPointerException();
                }
                inhabitants.add(inhabitant);

            } catch (IllegalArgumentException iaex) {
                System.out.println(iaex.getMessage());
            } catch (NullPointerException npex) {
                System.out.println(npex.getMessage());
            }
            line = reader.readLine();
        }
        String year = reader.readLine();

        for (Inhabitant inhabitant : inhabitants) {
            if (inhabitant instanceof Robot) {
                continue;
            } else if (inhabitant.getBirthday().endsWith(year)) {
                inhabitant.printBirthday();
            }
        }
    }

    private static AbstractInhabitant tryCreateInhabitant(String[] inhabitantParams) {
        AbstractInhabitant inhabitant = null;
        try {
            if (inhabitantParams[0].equals("Citizen")) {
                inhabitant = new Citizen(inhabitantParams[1],
                            Integer.valueOf(inhabitantParams[2]),
                                inhabitantParams[3],
                                    inhabitantParams[4]);
            } else if (inhabitantParams[0].equals("Robot")) {
                inhabitant = new Robot(inhabitantParams[1],
                            inhabitantParams[2]);
            } else if (inhabitantParams[0].equals("Pet")) {
                inhabitant = new Pet(inhabitantParams[1],
                            inhabitantParams[2]);
            }
        } catch (IllegalArgumentException iaex) {
            System.out.println(iaex.getMessage());
        }
        return inhabitant;
    }
}
