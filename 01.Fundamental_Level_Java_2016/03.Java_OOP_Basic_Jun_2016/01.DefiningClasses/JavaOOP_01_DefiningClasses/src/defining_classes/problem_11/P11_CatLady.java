package defining_classes.problem_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P11_CatLady {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Cat> cats = new ArrayList<>();

        String line = reader.readLine();
        while (!line.equals("End")) {
            String[] tokens = line.split("[\\s]+");
            String breed = tokens[0];
            String name = tokens[1];
            String characteristic = tokens[2];
            switch (breed) {
                case "Siamese":
                    Siamese cat01 = new Siamese(name, characteristic);
                    cats.add(cat01);
                    break;
                case "Cymric":
                    Cymric cat02 = new Cymric(name, characteristic);
                    cats.add(cat02);
                    break;
                case "StreetExtraordinaire":
                    StreetExtraordinaire cat03 = new StreetExtraordinaire(name, characteristic);
                    cats.add(cat03);
                    break;
            }
            line = reader.readLine();
        }
        String catToPrint = reader.readLine();
        for (Cat cat : cats) {
            if (cat.getName().equals(catToPrint)) {
                System.out.println(cat.toString());
            }
        }
    }
}

class Cat {
    String name;
    String characteristic;

    public Cat(String name, String characteristic) {
        this.name = name;
        this.characteristic = characteristic;
    }


    public String getName() {
        return name;
    }
}
class Siamese extends Cat{

    public Siamese(String name, String characteristic) {
        super(name, characteristic);
    }

    @Override
    public String toString() {
        return String.format("Siamese %s %s", this.name, this.characteristic);
    }
}

class Cymric extends Cat{

    public Cymric(String name, String characteristic) {
        super(name, characteristic);
    }

    @Override
    public String toString() {
        return String.format("Cymric %s %s", this.name, this.characteristic);
    }
}

class StreetExtraordinaire extends Cat{

    public StreetExtraordinaire(String name, String characteristic) {
        super(name, characteristic);
    }

    @Override
    public String toString() {
        return String.format("StreetExtraordinaire %s %s", this.name, this.characteristic);
    }
}