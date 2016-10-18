package models.centers.centersContracts;

import models.animals.Animal;
import java.util.List;

public interface CleansingCenter extends Center {

    void receiveUncleansedAnimals(List<Animal> animals);

    List<Animal> cleanse();
}
