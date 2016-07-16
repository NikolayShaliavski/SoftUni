package problem_04.models;

import problem_04.exceptions.InvalidPhoneException;
import problem_04.exceptions.InvalidURLException;
import problem_04.interfaces.Browseable;
import problem_04.interfaces.Callable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Nick on 11.7.2016 г..
 */
public class Smartphone implements Callable, Browseable {

    private List<String> phoneNumbers;
    private List<String> sites;

    public Smartphone() {
        this.phoneNumbers = new ArrayList<>();
        this.sites = new ArrayList<>();
    }

    @Override
    public void browse() {
        for (String site : this.sites) {
            try {
                if (site.matches("[^0-9]+")) {
                    System.out.printf("Browsing: %s!%n", site);

                } else {
                    throw new InvalidURLException();
                }
                }catch (InvalidURLException iuex) {
                System.out.println(iuex.getMessage());
            }
        }
    }

    @Override
    public void call() {
        for (String phoneNumber : this.phoneNumbers) {
            try {
                if (phoneNumber.matches("[0-9]+")) {
                    System.out.printf("Calling... %s%n", phoneNumber);
                } else {
                    throw new InvalidPhoneException();
                }

            } catch (InvalidPhoneException iphex) {
                System.out.println(iphex.getMessage());
            }
        }
    }

    public void addPhoneNumbers(String[] numbers) {
        this.phoneNumbers = Arrays.asList(numbers);
    }

    public void addSites(String[] sites) {
        this.sites = Arrays.asList(sites);
    }
}
