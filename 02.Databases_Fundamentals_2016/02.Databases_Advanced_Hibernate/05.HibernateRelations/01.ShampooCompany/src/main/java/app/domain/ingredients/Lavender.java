package app.domain.ingredients;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorColumn(name = "LV")
public class Lavender extends BasicIngredient {

    private static final String NAME = "Lavender";

    private static final BigDecimal PRICE = new BigDecimal("2");

    public Lavender() {
        super(NAME, PRICE);
    }
}
