package defining_classes.problem_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P10_FamilyTree {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String personToPrint = reader.readLine();
        List<Person> tree = new LinkedList<>();

        String line = reader.readLine();
        while (!line.equals("End")) {
            if (!line.contains("-")) {
                String[] tokens = line.split("[\\s]+");
                String name = tokens[0] + " " + tokens[1];
                String birthday = tokens[2];
                if (personDontExist(name, birthday, tree)) {
                    Person person = new Person(name, birthday);
                    tree.add(person);
                } else {
                    for (Person person : tree) {
                        if (person.getName() == null && person.getBirthday().equals(birthday)) {
                            person.setName(name);
                        } else if (person.getBirthday() == null && person.getName().equals(name)) {
                            person.setBirthday(birthday);
                        }
                    }
                }
            } else {
                String[] tokens = line.split(" - ");
                String parentInfo = tokens[0];
                String childInfo = tokens[1];
                Person personParent = personDontExist(parentInfo, tree);
                Person personChild = personDontExist(childInfo, tree);
                if (personParent == null) {
                    if (parentInfo.contains("/")) {
                        personParent = new Person(null, parentInfo);
                    } else {
                        personParent = new Person(parentInfo, null);
                    }
                    tree.add(personParent);
                } else {
                    if (personParent.getName() == null && !parentInfo.contains("/")) {
                        personParent.setName(parentInfo);
                    } else if (personParent.getBirthday() == null && parentInfo.contains("/")) {
                        personParent.setBirthday(parentInfo);
                    }
                }
                if (personChild == null) {
                    if (childInfo.contains("/")) {
                        personChild = new Person(null, childInfo);
                    } else {
                        personChild = new Person(childInfo, null);
                    }
                    tree.add(personChild);
                } else {
                    if (personChild.getName() == null && !childInfo.contains("/")) {
                        personChild.setName(childInfo);
                    } else if (personChild.getBirthday() == null && childInfo.contains("/")) {
                        personChild.setBirthday(childInfo);
                    }
                }
                personParent.getChildren().add(personChild);
                personChild.getParents().add(personParent);
            }
            line = reader.readLine();
        }

        for (int i = 0; i < tree.size() - 1; i++) {
            for (int j = i + 1; j < tree.size(); j++) {
                if (tree.get(i).getName().equals(tree.get(j).getName()) &&
                        tree.get(i).getBirthday().equals(tree.get(j).getBirthday())) {
                    tree.get(i).getParents().addAll(tree.get(j).getParents());
                    tree.get(i).getChildren().addAll(tree.get(j).getChildren());
                    tree.get(j).setName("No name");
                    tree.get(j).setBirthday("No birthday");
                }
            }
        }
        for (Person person : tree) {
            if (person.getName() != null && !person.getName().equals("No name") &&
                    person.getBirthday() != null && !person.getBirthday().equals("No birthday")) {
                if (person.getName().equals(personToPrint) || person.getBirthday().equals(personToPrint)) {
                    System.out.printf("%s %s%n",
                            person.getName(),
                            person.getBirthday());
                    System.out.println("Parents:");
                    for (Person parent : person.getParents()) {
                        System.out.printf("%s %s%n",
                                parent.getName(),
                                parent.getBirthday());
                    }
                    System.out.println("Children:");
                    for (Person child : person.getChildren()) {
                        System.out.printf("%s %s%n",
                                child.getName(),
                                child.getBirthday());
                    }
                }
            }
        }
    }

    private static Person personDontExist(String personInfo, List<Person> tree) {
        for (Person person : tree) {
            if (person.getName() != null) {
                if (person.getName().equals(personInfo)) {
                    return person;
                }
            }
            if (person.getBirthday() != null) {
                if (person.getBirthday().equals(personInfo)) {
                    return person;
                }
            }
        }
        return null;
    }

    private static boolean personDontExist(String name, String birthday, List<Person> tree) {
        for (Person person : tree) {
            if (person.getName() != null && person.getName().equals(name)) {
                return false;
            }
            if (person.getBirthday() != null && person.getBirthday().equals(birthday)) {
                return false;
            }
        }
        return true;
    }
}

class Person {
    String name;
    String birthday;
    Set<Person> parents;
    Set<Person> children;

    public Person(String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
        this.parents = new LinkedHashSet<>();
        this.children = new LinkedHashSet<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Set<Person> getParents() {
        return parents;
    }

    public Set<Person> getChildren() {
        return children;
    }
}