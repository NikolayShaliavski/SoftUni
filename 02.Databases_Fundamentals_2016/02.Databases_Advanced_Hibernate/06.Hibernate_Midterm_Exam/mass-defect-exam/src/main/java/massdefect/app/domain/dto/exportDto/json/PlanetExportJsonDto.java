package massdefect.app.domain.dto.exportDto.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class PlanetExportJsonDto implements Serializable {

    @Expose
    private String name;

    public PlanetExportJsonDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
