package photography.domain.dtos.exportDtos.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LandscapePhotographerExportDto implements Serializable {

    @Expose
    @SerializedName(value = "FirstName")
    private String firstName;

    @Expose
    @SerializedName(value = "LastName")
    private String lastName;

    @Expose
    @SerializedName(value = "CameraMake")
    private String primaryCameraMake;

    @Expose
    @SerializedName(value = "LensesCount")
    private Integer lenses;

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

    public String getPrimaryCameraMake() {
        return this.primaryCameraMake;
    }

    public void setPrimaryCameraMake(String primaryCameraMake) {
        this.primaryCameraMake = primaryCameraMake;
    }

    public Integer getLenses() {
        return this.lenses;
    }

    public void setLenses(Integer lenses) {
        this.lenses = lenses;
    }
}
