package massdefect.app.services.servicesImpl;

import massdefect.app.domain.dto.exportDto.json.AnomalyExportJsonDto;
import massdefect.app.domain.dto.exportDto.xml.AnomaliesExportXmlDto;
import massdefect.app.domain.dto.exportDto.xml.AnomalyExportXmlDto;
import massdefect.app.domain.dto.importDto.json.AnomalyImportJsonDto;
import massdefect.app.domain.dto.importDto.json.AnomalyVictimImportJsonDto;
import massdefect.app.domain.dto.exportDto.json.PlanetExportJsonDto;
import massdefect.app.domain.dto.importDto.xml.AnomalyWithVictimImportXmlDto;
import massdefect.app.domain.dto.importDto.xml.VictimXmlDto;
import massdefect.app.domain.entities.anomalies.Anomaly;
import massdefect.app.domain.entities.persons.Person;
import massdefect.app.domain.entities.planets.Planet;
import massdefect.app.repositories.AnomalyRepository;
import massdefect.app.services.AnomalyService;
import massdefect.app.services.PersonService;
import massdefect.app.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AnomalyServiceImpl implements AnomalyService {

    @Autowired
    private AnomalyRepository anomalyRepository;

    @Autowired
    private PlanetService planetService;

    @Autowired
    private PersonService personService;

    @Override
    public void saveAnomaly(AnomalyImportJsonDto anomalyImportJsonDto) {
        String originPlanetName = anomalyImportJsonDto.getOriginPlanet();
        String teleportPlanetName = anomalyImportJsonDto.getTeleportPlanet();

        Anomaly anomaly = this.createAnomaly(originPlanetName, teleportPlanetName);
        this.anomalyRepository.saveAndFlush(anomaly);
    }

    @Override
    public void saveAnomalyVictim(AnomalyVictimImportJsonDto anomalyVictimImportJsonDto) {
        Long anomalyId = anomalyVictimImportJsonDto.getId();
        String personName = anomalyVictimImportJsonDto.getPerson();

        Anomaly anomaly = this.anomalyRepository.getOne(anomalyId);
        Person person = this.personService.findByName(personName);

        if (anomaly == null || person == null) {
            return;
        }
        anomaly.addVictim(person);
        this.anomalyRepository.saveAndFlush(anomaly);
    }

    @Override
    public void saveNewAnomalyWithVictims(AnomalyWithVictimImportXmlDto anomalyVictimsDto) {

        String originPlanetName = anomalyVictimsDto.getOriginPlanet();
        String teleportPlanetName = anomalyVictimsDto.getTeleportPlanet();

        Anomaly anomaly = this.createAnomaly(originPlanetName, teleportPlanetName);

        List<VictimXmlDto> victimXmlDtos = anomalyVictimsDto.getVictims();
        for (VictimXmlDto victimXmlDto : victimXmlDtos) {
            String victimName = victimXmlDto.getName();
            Person victim = this.personService.findByName(victimName);
            if (victim == null) {
                throw new IllegalArgumentException("Error: Invalid data.");
            }
            anomaly.addVictim(victim);
        }
        this.anomalyRepository.saveAndFlush(anomaly);
    }

    @Override
    public AnomalyExportJsonDto exportAnomalyAffectedMostPeople() {
        List<Anomaly> anomalies = this.anomalyRepository.finAnomaliesAffectedPeople();
        Anomaly mostAffectedAnomaly = anomalies.get(0);

        return this.createAnomalyDto(mostAffectedAnomaly);
    }

    @Override
    public AnomaliesExportXmlDto exportAllAnomalies() {
        List<Anomaly> allAnomalies = this.anomalyRepository.findAll();
        AnomaliesExportXmlDto anomaliesExportXmlDto =
                this.createAnomaliesXmlDto(allAnomalies);
        return anomaliesExportXmlDto;
    }

    @Override
    public AnomalyImportJsonDto findById(Long id) {
        return null;
    }

    private AnomaliesExportXmlDto createAnomaliesXmlDto(List<Anomaly> allAnomalies) {
        AnomaliesExportXmlDto anomaliesExportXmlDto = new AnomaliesExportXmlDto();
        for (Anomaly anomaly : allAnomalies) {
            Long anomalyId = anomaly.getId();
            String originPlanetName = anomaly.getOriginPlanet().getName();
            String teleportPlanetName = anomaly.getTeleportPlanet().getName();
            Set<Person> victims = anomaly.getVictims();

            AnomalyExportXmlDto anomalyXmlDto = new AnomalyExportXmlDto();
            anomalyXmlDto.setId(anomalyId);
            anomalyXmlDto.setOriginPlanet(originPlanetName);
            anomalyXmlDto.setTeleportPlanet(teleportPlanetName);

            for (Person victim : victims) {
                VictimXmlDto victimXmlDto = new VictimXmlDto();
                victimXmlDto.setName(victim.getName());
                anomalyXmlDto.addVictim(victimXmlDto);
            }
            anomaliesExportXmlDto.addAnomalyDto(anomalyXmlDto);
        }
        return anomaliesExportXmlDto;
    }

    private AnomalyExportJsonDto createAnomalyDto(Anomaly mostAffectedAnomaly) {
        Long anomalyId = mostAffectedAnomaly.getId();
        PlanetExportJsonDto originPlanet =
                this.planetService.createPlanetDto(mostAffectedAnomaly.getOriginPlanet());
        PlanetExportJsonDto teleportPlanet =
                this.planetService.createPlanetDto(mostAffectedAnomaly.getTeleportPlanet());
        Integer victimsCount = mostAffectedAnomaly.getVictims().size();

        AnomalyExportJsonDto anomalyToExport = new AnomalyExportJsonDto();
        anomalyToExport.setId(anomalyId);
        anomalyToExport.setOriginPlanet(originPlanet);
        anomalyToExport.setTeleportPlanet(teleportPlanet);
        anomalyToExport.setVictimsCount(victimsCount);

        return anomalyToExport;
    }

    private Anomaly createAnomaly(String originPlanetName, String teleportPlanetName) {

        Planet originPlanet = this.planetService.findByName(originPlanetName);
        Planet teleportPlanet = this.planetService.findByName(teleportPlanetName);

        Anomaly anomaly = new Anomaly();
        anomaly.setOriginPlanet(originPlanet);
        anomaly.setTeleportPlanet(teleportPlanet);
        return anomaly;
    }
}