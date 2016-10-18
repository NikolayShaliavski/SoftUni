package models.animals;

public class Dog extends AbstractAnimal {

    private int learnedCommands;

    public Dog(String name,
               String centerName,
               int age,
               int learnedCommands) {
        super(name, centerName, age);
        this.learnedCommands = learnedCommands;
    }
}
