package problem_06.animals;

import problem_06.contracts.Soundable;

public abstract class Animal implements Soundable{

    private String name;
    private Integer age;
    private String male;

    protected Animal(String name, String age, String male) {
        this.setName(name);
        this.setAge(age);
        this.setMale(male);
    }

    private void setName(String name) {
        if (name == null || name.length() == 0 || name.equalsIgnoreCase("name")) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.name = name;
    }

    private void setAge(String age) {
        Integer ageParsed = 0;
        try {
            ageParsed = Integer.valueOf(age);
            if (ageParsed <= 0) {
                throw new IllegalArgumentException("Invalid input!");
            }
        } catch (NumberFormatException nfex) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.age = ageParsed;
    }

    private void setMale(String male) {
        if (male == null || male.length() == 0 || (!male.equals("Male") && !male.equals("Female"))) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.male = male;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass().getSimpleName()).append(System.lineSeparator())
                .append(this.name).append(" ")
                .append(this.age).append(" ")
                .append(this.male).append(" ");

        return builder.toString();
    }
}
