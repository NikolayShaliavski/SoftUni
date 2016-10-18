package models.corporation;

import models.animals.Animal;
import models.centers.centersContracts.Center;

public interface Corporation {

    void registerCenter(Center center);

    void registerAnimal(Animal animal, String adoptionCenterName);

    void sendForCleansing(String adoptionCenterName, String cleansingCenterName);

    void cleanse(String cleansingCenterName);

    void adopt(String adoptionCenterName);

    String reportStatus();
}
