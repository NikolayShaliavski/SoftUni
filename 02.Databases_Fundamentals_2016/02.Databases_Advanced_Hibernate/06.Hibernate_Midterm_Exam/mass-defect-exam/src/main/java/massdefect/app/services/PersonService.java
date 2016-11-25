package massdefect.app.services;

import massdefect.app.domain.dto.exportDto.json.PersonExportJsonDto;
import massdefect.app.domain.dto.importDto.json.PersonImportJsonDto;
import massdefect.app.domain.entities.persons.Person;

import java.util.List;

public interface PersonService {

    void savePerson(PersonImportJsonDto personImportJsonDto);

    List<PersonExportJsonDto> exportPersonsNoVictims();

    Person findByName(String name);
}