package models.animals;

public abstract class AbstractAnimal implements Animal {

    private String name;
    private String centerName;
    private int age;

    protected AbstractAnimal(String name,
                             String centerName,
                             int age) {
        this.name = name;
        this.centerName = centerName;
        this.age = age;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getCenterName() {
        return this.centerName;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public int compareTo(Animal otherAnimal) {
        return this.getName().compareTo(otherAnimal.getName());
    }
}
