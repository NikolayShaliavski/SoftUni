package problem_08.soldiers;

import problem_08.interfaces.Soldier;

public abstract class SoldierImpl implements Soldier {

    private String firstName;
    private String lastName;
    private String id;

    SoldierImpl(String firstName, String lastName, String id) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setId(id);
    }


    @Override
    public String getFirstName() {
        return this.firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getId() {
        return this.id;
    }

    private void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(
                "Name: %s %s Id: %s",
                    this.firstName,
                        this.lastName,
                            this.id);
    }
}
