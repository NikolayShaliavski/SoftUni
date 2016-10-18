package models.animals;

public class Cat extends AbstractAnimal {

    private int intelligence;

    public Cat(String name,
               String centerName,
               int age,
               int intelligence) {
        super(name, centerName, age);
        this.intelligence = intelligence;
    }
}
