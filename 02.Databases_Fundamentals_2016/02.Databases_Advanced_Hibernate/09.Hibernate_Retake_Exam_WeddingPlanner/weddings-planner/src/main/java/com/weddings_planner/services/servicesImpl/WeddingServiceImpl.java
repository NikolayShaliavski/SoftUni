package com.weddings_planner.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.weddings_planner.repositories.WeddingRepository;
import com.weddings_planner.services.WeddingService;

@Service
@Transactional
public class WeddingServiceImpl implements WeddingService {

    @Autowired
    private WeddingRepository weddingRepository;

}