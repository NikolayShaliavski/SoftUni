package massdefect.app.domain.dto.importDto.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class SolarSystemImportJsonDto implements Serializable {

    @Expose
    private String name;

    public SolarSystemImportJsonDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
