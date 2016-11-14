package app.domain.ingredients;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorColumn(name = "AC")
public class AmmoniumChloride extends BasicChemicalIngredient {

    private static final String NAME = "AmmoniumChloride";

    private static final BigDecimal PRICE = new BigDecimal("0.59");

    private static final String CHEMICAL_FORMULA = "NH4Cl";

    public AmmoniumChloride() {
        super(NAME, PRICE, CHEMICAL_FORMULA);
    }
}
