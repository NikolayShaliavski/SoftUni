package com.weddings_planner.services;

import com.weddings_planner.dtos.InvitationImportDto;

public interface InvitationService {
	
	void save(Long weddingId, InvitationImportDto invitationDto);
}