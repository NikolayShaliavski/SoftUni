package massdefect.app.services.servicesImpl;

import massdefect.app.domain.dto.importDto.json.PlanetImportJsonDto;
import massdefect.app.domain.dto.exportDto.json.PlanetExportJsonDto;
import massdefect.app.domain.entities.planets.Planet;
import massdefect.app.domain.entities.solarSystems.SolarSystem;
import massdefect.app.domain.entities.stars.Star;
import massdefect.app.repositories.PlanetRepository;
import massdefect.app.services.PlanetService;
import massdefect.app.services.SolarSystemService;
import massdefect.app.services.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    private PlanetRepository planetRepository;

    //hold here repositories, not services
    @Autowired
    private SolarSystemService solarSystemService;

    @Autowired
    private StarService starService;

    @Override
    public void savePlanet(PlanetImportJsonDto planetImportJsonDto) {
        Planet planet = this.createPlanet(planetImportJsonDto);
        this.planetRepository.saveAndFlush(planet);
    }

    @Override
    public List<PlanetExportJsonDto> findPlanetsWithoutTeleports() {
        List<PlanetExportJsonDto> planets = new ArrayList<>();
        List<Planet> exportedPlanets = this.planetRepository.findPlanetsWithoutTeleports();
        for (Planet exportedPlanet : exportedPlanets) {
            planets.add(this.createPlanetDto(exportedPlanet));
        }
        return planets;
    }

    @Override
    public Planet findByName(String name) {
        return this.planetRepository.findByName(name);
    }

    public PlanetExportJsonDto createPlanetDto(Planet planet) {
        PlanetExportJsonDto planetDto = new PlanetExportJsonDto();
        planetDto.setName(planet.getName());
        return planetDto;
    }

    private Planet createPlanet(PlanetImportJsonDto planetImportJsonDto) {
        String planetName = planetImportJsonDto.getName();
        String sunName = planetImportJsonDto.getSun();
        String solarSystemName = planetImportJsonDto.getSolarSystem();

        Star sun = this.starService.findByName(sunName);
        SolarSystem solarSystem = this.solarSystemService.findByName(solarSystemName);

        Planet planet = new Planet();
        planet.setName(planetName);
        planet.setSun(sun);
        planet.setSolarSystem(solarSystem);
        return planet;
    }
}