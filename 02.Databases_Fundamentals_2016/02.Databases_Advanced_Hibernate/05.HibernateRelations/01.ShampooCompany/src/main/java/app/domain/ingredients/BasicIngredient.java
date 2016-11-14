package app.domain.ingredients;

import app.domain.shampoos.BasicShampoo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity(name = "ingredients")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ingredient_type", discriminatorType = DiscriminatorType.STRING)
public abstract class BasicIngredient implements Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    @ManyToMany(mappedBy = "ingredients", targetEntity = BasicShampoo.class)
    private List<BasicShampoo> shampoos;

    protected BasicIngredient() {
        this.setShampoos(new ArrayList<>());
    }

    protected BasicIngredient(String name, BigDecimal price) {
        this();
        this.setName(name);
        this.setPrice(price);
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
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public List<BasicShampoo> getShampoos() {
        return Collections.unmodifiableList(this.shampoos);
    }

    @Override
    public void setShampoos(List<BasicShampoo> shampoos) {
        this.shampoos = shampoos;
    }

    @Override
    public void addShampoo(BasicShampoo shampoo) {
        this.shampoos.add(shampoo);
    }
}
