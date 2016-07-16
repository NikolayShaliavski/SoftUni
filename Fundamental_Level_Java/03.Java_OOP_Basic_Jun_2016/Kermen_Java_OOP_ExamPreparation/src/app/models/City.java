package app.models;

import app.IO.Writer;
import app.models.homes.Home;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 8.7.2016 г..
 */
public class City {

    private Integer population;
    private Double consumption;
    private List<Home> homes;

    public City() {
        this.population = 0;
        this.consumption = 0D;
        this.homes = new ArrayList<>();
    }

    public void addHome(Home home) {
        this.homes.add(home);
    }

    public void payBills() {
        for (int i = 0; i < this.homes.size(); i++) {
            if (!this.homes.get(i).payBills()) {
                this.homes.remove(this.homes.get(i));
                i--;
            }
        }
    }

    public void paySalaries() {
        for (Home home : this.homes) {
            home.paySalaries();
        }
    }

    public void printConsumption() {
        Writer.write("Total consumption: " + this.calculateConsumption());
    }

    public void printPopulation() {
        Writer.write("Total population: " + this.calculatePopulation());
    }

    private Double calculateConsumption() {
        return this.homes.stream().
                mapToDouble(home -> home.getConsumption()).
                sum();
    }

    private Integer calculatePopulation() {
        return this.homes.stream().
                mapToInt(home -> home.getPeopleCount()).sum();
    }
}
