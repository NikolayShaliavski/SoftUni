package app.services;

import app.domain.batches.ProductionBatch;

public interface ProductionBatchService {

    void create(ProductionBatch productionBatch);
}