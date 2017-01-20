package com.weddings_planner.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weddings_planner.dtos.InvitationImportDto;
import com.weddings_planner.entities.Invitation;
import com.weddings_planner.entities.Person;
import com.weddings_planner.entities.Wedding;
import com.weddings_planner.repositories.InvitationRepository;
import com.weddings_planner.repositories.PersonRepository;
import com.weddings_planner.repositories.WeddingRepository;
import com.weddings_planner.services.InvitationService;

@Service
@Transactional
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private WeddingRepository weddingRepository;

	@Override
	public void save(Long weddingId, InvitationImportDto invitationDto) {
		
		Invitation invitation = this.convertToEntity(invitationDto);
		if (invitation == null) {
			return;
		}
		Wedding wedding = this.weddingRepository.findOne(weddingId);
		invitation.setWedding(wedding);
		
		this.invitationRepository.save(invitation);
	}

	private Invitation convertToEntity(InvitationImportDto invitationDto) {
		String[] guestTokens = invitationDto.getGuestName().split("[\\s]+");
		Person guest = this.personRepository.findByFullName(guestTokens[0], guestTokens[1], guestTokens[2]);
		if (guest == null) {
			return null;
		}
		
		Invitation invitation = new Invitation();
		invitation.setGuest(guest);
		invitation.setAttending(invitationDto.getAttending());
		invitation.setFamily(invitationDto.getFamily());
		
		return invitation;
	}

}