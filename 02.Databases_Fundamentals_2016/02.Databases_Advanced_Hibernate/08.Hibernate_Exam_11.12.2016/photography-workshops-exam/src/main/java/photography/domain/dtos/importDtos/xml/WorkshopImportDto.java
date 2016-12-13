package photography.domain.dtos.importDtos.xml;

import photography.domain.dtos.importDtos.json.PhotographerImportDto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "workshop")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopImportDto implements Serializable {

    @XmlAttribute(name = "name")
    @NotNull
    private String name;

    @XmlAttribute(name = "start-date")
    private Date startDate;

    @XmlAttribute(name = "end-date")
    private Date endDate;

    @XmlAttribute(name = "location")
    @NotNull
    private String location;

    @XmlAttribute(name = "price")
    @NotNull
    private BigDecimal pricePerParticipant;

    @XmlElement(name = "trainer")
    @NotNull
    private String trainer;

    @XmlElementWrapper(name = "participants")
    @XmlElement(name = "participant")
    private List<PhotographerImportDto> participants;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getPricePerParticipant() {
        return this.pricePerParticipant;
    }

    public void setPricePerParticipant(BigDecimal pricePerParticipant) {
        this.pricePerParticipant = pricePerParticipant;
    }

    public String getTrainer() {
        return this.trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public List<PhotographerImportDto> getParticipants() {
        return this.participants;
    }

    public void setParticipants(List<PhotographerImportDto> participants) {
        this.participants = participants;
    }
}
