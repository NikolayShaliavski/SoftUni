package app.models.homes;

import app.models.devices.Device;
import app.models.people.Person;

/**
 * Created by Nick on 7.7.2016 г..
 */
public class AloneOldHome extends Home {

    private static final Integer ROOMS_COUNT = 1;
    private static final Integer CONSUMPTION_PER_ROOM = 15;

    AloneOldHome(Person[] people) {
        super(people,new Device[0], ROOMS_COUNT, CONSUMPTION_PER_ROOM);
    }
}
