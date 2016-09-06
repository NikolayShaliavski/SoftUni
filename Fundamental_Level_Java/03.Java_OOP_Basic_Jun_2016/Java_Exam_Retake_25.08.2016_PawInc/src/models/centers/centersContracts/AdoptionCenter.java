package models.centers.centersContracts;

import models.animals.Animal;
import java.util.List;

public interface AdoptionCenter extends Center {

    void registerAnimal(Animal animal);

    void returnCleansedAnimals(Animal animal);

    List<Animal> sendForCleansing();

    List<Animal> adopt();
}
