package defining_classes.problem_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class P04_CompanyRoaster {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int lines = Integer.valueOf(reader.readLine());
        HashMap<String, ArrayList<Employee>> stat = new HashMap<>();

        for (int i = 0; i < lines; i++) {
            String[] tokens = reader.readLine().split("[\\s]+");
            Employee employee = null;

            if (tokens.length == 6) {
                String name = tokens[0];
                double salary = Double.valueOf(tokens[1]);
                String position = tokens[2];
                String department = tokens[3];
                String email = tokens[4];
                int age = Integer.valueOf(tokens[5]);
                employee = new Employee(name, salary, position, department, email, age);
            } else if (tokens.length == 5) {
                String name = tokens[0];
                double salary = Double.valueOf(tokens[1]);
                String position = tokens[2];
                String department = tokens[3];

                if (tokens[4].contains("@")) {
                    String email = tokens[4];
                    employee = new Employee(name, salary, position, department, email);
                } else {
                    int age = Integer.valueOf(tokens[4]);
                    employee = new Employee(name, salary, position, department, age);
                }
            } else {
                String name = tokens[0];
                double salary = Double.valueOf(tokens[1]);
                String position = tokens[2];
                String department = tokens[3];
                employee = new Employee(name, salary, position, department);
            }
            if (!stat.containsKey(employee.department)) {
                stat.put(employee.department, new ArrayList<>());
            }
            stat.get(employee.department).add(employee);
        }

        double maxSalary = Double.MIN_VALUE;
        String departmentToPrint = "";
        for (Map.Entry employes : stat.entrySet()) {
            double average = getAverage((ArrayList) employes.getValue());
            if (average > maxSalary) {
                departmentToPrint = (String) employes.getKey();
                maxSalary = average;
            }
        }

        System.out.printf("Highest Average Salary: %s%n", departmentToPrint);
        ArrayList<Employee> toPrint = stat.get(departmentToPrint);
        toPrint.stream()
                .sorted((pers1, pers2) -> Double.compare(pers2.salary, pers1.salary))
                .forEach(person -> System.out.printf("%s %.2f %s %d%n",
                        person.name,
                        person.salary,
                        person.email,
                        person.age));

    }

    private static double getAverage(ArrayList employes) {
        double result = 0.0;
        for (Object employe : employes) {
            result += ((Employee) employe).salary;
        }
        return result / employes.size();
    }
}

class Employee {
    static final String DEFAULT_EMAIL = "n/a";
    static final int DEFAULT_AGE = -1;

    String name;
    double salary;
    String position;
    String department;
    String email;
    int age;

    public Employee(String name, double salary, String position, String department, String email, int age) {
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;
        this.email = email;
        this.age = age;
    }

    public Employee(String name, double salary, String position, String department, String email) {
        this(name, salary, position, department, email, DEFAULT_AGE);
    }

    public Employee(String name, double salary, String position, String department, int age) {
        this(name, salary, position, department, DEFAULT_EMAIL, age);
    }

    public Employee(String name, double salary, String position, String department) {
        this(name, salary, position, department, DEFAULT_EMAIL, DEFAULT_AGE);
    }
}
