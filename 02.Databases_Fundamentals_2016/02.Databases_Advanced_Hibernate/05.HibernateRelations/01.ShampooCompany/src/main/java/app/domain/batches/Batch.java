package app.domain.batches;


import app.domain.shampoos.BasicShampoo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public interface Batch extends Serializable {

    Long getId();

    void setId(Long id);

    Date getBatchDate();

    void setBatchDate(Date batchDate);

    Set<BasicShampoo> getShampoos();

    void setShampoos(Set<BasicShampoo> shampoos);

    void addShampoo(BasicShampoo shampoo);
}
