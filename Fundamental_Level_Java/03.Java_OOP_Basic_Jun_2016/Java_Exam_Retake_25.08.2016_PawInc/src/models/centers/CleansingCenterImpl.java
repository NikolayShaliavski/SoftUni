package models.centers;

import models.animals.Animal;
import models.centers.centersContracts.CleansingCenter;

import java.util.ArrayList;
import java.util.List;

public class CleansingCenterImpl implements CleansingCenter {

    private String name;
    private List<Animal> animalsToCleanse;


    public CleansingCenterImpl(String name) {
        this.name = name;
        this.animalsToCleanse = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getWaitingAnimalsCount() {
        return this.animalsToCleanse.size();
    }

    @Override
    public void receiveUncleansedAnimals(List<Animal> animals) {
        this.animalsToCleanse.addAll(animals);
    }

    @Override
    public List<Animal> cleanse() {
        List<Animal> cleansedAnimals = new ArrayList<>(this.animalsToCleanse);
        this.animalsToCleanse.clear();
        return cleansedAnimals;
    }
}
