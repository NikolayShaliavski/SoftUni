package photography.domain.dtos.exportDtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "photographers")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhotographersSameCamerasDto {

    @XmlElement(name = "photographer")
    private List<PhotographerSameCamerasDto> photographers;

    public PhotographersSameCamerasDto() {
        this.setPhotographers(new ArrayList<>());
    }

    public List<PhotographerSameCamerasDto> getPhotographers() {
        return this.photographers;
    }

    public void setPhotographers(List<PhotographerSameCamerasDto> photographers) {
        this.photographers = photographers;
    }
}
