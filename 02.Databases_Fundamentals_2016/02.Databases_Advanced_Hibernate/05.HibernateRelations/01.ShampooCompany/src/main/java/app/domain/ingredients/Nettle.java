package app.domain.ingredients;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorColumn(name = "NT")
public class Nettle extends BasicIngredient {

    private static final String NAME = "Nettle";

    private static final BigDecimal PRICE = new BigDecimal("6.12");

    public Nettle() {
        super(NAME, PRICE);
    }
}
