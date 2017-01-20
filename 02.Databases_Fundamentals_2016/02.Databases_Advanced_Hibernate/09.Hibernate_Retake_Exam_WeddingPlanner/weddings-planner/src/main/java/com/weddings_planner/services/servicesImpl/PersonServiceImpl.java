package com.weddings_planner.services.servicesImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weddings_planner.dtos.PersonImportDto;
import com.weddings_planner.entities.Person;
import com.weddings_planner.enums.Gender;
import com.weddings_planner.io.parsers.modelParsers.ModelParser;
import com.weddings_planner.repositories.PersonRepository;
import com.weddings_planner.services.PersonService;
import com.weddings_planner.validation.EmailValidator;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	private static final DateFormat DATE_FORMAT = 
			new SimpleDateFormat("YYYY-MM-DD'T'HH:mm:ss");
			
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelParser modelParser;
    
    @Autowired
    private EmailValidator emailValidator;
    
	@Override
	public String save(PersonImportDto personDto) throws ParseException {
		Person person = this.convertToEntity(personDto);
		this.personRepository.save(person);
		person.setFullName();
		return person.getFullName();
	}

	private Person convertToEntity(PersonImportDto personDto) throws ParseException {
		if (personDto.getEmail() != null && !this.emailValidator.isValid(personDto.getEmail())) {
			personDto.setEmail(null);
		}
		if (personDto.getGender() == null) {
			personDto.setGender(Gender.NotSpecified);
		}

		PropertyMap<PersonImportDto, Person> propertyMap = new PropertyMap<PersonImportDto, Person>() {
			@Override
			protected void configure() {
				skip().setBirthday(null);
			}
		};
		Person person = this.modelParser.convert(personDto, Person.class, propertyMap);
		if (personDto.getBirthday() != null) {
			person.setBirthday(DATE_FORMAT.parse(personDto.getBirthday()));
		}
		return person;
	}
}