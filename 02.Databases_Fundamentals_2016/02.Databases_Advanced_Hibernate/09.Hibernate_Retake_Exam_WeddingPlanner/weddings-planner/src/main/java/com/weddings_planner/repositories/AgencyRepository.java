package com.weddings_planner.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.weddings_planner.entities.Agency;

@Repository
public interface AgencyRepository extends JpaRepository<Agency,Long> {
	
	Agency findOneByName(String name);
}