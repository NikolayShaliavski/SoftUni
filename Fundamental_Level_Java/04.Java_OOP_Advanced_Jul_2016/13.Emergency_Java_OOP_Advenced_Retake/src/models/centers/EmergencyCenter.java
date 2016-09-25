package models.centers;

public interface EmergencyCenter {

    String getName();

    Integer getAmountOfMaximumEmergencies();

    Integer getProcessedEmergencies();

    void processEmergency();

    Boolean isForRetirement();
}
