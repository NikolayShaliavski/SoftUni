package models.corporation;

import models.animals.Animal;
import models.centers.centersContracts.AdoptionCenter;
import models.centers.centersContracts.Center;
import models.centers.centersContracts.CleansingCenter;

import java.util.*;

public class PawCorporation implements Corporation {

    private Map<String, Map<String, Center>> centers;
    private Set<Animal> adoptedAnimals;
    private Set<Animal> cleansedAnimals;

    public PawCorporation() {
        this.initCentersData();
        this.adoptedAnimals = new TreeSet<>();
        this.cleansedAnimals = new TreeSet<>();
    }

    @Override
    public void registerCenter(Center center) {
        int index = center.getClass().getSimpleName().lastIndexOf('I');
        String centerType = center.getClass().getSimpleName().substring(0, index);
        this.centers.get(centerType).put(center.getName(), center);
    }

    @Override
    public void registerAnimal(Animal animal, String adoptionCenterName) {
        AdoptionCenter adoptionCenter =
                (AdoptionCenter) this.centers.get("AdoptionCenter").get(adoptionCenterName);
        adoptionCenter.registerAnimal(animal);
    }

    @Override
    public void sendForCleansing(String adoptionCenterName, String cleansingCenterName) {
        AdoptionCenter adoptionCenter =
                (AdoptionCenter) this.centers.get("AdoptionCenter").get(adoptionCenterName);
        CleansingCenter cleansingCenter =
                (CleansingCenter) this.centers.get("CleansingCenter").get(cleansingCenterName);
        List<Animal> animalsForCleansing = adoptionCenter.sendForCleansing();
        cleansingCenter.receiveUncleansedAnimals(animalsForCleansing);
    }

    @Override
    public void cleanse(String cleansingCenterName) {
        CleansingCenter cleansingCenter =
                (CleansingCenter) this.centers.get("CleansingCenter").get(cleansingCenterName);
        List<Animal> cleansedAnimals = cleansingCenter.cleanse();
        for (Animal cleansedAnimal : cleansedAnimals) {
            String adoptionCenterName = cleansedAnimal.getCenterName();
            AdoptionCenter adoptionCenter =
                    (AdoptionCenter) this.centers.get("AdoptionCenter").get(adoptionCenterName);
            adoptionCenter.returnCleansedAnimals(cleansedAnimal);
        }
        this.cleansedAnimals.addAll(cleansedAnimals);
    }

    @Override
    public void adopt(String adoptionCenterName) {
        AdoptionCenter adoptionCenter =
                (AdoptionCenter) this.centers.get("AdoptionCenter").get(adoptionCenterName);
        this.adoptedAnimals.addAll(adoptionCenter.adopt());
    }

    @Override
    public String reportStatus() {
        StringBuilder builder = new StringBuilder();
        builder.append("Paw Incorporative Regular Statistics").
                append(System.lineSeparator());
        builder.append(
                String.format("Adoption Centers: %d%n", this.centers.get("AdoptionCenter").size()));
        builder.append(
                String.format("Cleansing Centers: %d%n", this.centers.get("CleansingCenter").size()));
        builder.append("Adopted Animals: ");
        if (this.adoptedAnimals.size() > 0) {
            for (Animal adoptedAnimal : this.adoptedAnimals) {
                builder.append(adoptedAnimal.getName()).append(", ");
            }
            builder.delete(builder.length() - 2, builder.length());
            builder.append(System.lineSeparator());
        } else {
            builder.append("None").append(System.lineSeparator());
        }
        builder.append("Cleansed Animals: ");
        if (this.cleansedAnimals.size() > 0) {
            for (Animal cleansedAnimal : this.cleansedAnimals) {
                builder.append(cleansedAnimal.getName()).append(", ");
            }
            builder.delete(builder.length() - 2, builder.length());
            builder.append(System.lineSeparator());
        } else {
            builder.append("None").append(System.lineSeparator());
        }
        int animalsWaitingToBeAdopted = this.centers.get("AdoptionCenter").entrySet().stream().
                mapToInt(center -> center.getValue().getWaitingAnimalsCount()).sum();
        builder.append(String.format("Animals Awaiting Adoption: %d%n", animalsWaitingToBeAdopted));
        int animalsWaitingToBeCleansed = this.centers.get("CleansingCenter").entrySet().stream().
                mapToInt(center -> center.getValue().getWaitingAnimalsCount()).sum();
        builder.append(String.format("Animals Awaiting Cleansing: %d%n", animalsWaitingToBeCleansed));
        return builder.toString();
    }

    private void initCentersData() {
        this.centers = new LinkedHashMap<>();
        this.centers.put("AdoptionCenter", new LinkedHashMap<>());
        this.centers.put("CleansingCenter", new LinkedHashMap<>());
    }
}
