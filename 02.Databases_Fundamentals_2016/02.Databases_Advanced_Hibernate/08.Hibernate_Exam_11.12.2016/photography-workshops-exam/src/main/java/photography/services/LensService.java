package photography.services;

import photography.domain.dtos.importDtos.json.LensImportDto;

public interface LensService {

    void save(LensImportDto lensDto);
}