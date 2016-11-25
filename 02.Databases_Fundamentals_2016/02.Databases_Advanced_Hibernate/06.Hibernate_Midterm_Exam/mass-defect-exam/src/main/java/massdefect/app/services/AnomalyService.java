package massdefect.app.services;

import massdefect.app.domain.dto.exportDto.json.AnomalyExportJsonDto;
import massdefect.app.domain.dto.importDto.json.AnomalyImportJsonDto;
import massdefect.app.domain.dto.importDto.json.AnomalyVictimImportJsonDto;
import massdefect.app.domain.dto.exportDto.xml.AnomaliesExportXmlDto;
import massdefect.app.domain.dto.importDto.xml.AnomalyWithVictimImportXmlDto;

public interface AnomalyService {

    void saveAnomaly(AnomalyImportJsonDto anomalyImportJsonDto);

    void saveAnomalyVictim(AnomalyVictimImportJsonDto anomalyVictimImportJsonDto);

    void saveNewAnomalyWithVictims(AnomalyWithVictimImportXmlDto anomalyVictimsDto);

    AnomalyExportJsonDto exportAnomalyAffectedMostPeople();

    AnomaliesExportXmlDto exportAllAnomalies();

    AnomalyImportJsonDto findById(Long id);
}