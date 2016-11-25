package massdefect.app.domain.dto.importDto.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class PlanetImportJsonDto implements Serializable {

    @Expose
    private String name;
    @Expose
    private String sun;
    @Expose
    private String solarSystem;

    public PlanetImportJsonDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSun() {
        return this.sun;
    }

    public void setSun(String sun) {
        this.sun = sun;
    }

    public String getSolarSystem() {
        return this.solarSystem;
    }

    public void setSolarSystem(String solarSystem) {
        this.solarSystem = solarSystem;
    }
}
