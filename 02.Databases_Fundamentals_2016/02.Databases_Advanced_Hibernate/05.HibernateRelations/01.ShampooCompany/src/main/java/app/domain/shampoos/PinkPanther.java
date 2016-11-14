package app.domain.shampoos;

import app.domain.batches.ProductionBatch;
import app.domain.labels.ClassicLabel;
import app.domain.enums.Size;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorColumn(name = "PP")
public class PinkPanther extends BasicShampoo {

    private static final String BRAND = "Pink Panther";

    private static final BigDecimal PRICE = new BigDecimal("8.50");

    private static final Size SIZE = Size.MEDIUM;

    public PinkPanther(ClassicLabel label, ProductionBatch batch) {
        super(BRAND, PRICE, SIZE, label, batch);
    }
}
