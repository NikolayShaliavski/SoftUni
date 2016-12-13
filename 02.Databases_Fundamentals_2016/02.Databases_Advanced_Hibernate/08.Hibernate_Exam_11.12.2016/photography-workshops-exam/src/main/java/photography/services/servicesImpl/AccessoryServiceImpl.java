package photography.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import photography.domain.dtos.importDtos.xml.AccessoryImportDto;
import photography.domain.entities.accessories.Accessory;
import photography.domain.entities.photographers.Photographer;
import photography.io.parsers.modelParsers.ModelParser;
import photography.repositories.AccessoryRepository;
import photography.repositories.PhotographerRepository;
import photography.services.AccessoryService;

import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class AccessoryServiceImpl implements AccessoryService {

    @Autowired
    private AccessoryRepository accessoryRepository;

    @Autowired
    private PhotographerRepository photographerRepository;

    @Autowired
    private ModelParser modelParser;

    @Override
    public void save(AccessoryImportDto accessoryImportDto) {
        Accessory accessory = this.convertToEntity(accessoryImportDto);
        this.accessoryRepository.save(accessory);
    }

    private Accessory convertToEntity(AccessoryImportDto accessoryImportDto) {
        Accessory accessory = this.modelParser.convert(accessoryImportDto, Accessory.class);
        Long photographersCount = this.photographerRepository.count();
        Long photographerId = ThreadLocalRandom.current().nextLong(1, photographersCount + 1);
        Photographer photographer = this.photographerRepository.findOne(photographerId);
        accessory.setOwner(photographer);
        return accessory;
    }
}