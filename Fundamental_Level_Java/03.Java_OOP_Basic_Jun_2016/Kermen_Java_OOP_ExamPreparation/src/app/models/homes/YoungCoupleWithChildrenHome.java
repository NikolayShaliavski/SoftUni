package app.models.homes;

import app.models.devices.Device;
import app.models.people.Child;
import app.models.people.Person;

import java.util.Arrays;

/**
 * Created by Nick on 7.7.2016 г..
 */
public class YoungCoupleWithChildrenHome extends Home {

    private static final Integer ROOMS_COUNT = 2;
    private static final Integer CONSUMPTION_PER_ROOM = 30;


    YoungCoupleWithChildrenHome(Person[] people, Device[] devices, Child[] children) {
        super(people, devices, ROOMS_COUNT, CONSUMPTION_PER_ROOM);
        this.setChildren(children);
        this.setPeopleCount();
        this.addChildExpenses();
    }


    private void addChildExpenses() {
        this.consumption += this.children.stream().
                mapToDouble(child -> child.getExpense()).sum();
    }

    @Override
    protected void setPeopleCount() {
        this.peopleCount = this.people.size() + this.children.size();
    }

    private void setChildren(Child[] children) {
        this.children = Arrays.asList(children);
    }
}
