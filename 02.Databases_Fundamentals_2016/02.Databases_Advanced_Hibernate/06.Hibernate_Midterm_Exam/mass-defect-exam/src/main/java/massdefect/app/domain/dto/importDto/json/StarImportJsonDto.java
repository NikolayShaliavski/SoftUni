package massdefect.app.domain.dto.importDto.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class StarImportJsonDto implements Serializable {

    @Expose
    private String name;
    @Expose
    private String solarSystem;

    public StarImportJsonDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSolarSystem() {
        return this.solarSystem;
    }

    public void setSolarSystem(String solarSystem) {
        this.solarSystem = solarSystem;
    }
}
