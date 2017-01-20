package behavioral.chainOfResponsibility.chains;

import behavioral.chainOfResponsibility.currency.Currency;

/**
 * Base interface have a method to define the next processor in the chain
 * and the method that will process the request.
 */
public interface DispenseChain {

    void setNextChain(DispenseChain nextChain);

    /**
     * Every implementation is trying to process the request and based on the amount,
     * it might process some or full part of it.
     * If one of the chain isn't able to process it fully,
     * it sends the request to the next processor in chain to process the remaining request.
     * If the processor is not able to process anything,
     * it just forwards the same request to the next chain.
     *
     * @param currency amount to be dispensed
     */
    void dispense(Currency currency);
}
