package problem_02;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikolay Shalyavski on 28.7.2016 г..
 */
public class PersonData {

    private List<Person> people;

    public PersonData() {
        this.people = new ArrayList<>();
    }

    public void addPerson(Person person) {

    }

    public Person removePerson() {
        throw new NotImplementedException();
    }

    public Person searchByID(long ID) {
        throw new NotImplementedException();
    }

    public Person searchByName(String name) {
        throw new NotImplementedException();
    }
}
