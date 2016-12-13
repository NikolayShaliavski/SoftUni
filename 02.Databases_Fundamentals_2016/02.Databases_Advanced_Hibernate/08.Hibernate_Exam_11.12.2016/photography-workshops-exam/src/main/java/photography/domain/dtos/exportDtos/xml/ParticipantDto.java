package photography.domain.dtos.exportDtos.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "participants")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParticipantDto {

    @XmlAttribute(name = "count")
    private Integer count;

    @XmlElement(name = "participant")
    private List<String> participants;

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<String> getParticipants() {
        return this.participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }
}
