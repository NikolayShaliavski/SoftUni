package photography.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import photography.domain.dtos.importDtos.json.CameraImportDto;
import photography.domain.entities.cameras.BasicCamera;
import photography.domain.entities.cameras.DSLRCamera;
import photography.domain.entities.cameras.MirrorlessCamera;
import photography.io.parsers.modelParsers.ModelParser;
import photography.repositories.CameraRepository;
import photography.services.CameraService;

@Service
@Transactional
public class CameraServiceImpl implements CameraService {

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private ModelParser modelParser;

    @Override
    public void save(CameraImportDto cameraDto) {
        BasicCamera camera = this.convertToEntity(cameraDto);
        this.cameraRepository.save(camera);
    }

    private BasicCamera convertToEntity(CameraImportDto cameraDto) {
        String cameraType = cameraDto.getType();
        BasicCamera camera = null;
        switch (cameraType) {
            case "DSLR":
                camera = this.modelParser.convert(cameraDto, DSLRCamera.class);
                break;
            case "Mirrorless":
                camera = this.modelParser.convert(cameraDto, MirrorlessCamera.class);
                break;
        }
        return camera;
    }
}