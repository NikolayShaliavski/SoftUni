package photography.services;

import photography.domain.dtos.importDtos.xml.AccessoryImportDto;

public interface AccessoryService {

    void save(AccessoryImportDto accesoriesImportDto);
}