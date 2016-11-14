package app.domain.batches;


import app.domain.shampoos.BasicShampoo;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "batches")
public class ProductionBatch implements Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date batchDate;

    @OneToMany(mappedBy = "batch", targetEntity = BasicShampoo.class)
    private Set<BasicShampoo> shampoos;

    public ProductionBatch() {
        this.setShampoos(new HashSet<>());
    }

    public ProductionBatch(Date batchDate) {
        this();
        this.setBatchDate(batchDate);
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
    public Date getBatchDate() {
        return this.batchDate;
    }

    @Override
    public void setBatchDate(Date batchDate) {
        this.batchDate = batchDate;
    }

    @Override
    public Set<BasicShampoo> getShampoos() {
        return Collections.unmodifiableSet(this.shampoos);
    }

    @Override
    public void setShampoos(Set<BasicShampoo> shampoos) {
        this.shampoos = shampoos;
    }

    @Override
    public void addShampoo(BasicShampoo shampoo) {
        this.shampoos.add(shampoo);
    }
}
