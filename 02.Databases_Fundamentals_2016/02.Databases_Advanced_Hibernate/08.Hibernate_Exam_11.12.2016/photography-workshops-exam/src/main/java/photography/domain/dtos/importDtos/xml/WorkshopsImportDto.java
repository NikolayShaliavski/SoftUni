package photography.domain.dtos.importDtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "workshops")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopsImportDto implements Serializable {

    @XmlElement(name = "workshop")
    private List<WorkshopImportDto> workshopDtos;

    public List<WorkshopImportDto> getWorkshopDtos() {
        return this.workshopDtos;
    }

    public void setWorkshopDtos(List<WorkshopImportDto> workshopDtos) {
        this.workshopDtos = workshopDtos;
    }
}
