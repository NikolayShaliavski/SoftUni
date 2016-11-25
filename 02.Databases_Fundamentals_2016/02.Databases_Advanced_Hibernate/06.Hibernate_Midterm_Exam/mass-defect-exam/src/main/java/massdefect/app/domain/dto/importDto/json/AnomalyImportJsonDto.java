package massdefect.app.domain.dto.importDto.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class AnomalyImportJsonDto implements Serializable {

    @Expose
    private String originPlanet;
    @Expose
    private String teleportPlanet;

    public AnomalyImportJsonDto() {
    }

    public String getOriginPlanet() {
        return this.originPlanet;
    }

    public void setOriginPlanet(String originPlanet) {
        this.originPlanet = originPlanet;
    }

    public String getTeleportPlanet() {
        return this.teleportPlanet;
    }

    public void setTeleportPlanet(String teleportPlanet) {
        this.teleportPlanet = teleportPlanet;
    }
}
