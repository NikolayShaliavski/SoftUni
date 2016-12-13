package photography.services;

import photography.domain.dtos.importDtos.json.CameraImportDto;

public interface CameraService {

    void save(CameraImportDto cameraDto);
}