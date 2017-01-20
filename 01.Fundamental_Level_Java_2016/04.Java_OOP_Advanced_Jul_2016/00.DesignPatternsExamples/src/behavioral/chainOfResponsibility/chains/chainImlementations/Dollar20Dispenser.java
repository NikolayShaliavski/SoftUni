package behavioral.chainOfResponsibility.chains.chainImlementations;

import behavioral.chainOfResponsibility.chains.DispenseChain;
import behavioral.chainOfResponsibility.currency.Currency;

/**
 * Dispenser for 20 dollar notes
 */
public class Dollar20Dispenser implements DispenseChain {

    private DispenseChain nextChain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public void dispense(Currency currency) {

        if (currency.getAmount() >= 20) {
            //dispensing n notes of 20 dollars
            int num = currency.getAmount() / 20;
            int reminder = currency.getAmount() % 20;
            System.out.println("Dispensing " + num + " 20$ note.");

            //send reminder of amount to next nextChain for dispensing
            if (reminder != 0) {
                this.nextChain.dispense(new Currency(reminder));
            }
        } else {
            //if amount is less than 20 dollars -> send it to next nextChain for dispensing
            this.nextChain.dispense(currency);
        }
    }
}
