package problem_07.models;

public class Person implements Comparable<Person> {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode() + this.getAge() * 7;
    }

    @Override
    public boolean equals(Object person) {
        if (person == null || !(person instanceof Person)) {
            return false;
        }
        Person other = (Person) person;
        return this.getName().equals(other.getName()) &&
                this.getAge() == other.getAge();
    }

    @Override
    public int compareTo(Person other) {
        int result = this.getName().compareTo(other.getName());
        if (result == 0) {
            result = Integer.compare(this.getAge(), other.getAge());
        }
        return result;
    }
}
