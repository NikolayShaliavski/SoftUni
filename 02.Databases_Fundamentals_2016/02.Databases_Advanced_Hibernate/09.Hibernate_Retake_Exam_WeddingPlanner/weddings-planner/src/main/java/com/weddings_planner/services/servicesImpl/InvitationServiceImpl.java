package com.weddings_planner.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.weddings_planner.repositories.InvitationRepository;
import com.weddings_planner.services.InvitationService;

@Service
@Transactional
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;

}