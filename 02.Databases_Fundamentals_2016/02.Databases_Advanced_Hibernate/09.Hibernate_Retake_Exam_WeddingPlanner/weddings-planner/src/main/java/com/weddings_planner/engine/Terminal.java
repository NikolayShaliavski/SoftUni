package com.weddings_planner.engine;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.weddings_planner.constants.ImportConstants;
import com.weddings_planner.dtos.AgenciesImportDto;
import com.weddings_planner.io.parsers.fileParsers.Parser;
import com.weddings_planner.io.writers.Writer;
import com.weddings_planner.services.AgencyService;

@Component
public class Terminal implements CommandLineRunner {

	@Autowired
	private AgencyService agencyService;

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

	private void importData() throws FileNotFoundException, IOException {
		this.importAgencies();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
