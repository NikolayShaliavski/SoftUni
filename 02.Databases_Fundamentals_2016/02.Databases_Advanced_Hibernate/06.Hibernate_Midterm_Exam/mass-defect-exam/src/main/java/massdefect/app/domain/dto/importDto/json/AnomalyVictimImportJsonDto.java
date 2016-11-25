package massdefect.app.domain.dto.importDto.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class AnomalyVictimImportJsonDto implements Serializable {

    @Expose
    private Long id;
    @Expose
    private String person;

    public AnomalyVictimImportJsonDto() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerson() {
        return this.person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
