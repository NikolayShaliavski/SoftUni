package com.weddings_planner.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.weddings_planner.repositories.PresentRepository;
import com.weddings_planner.services.PresentService;

@Service
@Transactional
public class PresentServiceImpl implements PresentService {

    @Autowired
    private PresentRepository presentRepository;

}