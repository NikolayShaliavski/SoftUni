package behavioral.chainOfResponsibility.currency;

/**
 * Stores the amount to dispense and used by the chain implementations.
 */
public class Currency {

    private int amount;

    public Currency(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }
}
