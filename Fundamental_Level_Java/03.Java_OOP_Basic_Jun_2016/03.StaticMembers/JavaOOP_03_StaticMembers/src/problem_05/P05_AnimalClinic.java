package problem_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P05_AnimalClinic {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (!line.equals("End")) {
            String[] tokens = line.split("[\\s]+");
            String name = tokens[0];
            String breed = tokens[1];
            String command = tokens[2];
            Animal animal = new Animal(name, breed);

            if (command.equals("heal")) {
                AnimalClinic.heal(animal);
            } else if (command.equals("rehabilitate")) {
                AnimalClinic.rehabilitate(animal);
            }

            line = reader.readLine();
        }
        System.out.printf("Total healed animals: %d%n", AnimalClinic.getAnimalHealed());
        System.out.printf("Total rehabilitated animals: %d%n", AnimalClinic.getAnimalsRehabilitated());
        String command = reader.readLine();

        if (command.equals("heal")) {
            AnimalClinic.getHealedAnimals().stream()
                    .forEach(animal -> System.out.printf("%s %s%n",
                            animal.getName(),
                            animal.getBreed()));
        } else if (command.equals("rehabilitate")) {
            AnimalClinic.getRehabilitatedAnimals().stream()
                    .forEach(animal -> System.out.printf("%s %s%n",
                            animal.getName(),
                            animal.getBreed()));
        }
    }

}

class Animal {

    private String name;
    private String breed;

    public Animal(String name, String breed) {
        this.name = name;
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }
}

class AnimalClinic {

    private static List<Animal> healedAnimals = new ArrayList<>();
    private static List<Animal> rehabilitatedAnimals = new ArrayList<>();
    private static int animalHealed = 0;
    private static int animalsRehabilitated = 0;
    private static int patientCount = 0;

    public static List<Animal> getHealedAnimals() {
        return healedAnimals;
    }

    public static List<Animal> getRehabilitatedAnimals() {
        return rehabilitatedAnimals;
    }

    public static int getAnimalHealed() {
        return animalHealed;
    }

    public static int getAnimalsRehabilitated() {
        return animalsRehabilitated;
    }

    public static void heal(Animal animal) {
        healedAnimals.add(animal);
        animalHealed++;
        patientCount++;
        System.out.printf("Patient %d: [%s (%s)] has been healed!%n",
                patientCount,
                animal.getName(),
                animal.getBreed());
    }

    public static void rehabilitate(Animal animal) {
        rehabilitatedAnimals.add(animal);
        animalsRehabilitated++;
        patientCount++;
        System.out.printf("Patient %d: [%s (%s)] has been rehabilitated!%n",
                patientCount,
                animal.getName(),
                animal.getBreed());
    }
}