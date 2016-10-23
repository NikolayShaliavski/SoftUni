package entities;

import persistence.Column;
import persistence.Entity;
import persistence.Id;

import java.util.Date;

@Entity(name = "users")
public class User {

    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "registration_date")
    private Date registrationDate;

    @SuppressWarnings("unused")
    public User() {
        super();
    }

    public User(String name, int age, Date registrationDate) {
        this.setName(name);
        this.setAge(age);
        this.setRegistrationDate(registrationDate);
    }
    public void setid(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return String.format("Users Id: %d, Password: %s, Users age: %d, Registration date: %s",
                this.id, this.name, this.age, this.registrationDate);
    }
}





