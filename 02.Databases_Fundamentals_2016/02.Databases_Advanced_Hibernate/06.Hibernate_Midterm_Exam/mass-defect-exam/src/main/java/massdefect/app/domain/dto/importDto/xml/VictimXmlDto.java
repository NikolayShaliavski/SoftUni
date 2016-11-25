package massdefect.app.domain.dto.importDto.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "victim")
@XmlAccessorType(XmlAccessType.FIELD)
public class VictimXmlDto implements Serializable {

    @XmlAttribute(name = "name")
    private String name;

    public VictimXmlDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
