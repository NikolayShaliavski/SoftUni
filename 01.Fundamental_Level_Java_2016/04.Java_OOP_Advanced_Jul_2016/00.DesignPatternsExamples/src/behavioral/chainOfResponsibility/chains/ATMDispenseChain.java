package behavioral.chainOfResponsibility.chains;

import behavioral.chainOfResponsibility.chains.chainImlementations.Dollar10Dispenser;
import behavioral.chainOfResponsibility.chains.chainImlementations.Dollar20Dispenser;
import behavioral.chainOfResponsibility.chains.chainImlementations.Dollar50Dispenser;

public class ATMDispenseChain {

    //first chain is dispenser for highest amount - 50 dollars
    public DispenseChain chain1;

    public ATMDispenseChain() {

        //initialize chains
        this.chain1 = new Dollar50Dispenser();
        DispenseChain chain2 = new Dollar20Dispenser();
        DispenseChain chain3 = new Dollar10Dispenser();

        //set the chain of responsibility
        this.chain1.setNextChain(chain2);
        chain2.setNextChain(chain3);
    }
}
