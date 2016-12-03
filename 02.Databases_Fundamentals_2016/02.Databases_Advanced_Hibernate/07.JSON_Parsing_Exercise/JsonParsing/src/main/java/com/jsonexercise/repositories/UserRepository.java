package com.jsonexercise.repositories;

import com.jsonexercise.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT u FROM User AS u " +
            "INNER JOIN u.soldProducts AS p " +
            "WHERE p.buyer IS NOT NULL " +
            "ORDER BY u.lastName, u.firstName")
    List<User> findAllWithSoldProducts();

    @Query(value = "SELECT u FROM User AS u " +
            "INNER JOIN u.soldProducts AS p " +
            "GROUP BY u " +
            "ORDER BY COUNT(p.id) DESC, u.lastName")
    List<User> findAllWithAtLeastOneSoldProduct();
}