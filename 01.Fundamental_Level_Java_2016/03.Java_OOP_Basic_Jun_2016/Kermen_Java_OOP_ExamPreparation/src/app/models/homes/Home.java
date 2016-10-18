package app.models.homes;

import app.models.devices.Device;
import app.models.people.Child;
import app.models.people.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Nick on 7.7.2016 г..
 */
public abstract class Home {

    List<Person> people;
    List<Device> devices;
    List<Child> children;

    Integer peopleCount;
    Double budget;
    Double consumption;

    Home(Person[] people, Device[] devices, Integer roomsCount, Integer roomConsumption) {
        this.setPeople(people);
        this.setDevices(devices);
        this.setConsumption(roomsCount, roomConsumption);
        this.children = new ArrayList<>();
        this.setPeopleCount();
        this.budget = 0D;
    }

    public Double getConsumption() {
        return this.consumption;
    }

    public Integer getPeopleCount() {
        return this.peopleCount;
    }

    private void setPeople(Person[] people) {
        this.people = new ArrayList<>();
        this.people.addAll(Arrays.asList(people));
    }

    private void setDevices(Device[] devices) {
        this.devices = new ArrayList<>();
        if (devices.length == 0) {
            return;
        }
        this.devices = Arrays.asList(devices);
    }

    private void setConsumption(Integer roomsCount, Integer roomConsumption) {
        this.consumption = this.devices.stream().
                mapToDouble(device -> device.getConsumption()).
                sum() + (roomsCount * roomConsumption);
    }

    protected void setPeopleCount() {
        this.peopleCount = this.people.size();
    }

    public void paySalaries() {
        this.budget += this.people.stream().
                mapToDouble(person -> person.getIncome()).sum();
    }

    public boolean payBills() {
        return (this.budget -= this.consumption) > 0;
    }
}
