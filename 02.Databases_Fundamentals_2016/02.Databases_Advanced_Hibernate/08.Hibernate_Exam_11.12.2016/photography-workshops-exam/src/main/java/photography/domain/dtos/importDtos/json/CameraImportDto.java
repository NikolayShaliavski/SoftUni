package photography.domain.dtos.importDtos.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CameraImportDto implements Serializable {

    @Expose
    @NotNull
    private String type;

    @Expose
    @NotNull
    private String make;

    @Expose
    @NotNull
    private String model;

    @Expose
    @SerializedName(value = "isFullFrame")
    private Boolean isFullNameOrNot;

    @Expose
    @NotNull
    @Min(100)
    private Integer minISO;

    @Expose
    private Integer maxISO;

    @Expose
    @SerializedName(value = "MaxShutterSpeed")
    private Integer maxShutterSpeed;

    @Expose
    private String maxVideoResolution;

    @Expose
    private Integer maxFrameRate;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getFullNameOrNot() {
        return this.isFullNameOrNot;
    }

    public void setFullNameOrNot(Boolean fullNameOrNot) {
        this.isFullNameOrNot = fullNameOrNot;
    }

    public Integer getMinISO() {
        return this.minISO;
    }

    public void setMinISO(Integer minISO) {
        this.minISO = minISO;
    }

    public Integer getMaxISO() {
        return this.maxISO;
    }

    public void setMaxISO(Integer maxISO) {
        this.maxISO = maxISO;
    }

    public Integer getMaxShutterSpeed() {
        return this.maxShutterSpeed;
    }

    public void setMaxShutterSpeed(Integer maxShutterSpeed) {
        this.maxShutterSpeed = maxShutterSpeed;
    }

    public String getMaxVideoResolution() {
        return this.maxVideoResolution;
    }

    public void setMaxVideoResolution(String maxVideoResolution) {
        this.maxVideoResolution = maxVideoResolution;
    }

    public Integer getMaxFrameRate() {
        return this.maxFrameRate;
    }

    public void setMaxFrameRate(Integer maxFrameRate) {
        this.maxFrameRate = maxFrameRate;
    }
}
