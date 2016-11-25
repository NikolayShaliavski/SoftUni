package massdefect.app.io;

import massdefect.app.domain.dto.exportDto.json.AnomalyExportJsonDto;
import massdefect.app.domain.dto.exportDto.json.PersonExportJsonDto;
import massdefect.app.domain.dto.exportDto.json.PlanetExportJsonDto;
import massdefect.app.domain.dto.importDto.json.*;
import massdefect.app.domain.dto.exportDto.xml.AnomaliesExportXmlDto;
import massdefect.app.domain.dto.importDto.xml.AnomaliesImportXmlDto;
import massdefect.app.domain.dto.importDto.xml.AnomalyWithVictimImportXmlDto;
import massdefect.app.io.dataParsers.jsonParsers.JSONParser;
import massdefect.app.io.dataParsers.xmlParsers.XmlParser;
import massdefect.app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    private SolarSystemService solarSystemService;
    private StarService starService;
    private PlanetService planetService;
    private AnomalyService anomalyService;
    private PersonService personService;
    private JSONParser jsonParser;
    private XmlParser xmlParser;

    @Autowired
    public Terminal(SolarSystemService solarSystemService,
                    StarService starService,
                    PlanetService planetService,
                    AnomalyService anomalyService,
                    PersonService personService,
                    JSONParser jsonParser,
                    XmlParser xmlParser) {
        this.solarSystemService = solarSystemService;
        this.starService = starService;
        this.planetService = planetService;
        this.anomalyService = anomalyService;
        this.personService = personService;
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.importDataToDatabase();
        //this.exportDataFromDatabase();
    }

    private void importDataToDatabase() throws JAXBException {
        try {
            this.importSolarSystems();
            this.importStars();
            this.importPlanets();
            this.importPersons();
            this.importAnomalies();
            this.importAnomaliesAndVictims();

            this.importNewAnomalies();

        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        }
    }

    private void exportDataFromDatabase() {
        //this.exportPlanetsWithoutTeleports();
        //this.exportPersonsNoVictims();
        //this.exportAnomalyAffectedMostPeople();
        this.exportAllAnomaliesToXml();
    }

    private void exportAllAnomaliesToXml() {
        AnomaliesExportXmlDto allAnomaliesDtos =
                this.anomalyService.exportAllAnomalies();
        try {
            this.xmlParser.writeToXml(allAnomaliesDtos,
                    "src/main/resources/files/output/xml/anomalies.xml");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void exportAnomalyAffectedMostPeople() {
        AnomalyExportJsonDto anomalyToExport =
                this.anomalyService.exportAnomalyAffectedMostPeople();
        try {
            this.jsonParser.writeToJSON(anomalyToExport,
                    "src/main/resources/files/output/json/anomaly.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exportPersonsNoVictims() {
        List<PersonExportJsonDto> exportedPersons = this.personService.exportPersonsNoVictims();
        try {
            this.jsonParser.writeToJSON(exportedPersons,
                    "src/main/resources/files/output/json/people.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exportPlanetsWithoutTeleports() {
        List<PlanetExportJsonDto> exportedPlanets =
                this.planetService.findPlanetsWithoutTeleports();
        try {
            this.jsonParser.writeToJSON(exportedPlanets,
                    "src/main/resources/files/output/json/planets.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void importNewAnomalies() throws JAXBException {
        AnomaliesImportXmlDto newAnomalies = this.xmlParser.readFromXml(AnomaliesImportXmlDto.class,
                "src/main/resources/files/input/xml/new-anomalies.xml");
        List<AnomalyWithVictimImportXmlDto> anomalies = newAnomalies.getAnomalies();
        for (AnomalyWithVictimImportXmlDto anomaly : anomalies) {
            try {
                this.anomalyService.saveNewAnomalyWithVictims(anomaly);
            } catch (IllegalArgumentException ilex) {
                System.out.println(ilex.getMessage());
            }
        }
    }

    private void importAnomaliesAndVictims() throws IOException {
        AnomalyVictimImportJsonDto[] anomaliesVictims = this.jsonParser.readFromJSON(AnomalyVictimImportJsonDto[].class,
                "/files/input/json/anomaly-victims.json");
        for (AnomalyVictimImportJsonDto anomalyVictim : anomaliesVictims) {
            this.anomalyService.saveAnomalyVictim(anomalyVictim);
        }
    }

    private void importAnomalies() throws IOException {
        AnomalyImportJsonDto[] anomalyImportJsonDtos = this.jsonParser.readFromJSON(AnomalyImportJsonDto[].class,
                "/files/input/json/anomalies.json");
        for (AnomalyImportJsonDto anomalyImportJsonDto : anomalyImportJsonDtos) {
            try {
                this.anomalyService.saveAnomaly(anomalyImportJsonDto);
                System.out.println("Successfully imported anomaly.");
            } catch (IllegalArgumentException ilex) {
                System.out.println(ilex.getMessage());
            }
        }
    }

    private void importPersons() throws IOException {
        PersonImportJsonDto[] personImportJsonDtos = this.jsonParser.readFromJSON(PersonImportJsonDto[].class,
                "/files/input/json/persons.json");
        for (PersonImportJsonDto personImportJsonDto : personImportJsonDtos) {
            try {
                this.personService.savePerson(personImportJsonDto);
                System.out.println(
                        String.format("Successfully imported Person %s.",
                                personImportJsonDto.getName()));
            } catch (IllegalArgumentException ilex) {
                System.out.println(ilex.getMessage());
            }
        }
    }

    private void importPlanets() throws IOException {
        PlanetImportJsonDto[] planetImportJsonDtos = this.jsonParser.readFromJSON(PlanetImportJsonDto[].class,
                "/files/input/json/planets.json");
        for (PlanetImportJsonDto planetImportJsonDto : planetImportJsonDtos) {
            try {
                this.planetService.savePlanet(planetImportJsonDto);
                System.out.println(
                        String.format("Successfully imported Planet %s.",
                                planetImportJsonDto.getName()));
            } catch (IllegalArgumentException ilex) {
                System.out.println(ilex.getMessage());
            }
        }
    }

    private void importStars() throws IOException {
        StarImportJsonDto[] starImportJsonDtos = this.jsonParser.readFromJSON(StarImportJsonDto[].class,
                "/files/input/json/stars.json");
        for (StarImportJsonDto starImportJsonDto : starImportJsonDtos) {
            try {
                this.starService.saveStar(starImportJsonDto);
                System.out.println(
                        String.format("Successfully imported Star %s.",
                                starImportJsonDto.getName()));
            } catch (IllegalArgumentException ilex) {
                System.out.println(ilex.getMessage());
            }
        }
    }

    private void importSolarSystems() throws IOException {
        SolarSystemImportJsonDto[] solarSystemDtos =
                this.jsonParser.readFromJSON(SolarSystemImportJsonDto[].class,
                        "/files/input/json/solar-systems.json");
        for (SolarSystemImportJsonDto solarSystemDto : solarSystemDtos) {
            try {
                this.solarSystemService.saveSolarSystem(solarSystemDto);
                System.out.println(
                        String.format("Successfully imported Solar System %s.",
                                solarSystemDto.getName()));
            } catch (IllegalArgumentException ilex) {
                System.out.println(ilex.getMessage());
            }
        }
    }
}
