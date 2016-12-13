package photography.services;

import photography.domain.dtos.exportDtos.xml.LocationsExportDto;
import photography.domain.dtos.importDtos.xml.WorkshopImportDto;

public interface WorkshopService {

    void save(WorkshopImportDto workshopDto);

    LocationsExportDto findWorkshopsByLocation();
}