package photography.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import photography.constants.FileExportConstants;
import photography.constants.FileImportConstants;
import photography.domain.dtos.exportDtos.json.LandscapePhotographerExportDto;
import photography.domain.dtos.exportDtos.json.PhotographerExportDto;
import photography.domain.dtos.exportDtos.xml.LocationsExportDto;
import photography.domain.dtos.exportDtos.xml.PhotographersSameCamerasDto;
import photography.domain.dtos.importDtos.json.CameraImportDto;
import photography.domain.dtos.importDtos.json.LensImportDto;
import photography.domain.dtos.importDtos.json.PhotographerImportDto;
import photography.domain.dtos.importDtos.xml.AccesoriesImportDto;
import photography.domain.dtos.importDtos.xml.AccessoryImportDto;
import photography.domain.dtos.importDtos.xml.WorkshopImportDto;
import photography.domain.dtos.importDtos.xml.WorkshopsImportDto;
import photography.io.parsers.fileParsers.Parser;
import photography.io.writers.Writer;
import photography.services.*;
import photography.validator.CustomValidator;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    @Autowired
    @Qualifier(value = "JSONParser")
    private Parser jsonParser;

    @Autowired
    @Qualifier(value = "XmlParser")
    private Parser xmlParser;

    @Autowired
    @Qualifier(value = "ConsoleWriter")
    private Writer consoleWriter;

    @Autowired
    private CustomValidator validator;

    @Autowired
    private LensService lensService;

    @Autowired
    private CameraService cameraService;

    @Autowired
    private PhotographerService photographerService;

    @Autowired
    private AccessoryService accessoryService;

    @Autowired
    private WorkshopService workshopService;

    @Override
    public void run(String... strings) throws Exception {
        this.importData();
        this.exportData();
    }

    private void importData() {
        this.importLenses();
        this.importCameras();
        this.importPhotographers();
        this.importAccessories();
        this.importWorkshops();
    }

    private void exportData() {
        this.exportPhotographers();
        this.exportLandscapePhotographers();
        this.exportSameCameraPhotographers();
        this.exportLocations();
    }

    private void exportLocations() {
        LocationsExportDto locationsDto =
                this.workshopService.findWorkshopsByLocation();
        try {
            this.xmlParser.write(locationsDto, FileExportConstants.WORKSHOPS_BY_LOCATION_XML);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exportSameCameraPhotographers() {
        PhotographersSameCamerasDto photographersDto =
                this.photographerService.findAllPhotographersWithSameCameras();
        try {
            this.xmlParser.write(photographersDto, FileExportConstants.SAME_CAMERA_PHOTOGRAPHERS_XML);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exportLandscapePhotographers() {
        List<LandscapePhotographerExportDto> landscapePhotographers =
                this.photographerService.findLandscapePhotographers();
        try {
            this.jsonParser.write(landscapePhotographers, FileExportConstants.LANDSCAPE_PHOTOGRAPHERS_JSON);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exportPhotographers() {
        List<PhotographerExportDto> photographerDtos =
                this.photographerService.findAllPhotographers();
        try {
            this.jsonParser.write(photographerDtos, FileExportConstants.PHOTOGRAPHERS_JSON);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void importWorkshops() {
        try {
            WorkshopsImportDto allWorkshopDto =
                    this.xmlParser.read(
                            WorkshopsImportDto.class,
                            FileImportConstants.WORKSHOPS_XML);
            List<WorkshopImportDto> workshopDtos = allWorkshopDto.getWorkshopDtos();

            for (WorkshopImportDto workshopDto : workshopDtos) {
                if (this.validator.isValid(workshopDto)) {
                    this.workshopService.save(workshopDto);
                    this.consoleWriter.write(
                            String.format("Successfully imported %s",
                                    workshopDto.getName()));
                } else {
                    this.consoleWriter.write("Error. Invalid data provided");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void importAccessories() {
        try {
            AccesoriesImportDto accessoriesDto = this.xmlParser.read(
                    AccesoriesImportDto.class,
                    FileImportConstants.ACCESSORIES_XML);

            for (AccessoryImportDto accessoryDto : accessoriesDto.getAccessories()) {
                this.accessoryService.save(accessoryDto);
                this.consoleWriter.write(
                        String.format("Successfully imported %s",
                                accessoryDto.getName()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void importPhotographers() {
        try {
            PhotographerImportDto[] photographerDtos = this.jsonParser.read(
                    PhotographerImportDto[].class,
                    FileImportConstants.PHOTOGRAPHERS_JSON);
            for (PhotographerImportDto photographerDto : photographerDtos) {
                if (this.validator.isValid(photographerDto)) {
                    Integer savedLenses = this.photographerService.save(photographerDto);
                    this.consoleWriter.write(
                            String.format("Successfully imported %s %s | Lenses: %d",
                                    photographerDto.getFirstName(),
                                    photographerDto.getLastName(),
                                    savedLenses));
                } else {
                    this.consoleWriter.write("Error. Invalid data provided");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void importCameras() {
        try {
            CameraImportDto[] cameraDtos = this.jsonParser.read(
                    CameraImportDto[].class,
                    FileImportConstants.CAMERAS_JSON);
            for (CameraImportDto cameraDto : cameraDtos) {
                if (this.validator.isValid(cameraDto)) {
                    this.cameraService.save(cameraDto);
                    this.consoleWriter.write(
                            String.format("Successfully imported %s %s %s",
                                    cameraDto.getType(),
                                    cameraDto.getMake(),
                                    cameraDto.getModel()));
                } else {
                    this.consoleWriter.write("Error. Invalid data provided");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void importLenses() {
        try {
            LensImportDto[] lensDtos =
                    this.jsonParser.read(
                            LensImportDto[].class,
                            FileImportConstants.LENSES_JSON);
            for (LensImportDto lensDto : lensDtos) {
                if (this.validator.isValid(lensDto)) {
                    this.lensService.save(lensDto);
                    this.consoleWriter.write(
                            String.format("Successfully imported %s %dmm f%.1f",
                                    lensDto.getMake(),
                                    lensDto.getFocalLength(),
                                    lensDto.getMaxAperture()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
