package core.system;

import collection.EmergencyRegister;
import collection.Register;
import models.centers.EmergencyCenter;
import models.emergencies.Emergency;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EmergencyManagementSystem implements ManagementSystem {

    private Map<String, Register<Emergency>> emergencies;
    private Map<String, Register<EmergencyCenter>> centers;
    private Map<String, Long> resultsOfProcessedEmergencies;
    private Integer totalProcessedEmergencies;

    public EmergencyManagementSystem() {
        this.initEmergenciesCollection();
        this.initCentersCollection();
        this.initResultsCollection();
        this.totalProcessedEmergencies = 0;
    }

    @Override
    public String registerPropertyEmergency(Emergency emergency) {
        this.emergencies.get("Property").enqueueEmergency(emergency);
        return String.format(
                "Registered Public Property Emergency of level %s at %s.",
                emergency.getEmergencyLevel(), emergency.getRegistrationTimeImpl().toString());
    }

    @Override
    public String registerHealthEmergency(Emergency emergency) {
        this.emergencies.get("Health").enqueueEmergency(emergency);
        return String.format(
                "Registered Public Health Emergency of level %s at %s.",
                emergency.getEmergencyLevel(), emergency.getRegistrationTimeImpl().toString());
    }

    @Override
    public String registerOrderEmergency(Emergency emergency) {
        this.emergencies.get("Order").enqueueEmergency(emergency);
        return String.format(
                "Registered Public Order Emergency of level %s at %s.",
                emergency.getEmergencyLevel(), emergency.getRegistrationTimeImpl().toString());
    }

    @Override
    public String registerFireServiceCenter(EmergencyCenter emergencyCenter) {
        this.centers.get("Property").enqueueEmergency(emergencyCenter);
        return String.format(
                "Registered Fire Service Emergency Center - %s.",
                emergencyCenter.getName());
    }

    @Override
    public String registerMedicalServiceCenter(EmergencyCenter emergencyCenter) {
        this.centers.get("Health").enqueueEmergency(emergencyCenter);
        return String.format(
                "Registered Medical Service Emergency Center - %s.",
                emergencyCenter.getName());
    }

    @Override
    public String registerPoliceServiceCenter(EmergencyCenter emergencyCenter) {
        this.centers.get("Order").enqueueEmergency(emergencyCenter);
        return String.format(
                "Registered Police Service Emergency Center - %s.",
                emergencyCenter.getName());
    }

    @Override
    public String processEmergencies(String type) {
        Register<Emergency> emergenciesToProcess = this.emergencies.get(type);
        Register<EmergencyCenter> processCenters = this.centers.get(type);

        while (!emergenciesToProcess.isEmpty()) {
            if (processCenters.isEmpty()) {
                return String.format(
                        "%s Emergencies left to process: %d.",
                        type, emergenciesToProcess.count());
            }
            EmergencyCenter currentCenter = processCenters.dequeueEmergency();
            if (currentCenter.isForRetirement()) {
                continue;
            }
            Emergency currentEmergency = emergenciesToProcess.dequeueEmergency();
            Long currentResult = this.resultsOfProcessedEmergencies.get(type);
            this.resultsOfProcessedEmergencies.put(
                    type, currentResult + currentEmergency.getResultAfterProcessing());
            currentCenter.processEmergency();
            processCenters.enqueueEmergency(currentCenter);
            this.totalProcessedEmergencies++;
        }
        return String.format(
                "Successfully responded to all %s emergencies.", type);
    }

    @Override
    public String emergencyReport() {

        this.checkForRetiredCenters();

        StringBuilder builder = new StringBuilder();
        Integer allRegisteredEmergencies = this.emergencies.entrySet().stream().
                mapToInt(currentEmergencies -> currentEmergencies.getValue().count()).sum();

        builder.append(String.format("PRRM Services Live Statistics%n"));
        builder.append(
                String.format("Fire Service Centers: %d%n", this.centers.get("Property").count()));
        builder.append(
                String.format("Medical Service Centers: %d%n", this.centers.get("Health").count()));
        builder.append(
                String.format("Police Service Centers: %d%n", this.centers.get("Order").count()));
        builder.append(
                String.format("Total Processed Emergencies: %d%n", this.totalProcessedEmergencies));
        builder.append(
                String.format("Currently Registered Emergencies: %d%n", allRegisteredEmergencies));
        builder.append(String.format(
                "Total Property Damage Fixed: %d%n", this.resultsOfProcessedEmergencies.get("Property")));
        builder.append(String.format(
                "Total Health Casualties Saved: %d%n", this.resultsOfProcessedEmergencies.get("Health")));
        builder.append(String.format(
                "Total Special Cases Processed: %d", this.resultsOfProcessedEmergencies.get("Order")));
        return builder.toString();
    }

    private void checkForRetiredCenters() {
        for (Register<EmergencyCenter> centerRegister : this.centers.values()) {
            List<EmergencyCenter> temp = new LinkedList<>();
            while (!centerRegister.isEmpty()) {
                EmergencyCenter currentCenter = centerRegister.dequeueEmergency();
                if (currentCenter.isForRetirement()) {
                    continue;
                }
                temp.add(currentCenter);
            }
            for (EmergencyCenter emergencyCenter : temp) {
                centerRegister.enqueueEmergency(emergencyCenter);
            }
        }
    }

    private void initCentersCollection() {
        this.centers = new HashMap<>();
        this.centers.put("Property", new EmergencyRegister<>());
        this.centers.put("Health", new EmergencyRegister<>());
        this.centers.put("Order", new EmergencyRegister<>());
    }

    private void initEmergenciesCollection() {
        this.emergencies = new HashMap<>();
        this.emergencies.put("Property", new EmergencyRegister<>());
        this.emergencies.put("Health", new EmergencyRegister<>());
        this.emergencies.put("Order", new EmergencyRegister<>());
    }

    private void initResultsCollection() {
        this.resultsOfProcessedEmergencies = new HashMap<>();
        this.resultsOfProcessedEmergencies.put("Property", 0L);
        this.resultsOfProcessedEmergencies.put("Health", 0L);
        this.resultsOfProcessedEmergencies.put("Order", 0L);
    }
}
