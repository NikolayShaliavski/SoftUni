package com.weddings_planner.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weddings_planner.dtos.AgenciesImportDto;
import com.weddings_planner.entities.Agency;
import com.weddings_planner.io.parsers.modelParsers.ModelParser;
import com.weddings_planner.repositories.AgencyRepository;
import com.weddings_planner.services.AgencyService;

@Service
@Transactional
public class AgencyServiceImpl implements AgencyService {

	@Autowired
	private AgencyRepository agencyRepository;

	@Autowired
	private ModelParser modelParser;

	@Override
	public void save(AgenciesImportDto agencyDto) {
		Agency agency = this.convertToEntity(agencyDto);
		this.agencyRepository.save(agency);
	}

	private Agency convertToEntity(AgenciesImportDto agencyDto) {
		return this.modelParser.convert(agencyDto, Agency.class);
	}
}