package massdefect.app.services;

import massdefect.app.domain.dto.importDto.json.SolarSystemImportJsonDto;
import massdefect.app.domain.entities.solarSystems.SolarSystem;

public interface SolarSystemService {

    void saveSolarSystem(SolarSystemImportJsonDto solarSystemImportJsonDto);

    SolarSystem findByName(String name);
}