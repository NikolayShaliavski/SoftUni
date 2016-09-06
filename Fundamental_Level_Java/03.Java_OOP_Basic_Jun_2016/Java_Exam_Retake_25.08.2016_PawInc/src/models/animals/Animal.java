package models.animals;

public interface Animal extends Comparable<Animal> {

    String getName();

    int getAge();

    String getCenterName();
}
