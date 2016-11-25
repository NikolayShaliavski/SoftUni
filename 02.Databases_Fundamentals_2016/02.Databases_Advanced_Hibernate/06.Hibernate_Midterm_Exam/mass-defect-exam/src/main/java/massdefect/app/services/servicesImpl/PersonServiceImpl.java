package massdefect.app.services.servicesImpl;

import massdefect.app.domain.dto.exportDto.json.PersonExportJsonDto;
import massdefect.app.domain.dto.importDto.json.PersonImportJsonDto;
import massdefect.app.domain.dto.exportDto.json.PlanetExportJsonDto;
import massdefect.app.domain.entities.persons.Person;
import massdefect.app.domain.entities.planets.Planet;
import massdefect.app.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import massdefect.app.repositories.PersonRepository;
import massdefect.app.services.PersonService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PlanetService planetService;

    @Override
    public void savePerson(PersonImportJsonDto personImportJsonDto) {
        Person person = this.createPerson(personImportJsonDto);
        this.personRepository.saveAndFlush(person);
    }

    @Override
    public List<PersonExportJsonDto> exportPersonsNoVictims() {
        List<Person> exportedPersons = this.personRepository.findPersonsNoVictims();
        List<PersonExportJsonDto> personDtos = new ArrayList<>();
        for (Person exportedPerson : exportedPersons) {
            PersonExportJsonDto personDto = this.createPersonDto(exportedPerson);
            personDtos.add(personDto);
        }
        return personDtos;
    }

    @Override
    public Person findByName(String name) {
        return this.personRepository.findByName(name);
    }

    private PersonExportJsonDto createPersonDto(Person exportedPerson) {
        String name = exportedPerson.getName();
        PlanetExportJsonDto homePlanet =
                this.planetService.createPlanetDto(exportedPerson.getHomePlanet());
        PersonExportJsonDto personDto = new PersonExportJsonDto();
        personDto.setName(name);
        personDto.setHomePlanet(homePlanet);

        return personDto;
    }

    private Person createPerson(PersonImportJsonDto personImportJsonDto) {
        String personName = personImportJsonDto.getName();
        String homePlanetName = personImportJsonDto.getHomePlanet();

        Planet homePlanet = this.planetService.findByName(homePlanetName);

        Person person = new Person();
        person.setName(personName);
        person.setHomePlanet(homePlanet);

        return person;
    }
}