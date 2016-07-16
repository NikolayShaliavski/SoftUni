package problem_01.models;

import problem_01.interfaces.Birthable;
import problem_01.interfaces.Identifiable;
import problem_01.interfaces.Person;

/**
 * Created by Nick on 11.7.2016 г..
 */
public class Citizen implements Person, Birthable, Identifiable {
    
    private String name;
    private int age;
    private String id;
    private String birthDate;
    
    public Citizen(String name,
                   int age,
                   String id,
                   String birthDate) {
        this.setName(name);
        this.setAge(age);
        this.setId(id);
        this.setBirthDate(birthDate);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public String getBirthDate() {
        return this.birthDate;
    }

    @Override
    public String getId() {
        return this.id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setAge(int age) {
        this.age = age;
    }

    private void setId(String id) {
        this.id = id;
    }

    private void setBirthDate(String BirthDate) {
        this.birthDate = BirthDate;
    }
}
