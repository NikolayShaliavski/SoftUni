package problem_08;

import problem_08.contracts.Clinic;
import problem_08.contracts.Pet;
import problem_08.models.ClinicImpl;
import problem_08.models.PetImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        List<Pet> pets = new ArrayList<>();
        List<Clinic> clinics = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfLines = Integer.valueOf(reader.readLine());
        for (int i = 0; i < numberOfLines; i++) {
            String[] commands = reader.readLine().split("[\\s]+");
            switch (commands[0]) {
                case "Create":
                    if (commands[1].equals("Pet")) {
                        tryCreatePet(commands, pets);
                    } else if (commands[1].equals("Clinic")) {
                        tryCreateClinic(commands, clinics);
                    }
                    break;
                case "Add":
                    boolean petAdded = tryAcomodatePet(commands[1], commands[2], pets, clinics);
                    System.out.println(petAdded);
                    break;
                case "Release":
                    boolean releaseClinic = tryReleaseClinic(commands[1], clinics);
                    System.out.println(releaseClinic);
                    break;
                case "HasEmptyRooms":
                    Clinic clinic = findClinic(commands[1], clinics);
                    System.out.println(clinic.hasEmptyRooms());
                    break;
                case "Print":
                    clinic = findClinic(commands[1], clinics);
                    if (commands.length == 3) {
                        System.out.println(clinic.print(Integer.valueOf(commands[2])));
                    } else {
                        System.out.println(clinic.print());
                    }
                    break;
            }
        }
    }

    private static boolean tryReleaseClinic(String clinicName, List<Clinic> clinics) {
        Clinic clinic = findClinic(clinicName, clinics);
        return clinic.release();
    }

    private static boolean tryAcomodatePet(String name, String clinicName, List<Pet> pets, List<Clinic> clinics) {
        Pet petToAcomodate = pets.stream().
                filter(pet -> pet.getName().equals(name)).
                findFirst().get();
        Clinic clinic = findClinic(clinicName, clinics);
        return clinic.add(petToAcomodate);
    }

    private static Clinic findClinic(String clinicName, List<Clinic> clinics) {
        return clinics.stream().
                filter(clinicToCheck -> clinicToCheck.getClinicName().equals(clinicName)).
                findFirst().get();
    }

    private static void tryCreateClinic(String[] commands, List<Clinic> clinics) {
        try {
            Clinic clinic = new ClinicImpl(commands[2], Integer.valueOf(commands[3]));
            clinics.add(clinic);
        } catch (IllegalArgumentException iaex) {
            System.out.println(iaex.getMessage());
        }
    }

    private static void tryCreatePet(String[] commands, List<Pet> pets) {
        Pet pet = new PetImpl(commands[2], Integer.valueOf(commands[3]), commands[4]);
        pets.add(pet);
    }
}
