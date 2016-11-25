package massdefect.app.domain.dto.exportDto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "anomalies")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnomaliesExportXmlDto {

    @XmlElement(name = "anomaly")
    private List<AnomalyExportXmlDto> anomalyExportXmlDtos;

    public AnomaliesExportXmlDto() {
        this.setAnomalyExportXmlDtos(new ArrayList<>());
    }

    public List<AnomalyExportXmlDto> getAnomalyExportXmlDtos() {
        return anomalyExportXmlDtos;
    }

    public void setAnomalyExportXmlDtos(List<AnomalyExportXmlDto> anomalyExportXmlDtos) {
        this.anomalyExportXmlDtos = anomalyExportXmlDtos;
    }

    public void addAnomalyDto(AnomalyExportXmlDto anomalyDto) {
        this.anomalyExportXmlDtos.add(anomalyDto);
    }
}
