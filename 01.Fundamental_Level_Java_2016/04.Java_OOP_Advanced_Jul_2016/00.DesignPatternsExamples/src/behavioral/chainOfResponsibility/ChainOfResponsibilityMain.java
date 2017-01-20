package behavioral.chainOfResponsibility;

import behavioral.chainOfResponsibility.chains.ATMDispenseChain;
import behavioral.chainOfResponsibility.currency.Currency;

import java.util.Scanner;

/**
 * Chain of responsibility pattern is used to achieve loose coupling in software design
 * where a request from client is passed to a chain of objects to process them.
 * Then the object in the chain will decide themselves who will be processing the request
 * and whether the request is required to be sent to the next object in the chain or not.
 * An example of Chain of Responsibility pattern is ATM Dispense machine.
 * The user enters the amount to be dispensed and the machine dispense amount
 * in terms of defined currency bills such as 50$, 20$, 10$ etc.
 * If the user enters an amount that is not multiples of 10, program terminates (or can throws error).
 */
public class ChainOfResponsibilityMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATMDispenseChain atmDispenser = new ATMDispenseChain();

        System.out.println("Enter amount multiple of 10 to dispense:");
        String input = scanner.nextLine();
        int amount;
        while (!input.equalsIgnoreCase("end")) {

            amount = Integer.parseInt(input);
            if (amount % 10 != 0) {
                System.out.println("Amount should be in multiple of 10.");
                return;
            }
            // process the request
            atmDispenser.chain1.dispense(new Currency(amount));

            System.out.println("Enter amount multiple of 10 to dispense:");
            input = scanner.nextLine();
        }
    }
}
