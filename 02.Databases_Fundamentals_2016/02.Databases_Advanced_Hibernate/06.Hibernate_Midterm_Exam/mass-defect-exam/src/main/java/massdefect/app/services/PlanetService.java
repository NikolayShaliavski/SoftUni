package massdefect.app.services;

import massdefect.app.domain.dto.importDto.json.PlanetImportJsonDto;
import massdefect.app.domain.dto.exportDto.json.PlanetExportJsonDto;
import massdefect.app.domain.entities.planets.Planet;

import java.util.List;

public interface PlanetService {

    void savePlanet(PlanetImportJsonDto planetImportJsonDto);

    List<PlanetExportJsonDto> findPlanetsWithoutTeleports();

    Planet findByName(String name);

    PlanetExportJsonDto createPlanetDto(Planet planet);
}