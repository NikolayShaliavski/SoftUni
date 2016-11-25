package massdefect.app.domain.dto.importDto.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "anomaly")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnomalyWithVictimImportXmlDto implements Serializable {

    @XmlAttribute(name = "origin-planet", required = true)
    private String originPlanet;

    @XmlAttribute(name = "teleport-planet", required = true)
    private String teleportPlanet;

    @XmlElementWrapper(name = "victims")
    @XmlElement(name = "victim")
    private List<VictimXmlDto> victims;

    public AnomalyWithVictimImportXmlDto() {
        this.setVictims(new ArrayList<>());
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

    public List<VictimXmlDto> getVictims() {
        return victims;
    }

    public void setVictims(List<VictimXmlDto> victims) {
        this.victims = victims;
    }
}
