package massdefect.app.domain.dto.exportDto.xml;

import massdefect.app.domain.dto.importDto.xml.VictimXmlDto;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "anomaly")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnomalyExportXmlDto {

    @XmlAttribute(name = "id")
    private Long id;

    @XmlAttribute(name = "origin-planet")
    private String originPlanet;

    @XmlAttribute(name = "teleport-planet")
    private String teleportPlanet;

    @XmlElementWrapper(name = "victims")
    @XmlElement(name = "victim")
    private List<VictimXmlDto> victims;

    public AnomalyExportXmlDto() {
        this.setVictims(new ArrayList<>());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginPlanet() {
        return originPlanet;
    }

    public void setOriginPlanet(String originPlanet) {
        this.originPlanet = originPlanet;
    }

    public String getTeleportPlanet() {
        return teleportPlanet;
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

    public void addVictim(VictimXmlDto victim) {
        this.victims.add(victim);
    }
}
