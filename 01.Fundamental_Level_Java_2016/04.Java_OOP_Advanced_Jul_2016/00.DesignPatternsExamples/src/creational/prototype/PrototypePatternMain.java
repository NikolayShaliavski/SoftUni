package creational.prototype;

import java.util.List;

public class PrototypePatternMain {

    public static void main(String[] args) throws CloneNotSupportedException {

        Employees employees = new Employees();
        employees.loadData();

        //Use the clone method to get the Employee object
        Employees newEmployees1 = (Employees) employees.clone();
        Employees newEmployees2 = (Employees) employees.clone();
        List<String> list1 = newEmployees1.getEmployees();
        list1.add("John");
        List<String> list2 = newEmployees2.getEmployees();
        list2.remove("Nick");

        //original employees collection
        System.out.println("Original employees list: " + employees.getEmployees());

        //modified employees collection with new employee John
        System.out.println("NewEmployees1 list: " + list1);

        //modified employees collection without Nick
        System.out.println("NewEmployees2 list: " + list2);
    }
}
