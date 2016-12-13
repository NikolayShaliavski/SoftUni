package photography.domain.dtos.exportDtos.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "workshop")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopDto {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "total-profit")
    private Double totalProfit;

    @XmlElement(name = "participants")
    private ParticipantDto participants;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalProfit() {
        return this.totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public ParticipantDto getParticipants() {
        return this.participants;
    }

    public void setParticipants(ParticipantDto participants) {
        this.participants = participants;
    }
}
