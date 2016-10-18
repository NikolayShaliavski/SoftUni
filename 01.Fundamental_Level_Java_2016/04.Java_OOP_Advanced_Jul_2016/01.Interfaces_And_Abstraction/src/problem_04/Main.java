package problem_04;

import problem_04.models.Smartphone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Nick on 11.7.2016 г..
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Smartphone smartphone = new Smartphone();

        String[] phoneNumbers = reader.readLine().split("[\\s]+");
        String[] sites = reader.readLine().split("[\\s]+");

        smartphone.addPhoneNumbers(phoneNumbers);
        smartphone.addSites(sites);

        smartphone.call();
        smartphone.browse();
    }

}
