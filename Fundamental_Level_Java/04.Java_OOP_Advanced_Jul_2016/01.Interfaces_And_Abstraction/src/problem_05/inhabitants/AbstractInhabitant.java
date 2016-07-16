package problem_05.inhabitants;

import problem_05.interfaces.Inhabitant;

public abstract class AbstractInhabitant implements Inhabitant {

    private String name;
    private String birthday;

    AbstractInhabitant(String name, String birthday) {
        this.setName(name);
        this.setBirthday(birthday);
    }

    private void setBirthday(String birthday) {
        if (birthday == null || birthday.equals("")) {
            throw new IllegalArgumentException("Birthday of inhabitant cannot be empty!");
        }
        this.birthday = birthday;
    }

    private void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name of inhabitant cannot be empty!");
        }
        this.name = name;
    }

    @Override
    public String getBirthday() {
        return this.birthday;
    }

    @Override
    public void printBirthday() {
        System.out.println(this.birthday);
    }


}
