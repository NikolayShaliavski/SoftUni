package massdefect.app.services;

import massdefect.app.domain.dto.importDto.json.StarImportJsonDto;
import massdefect.app.domain.entities.stars.Star;

public interface StarService {

    void saveStar(StarImportJsonDto starImportJsonDto);

    Star findByName(String name);
}