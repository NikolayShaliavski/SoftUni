package photography.domain.dtos.importDtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "accessories")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccesoriesImportDto implements Serializable {

    @XmlElement(name = "accessory")
    private List<AccessoryImportDto> accessories;

    public List<AccessoryImportDto> getAccessories() {
        return this.accessories;
    }

    public void setAccessories(List<AccessoryImportDto> accessories) {
        this.accessories = accessories;
    }
}
