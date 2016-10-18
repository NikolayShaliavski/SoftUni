package problem_05;

public class Person implements Comparable<Person> {

    private String name;
    private int age;
    private String town;

    public Person(String nameArgs,
                  int ageArgs,
                  String townArgs) {
        this.setName(nameArgs);
        this.setAge(ageArgs);
        this.setTown(townArgs);
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

    public String getTown() {
        return this.town;
    }

    private void setTown(String town) {
        this.town = town;
    }

    @Override
    public int compareTo(Person anotherPerson) {
        int result = this.getName().compareTo(anotherPerson.getName());
        if (result == 0) {
            result = Integer.compare(this.getAge(), anotherPerson.getAge());
        }
        if (result == 0) {
            result = this.getTown().compareTo(anotherPerson.getTown());
        }
        return result;
    }
}
