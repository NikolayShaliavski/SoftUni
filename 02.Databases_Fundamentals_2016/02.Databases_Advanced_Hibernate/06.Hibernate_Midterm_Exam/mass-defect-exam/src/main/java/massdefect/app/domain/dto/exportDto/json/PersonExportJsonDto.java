package massdefect.app.domain.dto.exportDto.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class PersonExportJsonDto implements Serializable {

    @Expose
    private String name;

    @Expose
    private PlanetExportJsonDto homePlanet;

    public PersonExportJsonDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlanetExportJsonDto getHomePlanet() {
        return this.homePlanet;
    }

    public void setHomePlanet(PlanetExportJsonDto homePlanet) {
        this.homePlanet = homePlanet;
    }
}
