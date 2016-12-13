package photography.domain.dtos.exportDtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "locations")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocationsExportDto {

    @XmlElement(name = "location")
    private List<LocationDto> locations;

    public LocationsExportDto() {
        this.setLocations(new ArrayList<>());
    }

    public List<LocationDto> getLocations() {
        return this.locations;
    }

    public void setLocations(List<LocationDto> locations) {
        this.locations = locations;
    }
}
