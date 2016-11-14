package app.domain.labels;

import java.io.Serializable;

public interface Label extends Serializable {

    Long getId();

    void setId(Long id);

    String getTitle();

    void setTitle(String title);

    String getSubTitle();

    void setSubTitle(String subTitle);
}
