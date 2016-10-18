package models.centers;

import models.animals.Animal;
import models.centers.centersContracts.AdoptionCenter;

import java.util.ArrayList;
import java.util.List;

public class AdoptionCenterImpl implements AdoptionCenter {

    private String name;
    private List<Animal> cleansedAnimals;
    private List<Animal> uncleansedAnimals;

    public AdoptionCenterImpl(String name) {
        this.name = name;
        this.cleansedAnimals = new ArrayList<>();
        this.uncleansedAnimals = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getWaitingAnimalsCount() {
        return this.cleansedAnimals.size();
    }

    @Override
    public void registerAnimal(Animal animal) {
        this.uncleansedAnimals.add(animal);
    }

    @Override
    public void returnCleansedAnimals(Animal animal) {
        this.cleansedAnimals.add(animal);
    }

    @Override
    public List<Animal> sendForCleansing() {
        List<Animal> animalsToCleanse = new ArrayList<>(this.uncleansedAnimals);
        this.uncleansedAnimals.clear();
        return animalsToCleanse;
    }

    @Override
    public List<Animal> adopt() {
        List<Animal> animalsToAdopt = new ArrayList<>(this.cleansedAnimals);
        this.cleansedAnimals.clear();
        return animalsToAdopt;
    }
}
