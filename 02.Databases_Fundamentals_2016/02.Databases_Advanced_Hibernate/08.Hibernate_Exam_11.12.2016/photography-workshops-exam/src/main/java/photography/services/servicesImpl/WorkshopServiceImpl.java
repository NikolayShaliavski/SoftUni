package photography.services.servicesImpl;

import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import photography.domain.dtos.exportDtos.xml.LocationDto;
import photography.domain.dtos.exportDtos.xml.LocationsExportDto;
import photography.domain.dtos.exportDtos.xml.WorkshopDto;
import photography.domain.dtos.importDtos.json.PhotographerImportDto;
import photography.domain.dtos.importDtos.xml.WorkshopImportDto;
import photography.domain.entities.photographers.Photographer;
import photography.domain.entities.workshhops.Workshop;
import photography.io.parsers.modelParsers.ModelParser;
import photography.repositories.PhotographerRepository;
import photography.repositories.WorkshopRepository;
import photography.services.WorkshopService;

import java.util.*;

@Service
@Transactional
public class WorkshopServiceImpl implements WorkshopService {

    @Autowired
    private ModelParser modelParser;

    @Autowired
    private WorkshopRepository workshopRepository;

    @Autowired
    private PhotographerRepository photographerRepository;

    @Override
    public void save(WorkshopImportDto workshopDto) {
        Workshop workshop = this.convertToEntity(workshopDto);
        this.workshopRepository.save(workshop);
    }

    @Override
    public LocationsExportDto findWorkshopsByLocation() {
        LocationsExportDto locationsExportDto = this.groupWorkshopsByLocation();
        return locationsExportDto;
    }

    private LocationsExportDto groupWorkshopsByLocation() {
        List<Object[]> workshopsAndProfits = this.workshopRepository.findWorkshopsByLocation();
        LocationsExportDto locationsExportDto = new LocationsExportDto();

        Map<String, List<WorkshopDto>> workshopDtosByLocation = new HashMap<>();
        for (Object[] workshopsAndProfit : workshopsAndProfits) {
            Workshop workshop = (Workshop) workshopsAndProfit[0];
            Double totalProfit = (Double) workshopsAndProfit[1];

            PropertyMap<Workshop, WorkshopDto> propertyMap =
                    new PropertyMap<Workshop, WorkshopDto>() {
                        @Override
                        protected void configure() {
                            map().getParticipants().setCount(source.getParticipants().size());
                        }
                    };
            WorkshopDto workshopDto =
                    this.modelParser.convert(workshop, WorkshopDto.class, propertyMap);
            workshopDto.setTotalProfit(totalProfit);

            workshopDto.getParticipants().setParticipants(new ArrayList<>());
            for (Photographer photographer : workshop.getParticipants()) {
                String participantName =
                        photographer.getFirstName() + " " + photographer.getLastName();
                workshopDto.getParticipants().getParticipants().add(participantName);
            }

            String locationName = workshop.getLocation();
            if (!workshopDtosByLocation.containsKey(locationName)) {
                workshopDtosByLocation.put(locationName, new ArrayList<>());
            }
            workshopDtosByLocation.get(locationName).add(workshopDto);
        }

        for (Map.Entry<String,List<WorkshopDto>> locationEntry : workshopDtosByLocation.entrySet()) {

            String locationName = locationEntry.getKey();
            List<WorkshopDto> workshopDtos = locationEntry.getValue();
            LocationDto locationDto = new LocationDto();
            locationDto.setName(locationName);
            locationDto.setWorkshops(workshopDtos);

            locationsExportDto.getLocations().add(locationDto);
        }

        return locationsExportDto;
    }

    private Workshop convertToEntity(WorkshopImportDto workshopDto) {
        Workshop workshop = this.modelParser.convert(workshopDto, Workshop.class);

        String[] trainerFullName = workshopDto.getTrainer().split("[\\s]");
        Photographer trainer =
                this.photographerRepository.findByFirstNameAndLastName(
                        trainerFullName[0],
                        trainerFullName[1]);

        workshop.setTrainer(trainer);

        if (workshopDto.getParticipants() == null) {
            return workshop;
        }
        workshop.setParticipants(new HashSet<>());
        List<PhotographerImportDto> participants = workshopDto.getParticipants();
        for (PhotographerImportDto participantDto : participants) {
            Photographer participant =
                    this.photographerRepository.findByFirstNameAndLastName(
                            participantDto.getFirstName(),
                            participantDto.getLastName());

            workshop.addParticipant(participant);
        }
        return workshop;
    }
}