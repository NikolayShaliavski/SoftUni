package models.centers;

abstract class BaseEmergencyCenter implements EmergencyCenter {

    private String name;

    private Integer amountOfMaximumEmergencies;

    private Integer processedEmergencies;

    protected BaseEmergencyCenter(String name,
                                  Integer amountOfMaximumEmergencies) {
        this.setName(name);
        this.setAmountOfMaximumEmergencies(amountOfMaximumEmergencies);
        this.setProcessedEmergencies();
    }

    @Override
    public Integer getProcessedEmergencies() {
        return this.processedEmergencies;
    }

    private void setProcessedEmergencies() {
        this.processedEmergencies = 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getAmountOfMaximumEmergencies() {
        return this.amountOfMaximumEmergencies;
    }

    private void setAmountOfMaximumEmergencies(Integer amountOfMaximumEmergencies) {
        this.amountOfMaximumEmergencies = amountOfMaximumEmergencies;
    }

    @Override
    public void processEmergency() {
        this.processedEmergencies++;
    }

    @Override
    public Boolean isForRetirement() {
        return this.processedEmergencies >= this.amountOfMaximumEmergencies;
    }
}
