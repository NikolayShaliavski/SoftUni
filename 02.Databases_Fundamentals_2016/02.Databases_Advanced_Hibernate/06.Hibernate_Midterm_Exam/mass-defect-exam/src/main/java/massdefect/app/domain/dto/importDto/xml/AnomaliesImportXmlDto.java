package massdefect.app.domain.dto.importDto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "anomalies")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnomaliesImportXmlDto implements Serializable {

    @XmlElement(name = "anomaly")
    private List<AnomalyWithVictimImportXmlDto> anomalies;

    public AnomaliesImportXmlDto() {
        this.setAnomalies(new ArrayList<>());
    }

    public List<AnomalyWithVictimImportXmlDto> getAnomalies() {
        return this.anomalies;
    }

    public void setAnomalies(List<AnomalyWithVictimImportXmlDto> anomalies) {
        this.anomalies = anomalies;
    }
}
