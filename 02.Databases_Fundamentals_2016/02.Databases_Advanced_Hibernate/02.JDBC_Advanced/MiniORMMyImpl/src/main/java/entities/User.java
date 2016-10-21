package entities;

import persistence.Column;
import persistence.Entity;
import persistence.Id;

import java.util.Date;

@Entity(name = "users")
public class User {

    @Id
    private int id;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private int age;

    @Column(name = "registration_date")
    private Date registrationDate;

    public User(int id, String password, int age, Date registrationDate) {
        this.setid(id);
        this.setPassword(password);
        this.setAge(age);
        this.setRegistrationDate(registrationDate);
    }
    private void setid(int id) {
        this.id = id;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setAge(int age) {
        this.age = age;
    }

    private void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return String.format("Users Id: %d, Password: %s, Users age: %d, Registration date: %s",
                this.id, this.password, this.age, this.registrationDate);
    }
}





