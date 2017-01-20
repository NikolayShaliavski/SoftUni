package creational.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * Prototype design pattern is used when the Object creation is a costly affair
 * and requires a lot of time and resources and you have a similar object already existing.
 * Prototype pattern provides a mechanism to copy the original object to a new object
 * and then modify it according to our needs.
 * Prototype design pattern uses java cloning to copy the object.
 */
public class Employees implements Cloneable {

    private List<String> employees;

    public Employees() {
        this.employees = new ArrayList<>();
    }

    public Employees(List<String> employees) {
        this.employees = employees;
    }

    public List<String> getEmployees() {
        return this.employees;
    }

    public void loadData() {
        //read all employees from database and put into the list
        this.employees.add("Nick");
        this.employees.add("Ivan");
        this.employees.add("Mario");
        this.employees.add("Desislava");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        List<String> temp = new ArrayList<>();
        for (String employee : this.employees) {
            temp.add(employee);
        }
        return new Employees(temp);
    }
}
