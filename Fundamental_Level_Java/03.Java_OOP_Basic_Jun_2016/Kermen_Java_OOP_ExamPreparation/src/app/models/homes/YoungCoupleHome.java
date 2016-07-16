package app.models.homes;

import app.models.devices.Device;
import app.models.people.Person;

/**
 * Created by Nick on 7.7.2016 г..
 */
public class YoungCoupleHome extends Home {

    private static final Integer ROOMS_COUNT = 2;
    private static final Integer CONSUMPTION_PER_ROOM = 20;

    YoungCoupleHome(Person[] people, Device[] devices) {
        super(people, devices, ROOMS_COUNT, CONSUMPTION_PER_ROOM);
    }
}
