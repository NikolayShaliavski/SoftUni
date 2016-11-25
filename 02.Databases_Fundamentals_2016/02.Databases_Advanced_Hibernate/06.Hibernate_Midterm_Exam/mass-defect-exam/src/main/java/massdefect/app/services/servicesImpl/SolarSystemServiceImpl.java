package massdefect.app.services.servicesImpl;

import massdefect.app.domain.dto.importDto.json.SolarSystemImportJsonDto;
import massdefect.app.domain.entities.solarSystems.SolarSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import massdefect.app.repositories.SolarSystemRepository;
import massdefect.app.services.SolarSystemService;

@Service
@Transactional
public class SolarSystemServiceImpl implements SolarSystemService {

    @Autowired
    private SolarSystemRepository solarSystemRepository;

    @Override
    public void saveSolarSystem(SolarSystemImportJsonDto solarSystemImportJsonDto) {
        SolarSystem solarSystem = this.createSolarSystem(solarSystemImportJsonDto);
        this.solarSystemRepository.saveAndFlush(solarSystem);
    }

    @Override
    public SolarSystem findByName(String name) {
        return this.solarSystemRepository.findByName(name);
    }

    private SolarSystem createSolarSystem(SolarSystemImportJsonDto solarSystemImportJsonDto) {
        String solarSystemName = solarSystemImportJsonDto.getName();
        SolarSystem solarSystem = new SolarSystem();
        solarSystem.setName(solarSystemName);
        return solarSystem;
    }
}