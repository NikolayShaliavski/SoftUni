package com.weddings_planner.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.weddings_planner.entities.presents.Present;

@Repository
public interface PresentRepository extends JpaRepository<Present,Long> {
}