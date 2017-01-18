package com.weddings_planner.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.weddings_planner.entities.Wedding;

@Repository
public interface WeddingRepository extends JpaRepository<Wedding,Long> {
}