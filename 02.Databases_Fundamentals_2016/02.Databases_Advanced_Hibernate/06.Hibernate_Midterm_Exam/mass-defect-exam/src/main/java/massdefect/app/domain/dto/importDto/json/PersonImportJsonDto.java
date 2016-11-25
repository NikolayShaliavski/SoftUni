package massdefect.app.domain.dto.importDto.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class PersonImportJsonDto implements Serializable {

    @Expose
    private String name;
    @Expose
    private String homePlanet;

    public PersonImportJsonDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomePlanet() {
        return this.homePlanet;
    }

    public void setHomePlanet(String homePlanet) {
        this.homePlanet = homePlanet;
    }
}
