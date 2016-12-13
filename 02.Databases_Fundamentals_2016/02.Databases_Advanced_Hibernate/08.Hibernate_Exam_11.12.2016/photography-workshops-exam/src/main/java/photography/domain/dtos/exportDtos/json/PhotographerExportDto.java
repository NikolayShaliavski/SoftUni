package photography.domain.dtos.exportDtos.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PhotographerExportDto implements Serializable {

    @Expose
    @SerializedName(value = "FirstName")
    private String firstName;

    @Expose
    @SerializedName(value = "LastName")
    private String lastName;

    @Expose
    @SerializedName(value = "Phone")
    private String phone;

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
}
