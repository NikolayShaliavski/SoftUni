package app.core;

import app.IO.Reader;
import app.IO.Writer;
import app.models.City;
import app.models.homes.Home;
import app.models.homes.HomeFactory;

import java.io.IOException;

/**
 * Created by Nick on 7.7.2016 г..
 */
public class Engine implements Runnable {

    @Override
    public void run() {

        City kermen = new City();
        HomeFactory factory = new HomeFactory();

        try {
            String line = Reader.read();
            int count = 0;

            while (!line.equals("Democracy")) {
                count++;
                if (line.equals("EVN")) {
                    if (count % 3 == 0) {
                        kermen.paySalaries();
                    }
                    kermen.printConsumption();
                    line = Reader.read();
                    continue;
                } else if (line.equals("EVN bill")) {
                    if (count % 3 == 0) {
                        kermen.paySalaries();
                    }
                    kermen.payBills();
                    line = Reader.read();
                    continue;
                }
                String homeType = line.substring(0, line.indexOf("("));

                Home home = factory.createNewHome(homeType, line);
                if (home != null) {
                    kermen.addHome(home);
                }
                if (count % 3 == 0) {
                    kermen.paySalaries();
                }
                line = Reader.read();
            }
        } catch (IOException ioex) {
            Writer.write(ioex.getMessage());
        }
        kermen.printPopulation();
    }
}
