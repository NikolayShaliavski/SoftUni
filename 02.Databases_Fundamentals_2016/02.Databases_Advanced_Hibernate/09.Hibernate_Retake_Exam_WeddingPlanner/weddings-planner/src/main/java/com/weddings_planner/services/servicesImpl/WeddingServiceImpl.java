package com.weddings_planner.services.servicesImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weddings_planner.dtos.InvitationImportDto;
import com.weddings_planner.dtos.WeddingImportDto;
import com.weddings_planner.entities.Agency;
import com.weddings_planner.entities.Person;
import com.weddings_planner.entities.Wedding;
import com.weddings_planner.repositories.AgencyRepository;
import com.weddings_planner.repositories.PersonRepository;
import com.weddings_planner.repositories.WeddingRepository;
import com.weddings_planner.services.InvitationService;
import com.weddings_planner.services.WeddingService;

@Service
@Transactional
public class WeddingServiceImpl implements WeddingService {

	private static final DateFormat DATE_FORMAT = 
			new SimpleDateFormat("YYYY-MM-DD'T'HH:mm:ss");
	
    @Autowired
    private WeddingRepository weddingRepository;
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private AgencyRepository agencyRepository;
    
    @Autowired
    private InvitationService invitationService;

	@Override
	public String save(WeddingImportDto weddingDto) throws ParseException {
		Wedding wedding  = this.convertToEntity(weddingDto);
//		if (wedding.getAgency() == null) {
//			return "error";
//		}
		this.weddingRepository.save(wedding);
		
		for (InvitationImportDto invitationDto : weddingDto.getGuests()) {
			this.invitationService.save(wedding.getId(), invitationDto);
		}
		String bridegroomsNames = wedding.getBride().getFirstName() + 
				" and " + wedding.getBridegroom().getFirstName();
		
		return bridegroomsNames;
	}

	private Wedding convertToEntity(WeddingImportDto weddingDto) throws ParseException {
		String[] brideTokens = weddingDto.getBrideFullName().split("[\\s]+");
		String[] brideroomTokens = weddingDto.getBridegRoomFullName().split("[\\s]+");
	
		Person bride = this.personRepository.findByFullName(brideTokens[0], brideTokens[1], brideTokens[2]);
		Person bridegroom = this.personRepository.findByFullName(brideroomTokens[0], brideroomTokens[1], brideroomTokens[2]);
		Agency agency = this.agencyRepository.findOneByName(weddingDto.getAgencyName());
		Date date = DATE_FORMAT.parse(weddingDto.getDate());
		
		Wedding wedding = new Wedding();
		wedding.setBride(bride);
		wedding.setBridegroom(bridegroom);
		wedding.setAgency(agency);
		wedding.setDate(date);
		
		return wedding;
	}

}