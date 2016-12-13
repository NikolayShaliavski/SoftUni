package photography.services;

import photography.domain.dtos.exportDtos.json.LandscapePhotographerExportDto;
import photography.domain.dtos.exportDtos.json.PhotographerExportDto;
import photography.domain.dtos.exportDtos.xml.PhotographersSameCamerasDto;
import photography.domain.dtos.importDtos.json.PhotographerImportDto;

import java.util.List;

public interface PhotographerService {

    Integer save(PhotographerImportDto photographerDto);

    List<PhotographerExportDto> findAllPhotographers();

    List<LandscapePhotographerExportDto> findLandscapePhotographers();

    PhotographersSameCamerasDto findAllPhotographersWithSameCameras();
}