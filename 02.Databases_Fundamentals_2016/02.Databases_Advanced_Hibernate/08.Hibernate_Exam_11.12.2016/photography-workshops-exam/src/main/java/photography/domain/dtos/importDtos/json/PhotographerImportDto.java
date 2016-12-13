package photography.domain.dtos.importDtos.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "participant")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhotographerImportDto implements Serializable {

    @XmlAttribute(name = "first-name")
    @Expose
    @NotNull
    private String firstName;

    @XmlAttribute(name = "last-name")
    @Expose
    @NotNull
    @Size(min = 2, max = 50)
    private String lastName;

    @Expose
    private String phone;

    @Expose
    private Long[] lenses;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long[] getLenses() {
        return this.lenses;
    }

    public void setLenses(Long[] lenses) {
        this.lenses = lenses;
    }
}
