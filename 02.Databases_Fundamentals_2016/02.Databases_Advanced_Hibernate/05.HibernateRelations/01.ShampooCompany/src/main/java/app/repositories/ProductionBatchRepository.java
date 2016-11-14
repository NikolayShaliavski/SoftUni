package app.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.domain.batches.ProductionBatch;

@Repository
public interface ProductionBatchRepository extends JpaRepository<ProductionBatch,Long> {
}