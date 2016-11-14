package app.domain.labels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "labels")
public class ClassicLabel implements Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String subTitle;

    public ClassicLabel() {
    }

    public ClassicLabel(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getSubTitle() {
        return this.subTitle;
    }

    @Override
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
