package defining_classes.problem_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P09_Google {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Person> people = new ArrayList<>();

        String line = reader.readLine();
        while (!line.equals("End")) {
            String[] tokens = line.split("[\\s]+");
            String name = tokens[0];
            Person person = new Person(name);
            boolean findPerson = false;
            for (Person currentPerson : people) {
                if (currentPerson.getName().equals(name)) {
                    person = currentPerson;
                    findPerson = true;
                    break;
                }
            }

            String key = tokens[1];
            if (key.equals("company")) {
                String companyName = tokens[2];
                String department = tokens[3];
                double salary = Double.valueOf(tokens[4]);
                person.setCompany(companyName, department, salary);
            } else if (key.equals("car")) {
                String model = tokens[2];
                int speed = Integer.valueOf(tokens[3]);
                person.setCar(model, speed);
            } else if (key.equals("pokemon")) {
                String pokemonName = tokens[2];
                String type = tokens[3];
                person.addPokemons(pokemonName, type);
            } else if (key.equals("parents")) {
                String parentName = tokens[2];
                String birthday = tokens[3];
                person.addParents(parentName, birthday);
            } else {
                String childName = tokens[2];
                String birthday = tokens[3];
                person.addChildren(childName, birthday);
            }
            if (!findPerson) {
                people.add(person);
            }
            line = reader.readLine();
        }
        line = reader.readLine();
        for (Person person : people) {
            if (line.equals(person.getName())) {
                System.out.println(person.getName());
                System.out.println("Company:");
                if (person.getCompany() != null) {
                    System.out.printf("%s %s %.2f%n",
                            person.getCompany().getCompanyName(),
                            person.getCompany().getDepartment(),
                            person.getCompany().getSalary());
                }
                System.out.println("Car:");
                if (person.getCar() != null) {
                    System.out.printf("%s %d%n",
                            person.getCar().getModel(),
                            person.getCar().getSpeed());
                }
                System.out.println("Pokemon:");
                for (Person.Pokemon pokemon : person.getPokemonList()) {
                    System.out.printf("%s %s%n",
                            pokemon.getPokemonName(),
                            pokemon.getType());
                }
                System.out.println("Parents:");
                for (Person.Parents parent : person.getParentsList()) {
                    System.out.printf("%s %s%n",
                            parent.getName(),
                            parent.getBirthday());
                }
                System.out.println("Children:");
                for (Person.Children child : person.getChildrenList()) {
                    System.out.printf("%s %s%n",
                            child.getName(),
                            child.getBirthday());
                }
                break;
            }
        }
    }
}

class Person {
    String name;
    Company company;
    Car car;
    List<Pokemon> pokemonList;
    List<Parents> parentsList;
    List<Children> childrenList;

    public Person(String name) {
        this.name = name;
        this.pokemonList = new ArrayList<>();
        this.parentsList = new ArrayList<>();
        this.childrenList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Company getCompany() {
        return company;
    }

    public Car getCar() {
        return car;
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public List<Parents> getParentsList() {
        return parentsList;
    }

    public List<Children> getChildrenList() {
        return childrenList;
    }

    public void setCompany(String companyName, String department, double salary) {
        this.company = new Company(companyName, department, salary);
    }

    public void setCar(String model, int speed) {
        this.car = new Car(model, speed);
    }
    public void addPokemons(String pokemonName, String type) {
        Pokemon pokemon = new Pokemon(pokemonName, type);
        this.pokemonList.add(pokemon);
    }

    public void addParents(String parentName, String birthday) {
        Parents parent = new Parents(parentName, birthday);
        this.parentsList.add(parent);
    }

    public void addChildren(String childName, String birthday) {
        Children child = new Children(childName, birthday);
        this.childrenList.add(child);
    }
    class Company {
        String companyName;
        String department;
        double salary;

        public Company(String companyName, String department, double salary) {
            this.companyName = companyName;
            this.department = department;
            this.salary = salary;
        }

        public String getCompanyName() {
            return companyName;
        }

        public String getDepartment() {
            return department;
        }

        public double getSalary() {
            return salary;
        }
    }

    class Car {
        String model;
        int speed;

        public Car(String model, int speed) {
            this.model = model;
            this.speed = speed;
        }

        public String getModel() {
            return model;
        }

        public int getSpeed() {
            return speed;
        }
    }

    class Pokemon {
        String pokemonName;
        String type;

        public Pokemon(String pokemonName, String type) {
            this.pokemonName = pokemonName;
            this.type = type;
        }

        public String getPokemonName() {
            return pokemonName;
        }

        public String getType() {
            return type;
        }
    }

    class Parents {
        String name;
        String birthday;

        public Parents(String name, String birthday) {
            this.name = name;
            this.birthday = birthday;
        }

        public String getName() {
            return name;
        }

        public String getBirthday() {
            return birthday;
        }
    }

    class Children {
        String name;
        String birthday;

        public Children(String name, String birthday) {
            this.name = name;
            this.birthday = birthday;
        }

        public String getName() {
            return name;
        }

        public String getBirthday() {
            return birthday;
        }
    }
}

