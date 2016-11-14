package app.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.domain.shampoos.BasicShampoo;

@Repository
public interface BasicShampooRepository extends JpaRepository<BasicShampoo,Long> {
}