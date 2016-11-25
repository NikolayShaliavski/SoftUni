package massdefect.app.domain.dto.exportDto.json;

import com.google.gson.annotations.Expose;

public class AnomalyExportJsonDto {

    @Expose
    private Long id;

    @Expose
    private PlanetExportJsonDto originPlanet;

    @Expose
    private PlanetExportJsonDto teleportPlanet;

    @Expose
    private Integer victimsCount;

    public AnomalyExportJsonDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlanetExportJsonDto getOriginPlanet() {
        return originPlanet;
    }

    public void setOriginPlanet(PlanetExportJsonDto originPlanet) {
        this.originPlanet = originPlanet;
    }

    public PlanetExportJsonDto getTeleportPlanet() {
        return teleportPlanet;
    }

    public void setTeleportPlanet(PlanetExportJsonDto teleportPlanet) {
        this.teleportPlanet = teleportPlanet;
    }

    public Integer getVictimsCount() {
        return victimsCount;
    }

    public void setVictimsCount(Integer victimsCount) {
        this.victimsCount = victimsCount;
    }
}
