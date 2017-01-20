package com.weddings_planner.repositories;

import com.weddings_planner.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
	
	@Query(value = "select p from Person AS p " +
            "where p.firstName = :firstName "
            + "and p.middleInitial = :middleInitial "
            + "and p.lastName = :lastName ")
	Person findByFullName(@Param(value = "firstName") String firstName,
			@Param(value = "middleInitial") String middleInitial, 
			@Param(value = "lastName") String lastName);
}