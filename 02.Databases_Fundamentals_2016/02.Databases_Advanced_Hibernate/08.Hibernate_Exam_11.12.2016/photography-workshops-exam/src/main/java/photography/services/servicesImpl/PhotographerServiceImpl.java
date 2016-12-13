package photography.services.servicesImpl;

import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import photography.domain.dtos.exportDtos.json.LandscapePhotographerExportDto;
import photography.domain.dtos.exportDtos.json.PhotographerExportDto;
import photography.domain.dtos.exportDtos.xml.PhotographerSameCamerasDto;
import photography.domain.dtos.exportDtos.xml.PhotographersSameCamerasDto;
import photography.domain.dtos.importDtos.json.PhotographerImportDto;
import photography.domain.entities.cameras.BasicCamera;
import photography.domain.entities.lenses.Lens;
import photography.domain.entities.photographers.Photographer;
import photography.io.parsers.modelParsers.ModelParser;
import photography.repositories.CameraRepository;
import photography.repositories.LensRepository;
import photography.repositories.PhotographerRepository;
import photography.services.PhotographerService;
import photography.validator.PhoneValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class PhotographerServiceImpl implements PhotographerService {

    @Autowired
    private PhotographerRepository photographerRepository;

    @Autowired
    private PhoneValidator phoneValidator;

    @Autowired
    private ModelParser modelParser;

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private LensRepository lensRepository;

    @Override
    public Integer save(PhotographerImportDto photographerDto) {
        Photographer photographer =
                this.convertToEntityImport(photographerDto);
        this.photographerRepository.save(photographer);
        return photographer.getLenses().size();
    }

    @Override
    public List<PhotographerExportDto> findAllPhotographers() {
        List<Photographer> photographers =
                this.photographerRepository.findAllPhotographs();

        List<PhotographerExportDto> photographerDtos =
                this.modelParser.convertToDtos(photographers, PhotographerExportDto.class);
        return photographerDtos;
    }

    @Override
    public List<LandscapePhotographerExportDto> findLandscapePhotographers() {
        List<LandscapePhotographerExportDto> landscapePhotographerDtos = new ArrayList<>();
        List<Photographer> allPhotographers = this.photographerRepository.findLandscapePhotographers();

        for (Photographer photographer : allPhotographers) {
            boolean isLandscape = true;
            if (photographer.getLenses().size() == 0) {
                isLandscape = false;
                continue;
            }
            for (Lens lens : photographer.getLenses()) {
                if (lens.getFocalLength() > 30) {
                    isLandscape = false;
                    break;
                }
            }
            if (isLandscape) {
                LandscapePhotographerExportDto landscapePhotographer =
                        this.convertToDto(photographer);
                landscapePhotographerDtos.add(landscapePhotographer);
            }
        }
        return landscapePhotographerDtos;
    }

    @Override
    public PhotographersSameCamerasDto findAllPhotographersWithSameCameras() {
        List<Photographer> photographers =
                this.photographerRepository.findAllPhotographersWithSameCameras();
        PhotographersSameCamerasDto photographersSameCamerasDto =
                this.convertToDtoExport(photographers);
        return photographersSameCamerasDto;
    }

    private PhotographersSameCamerasDto convertToDtoExport(List<Photographer> photographers) {
        PhotographersSameCamerasDto photographersXmlDto = new PhotographersSameCamerasDto();

        for (Photographer photographer : photographers) {
            PhotographerSameCamerasDto photographerDto = new PhotographerSameCamerasDto();
            String photographerName =
                    photographer.getFirstName() + " " + photographer.getLastName();
            String photographerCamera =
                    photographer.getPrimaryCamera().getMake() + " " +
                            photographer.getPrimaryCamera().getModel();
            photographerDto.setName(photographerName);
            photographerDto.setPrimaryCamera(photographerCamera);
            if (photographer.getLenses().size() == 0) {
                photographerDto.setLenses(null);
            }
            for (Lens lens : photographer.getLenses()) {
                String lensInfo =
                        String.format("%s %dmm f%.1f",
                                lens.getMake(),
                                lens.getFocalLength(),
                                lens.getMaxAperture());
                photographerDto.getLenses().add(lensInfo);
            }
            photographersXmlDto.getPhotographers().add(photographerDto);
        }
        return photographersXmlDto;
    }

    private LandscapePhotographerExportDto convertToDto(Photographer photographer) {
        PropertyMap<Photographer, LandscapePhotographerExportDto> propertyMap =
                new PropertyMap<Photographer, LandscapePhotographerExportDto>() {
                    @Override
                    protected void configure() {
                        map().setLenses(source.getLenses().size());
                    }
                };
        LandscapePhotographerExportDto landscapePhotographerDto =
                this.modelParser.convert(
                        photographer,
                        LandscapePhotographerExportDto.class,
                        propertyMap);
        return landscapePhotographerDto;
    }

    private Photographer convertToEntityImport(PhotographerImportDto photographerDto) {
        Photographer photographer =
                this.modelParser.convert(photographerDto, Photographer.class);
        Long camerasCount = this.cameraRepository.count();
        Long primaryCameraId = ThreadLocalRandom.current().nextLong(1, camerasCount + 1);
        Long secondaryCameraId = ThreadLocalRandom.current().nextLong(1, camerasCount + 1);
        BasicCamera primaryCamera = this.cameraRepository.findOne(primaryCameraId);
        BasicCamera secondaryCamera = this.cameraRepository.findOne(secondaryCameraId);
        photographer.setPrimaryCamera(primaryCamera);
        photographer.setSecondaryCamera(secondaryCamera);

        String phone = photographerDto.getPhone();

        if (phone != null && !this.phoneValidator.isValid(phone)) {
            photographer.setPhone(null);
        }

        for (Long lensId : photographerDto.getLenses()) {
            Lens lens = this.lensRepository.findOne(lensId);
            if (lens != null && lens.getCompatibleWith().equals(primaryCamera.getMake())
                    && lens.getCompatibleWith().equals(secondaryCamera.getMake())) {
                photographer.addLens(lens);
                lens.setOwner(photographer);
            }
        }
        return photographer;
    }
}