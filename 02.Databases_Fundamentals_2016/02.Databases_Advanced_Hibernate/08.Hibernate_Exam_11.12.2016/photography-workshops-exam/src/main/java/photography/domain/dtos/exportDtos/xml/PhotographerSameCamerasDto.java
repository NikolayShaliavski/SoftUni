package photography.domain.dtos.exportDtos.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "photographer")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhotographerSameCamerasDto implements Serializable {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "primary-camera")
    private String primaryCamera;

    @XmlElementWrapper(name = "lenses")
    @XmlElement(name = "lens")
    private List<String> lenses;

    public PhotographerSameCamerasDto() {
        this.setLenses(new ArrayList<>());
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryCamera() {
        return this.primaryCamera;
    }

    public void setPrimaryCamera(String primaryCamera) {
        this.primaryCamera = primaryCamera;
    }

    public List<String> getLenses() {
        return this.lenses;
    }

    public void setLenses(List<String> lenses) {
        this.lenses = lenses;
    }
}
