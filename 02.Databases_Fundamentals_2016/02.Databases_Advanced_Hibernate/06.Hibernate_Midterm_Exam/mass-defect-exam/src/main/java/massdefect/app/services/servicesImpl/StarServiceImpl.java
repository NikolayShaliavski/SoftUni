package massdefect.app.services.servicesImpl;

import massdefect.app.domain.dto.importDto.json.StarImportJsonDto;
import massdefect.app.domain.entities.solarSystems.SolarSystem;
import massdefect.app.domain.entities.stars.Star;
import massdefect.app.services.SolarSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import massdefect.app.repositories.StarRepository;
import massdefect.app.services.StarService;

@Service
@Transactional
public class StarServiceImpl implements StarService {

    @Autowired
    private StarRepository starRepository;

    //hold here repositories, not services
    @Autowired
    private SolarSystemService solarSystemService;

    @Override
    public void saveStar(StarImportJsonDto starImportJsonDto) {
        Star star = this.createStar(starImportJsonDto);
        this.starRepository.saveAndFlush(star);
    }

    @Override
    public Star findByName(String name) {
        return this.starRepository.findByName(name);
    }

    private Star createStar(StarImportJsonDto starImportJsonDto) {
        String starName = starImportJsonDto.getName();
        String solarSystemName = starImportJsonDto.getSolarSystem();
        SolarSystem solarSystem = this.solarSystemService.findByName(solarSystemName);
        Star star = new Star();
        star.setName(starName);
        star.setSolarSystem(solarSystem);
        return star;
    }
}