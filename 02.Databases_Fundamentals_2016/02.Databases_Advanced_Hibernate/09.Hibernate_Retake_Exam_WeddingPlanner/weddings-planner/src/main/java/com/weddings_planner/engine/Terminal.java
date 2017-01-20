package com.weddings_planner.engine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.weddings_planner.constants.ImportConstants;
import com.weddings_planner.dtos.AgenciesImportDto;
import com.weddings_planner.dtos.PersonImportDto;
import com.weddings_planner.dtos.WeddingImportDto;
import com.weddings_planner.io.parsers.fileParsers.Parser;
import com.weddings_planner.io.writers.Writer;
import com.weddings_planner.services.AgencyService;
import com.weddings_planner.services.PersonService;
import com.weddings_planner.services.WeddingService;
import com.weddings_planner.validation.CustomValidator;

@Component
public class Terminal implements CommandLineRunner {

	@Autowired
	private AgencyService agencyService;

	@Autowired
	private PersonService personService;
	
	@Autowired
	private WeddingService weddingService;
	
	@Autowired
	private CustomValidator customValidator;
	
	@Autowired
	@Qualifier(value = "JsonParser")
	private Parser jsonParser;

	@Autowired
	@Qualifier(value = "ConsoleWriter")
	private Writer consoleWriter;

	@Override
	public void run(String... arg0) throws Exception {
		this.importData();
	}

	private void importData() {
		this.importAgencies();
		try {
			
			this.importPeople();
			this.importWeddingsAndInvitations();
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void importWeddingsAndInvitations() throws FileNotFoundException, IOException, ParseException {
		WeddingImportDto[] weddingDtos = 
				this.jsonParser.read(WeddingImportDto[].class, ImportConstants.JSON_WEDDINGS);
		for (WeddingImportDto weddingImportDto : weddingDtos) {
			if (this.customValidator.isValid(weddingImportDto)) {
				String bridegroomsNames = this.weddingService.save(weddingImportDto);
//				if (bridegroomsNames.equals("error")) {
//					System.out.println("Error. Invalid data provided.");
//				} else {
//					
//				}
				System.out.printf("Successfully imported wedding of %s%n", bridegroomsNames);
			} else {
				System.out.println("Error. Invalid data provided.");
			}
		}
	}

	private void importPeople() throws ParseException {
		try {
			PersonImportDto[] peopleImportDtos = 
					this.jsonParser.read(PersonImportDto[].class, ImportConstants.JSON_PEOPLE);
			for (PersonImportDto personImportDto : peopleImportDtos) {
				if (this.customValidator.isValid(personImportDto)) {
					String fullName = this.personService.save(personImportDto);
					System.out.printf("Successfully imported %s%n", fullName);
				} else {
					System.out.println("Error. Invalid data provided");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void importAgencies() {

		try {
			AgenciesImportDto[] agencyImportDtos = this.jsonParser.read(AgenciesImportDto[].class,
					ImportConstants.JSON_AGENCIES);

			for (AgenciesImportDto agencyDto : agencyImportDtos) {
				this.agencyService.save(agencyDto);
				this.consoleWriter.write(String.format("Successfully imported %s", agencyDto.getName()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
