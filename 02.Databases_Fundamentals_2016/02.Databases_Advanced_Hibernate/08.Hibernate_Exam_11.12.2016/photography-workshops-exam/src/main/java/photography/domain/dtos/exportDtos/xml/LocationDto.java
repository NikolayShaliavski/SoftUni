package photography.domain.dtos.exportDtos.xml;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "location")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocationDto {

    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "workshop")
    private List<WorkshopDto> workshops;

    public LocationDto() {
        this.setWorkshops(new ArrayList<>());
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WorkshopDto> getWorkshops() {
        return this.workshops;
    }

    public void setWorkshops(List<WorkshopDto> workshops) {
        this.workshops = workshops;
    }
}
