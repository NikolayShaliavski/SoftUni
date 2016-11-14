package application.repositories;

import application.entities.WizzardDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WizzardDepositRepository extends JpaRepository<WizzardDeposit, Long> {
}
