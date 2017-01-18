package com.weddings_planner.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.weddings_planner.repositories.VenueRepository;
import com.weddings_planner.services.VenueService;

@Service
@Transactional
public class VenueServiceImpl implements VenueService {

    @Autowired
    private VenueRepository venueRepository;

}