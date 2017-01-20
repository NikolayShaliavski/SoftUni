package behavioral.chainOfResponsibility.chains.chainImlementations;

import behavioral.chainOfResponsibility.chains.DispenseChain;
import behavioral.chainOfResponsibility.currency.Currency;

/**
 * Dispenser for 50 dollar notes
 */
public class Dollar50Dispenser implements DispenseChain {

    //nextChain is Dollar20Dispenser.class
    private DispenseChain nextChain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public void dispense(Currency currency) {

        if (currency.getAmount() >= 50) {
            //dispensing n notes of 50 dollars
            int num = currency.getAmount() / 50;
            int reminder = currency.getAmount() % 50;
            System.out.println("Dispensing " + num + " 50$ note.");

            //send reminder of amount to next nextChain for dispensing
            if (reminder != 0) {
                this.nextChain.dispense(new Currency(reminder));
            }
        } else {
            //if amount is less than 50 dollars -> send it to next nextChain for dispensing
            this.nextChain.dispense(currency);
        }
    }
}
