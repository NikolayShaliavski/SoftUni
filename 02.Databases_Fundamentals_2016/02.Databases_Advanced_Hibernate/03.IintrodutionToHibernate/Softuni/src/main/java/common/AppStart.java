package common;

import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AppStart {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        //thirdTaskCreatingObjects(em);
        //fourthTaskRemoveObjects(em);
        //fifthTaskContainsEmployee(bf, em);
        //sixthTaskDataRefresh(em);
        //seventhTaskEmployeesWithSalaryAbove50000(em);
        //seventhTaskEmployeesFromSeattle(em);
        //eightTaskNewAddressAndUpdateEmployee(em);
        ninthTaskDeleteProjectById(bf, em);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }

    private static void ninthTaskDeleteProjectById(BufferedReader bf, EntityManager em) throws IOException {
        int projectId = Integer.valueOf(bf.readLine());
        Project projectToDelete = em.find(Project.class, projectId);

        em.remove(projectToDelete);
    }

    private static void eightTaskNewAddressAndUpdateEmployee(EntityManager em) {
        Town town = (Town)em.createQuery(
                "SELECT t FROM Town t WHERE t.name LIKE 'Sofia'").getSingleResult();

        Address address = new Address("Vitoshka 15", town);
        em.persist(address);

        Employee employee = (Employee) em.createQuery(
                "SELECT e FROM Employee e WHERE e.lastName LIKE 'Nakov'").getSingleResult();

        employee.setAddress(address);
        em.persist(employee);
    }

    private static void seventhTaskEmployeesFromSeattle(EntityManager em) {
        Query query = em.createQuery(
                "SELECT e FROM Employee e " +
                        "INNER JOIN e.department d " +
                        "WHERE d.name LIKE 'Research and Development'");

        List<Employee> employees = query.getResultList();
        employees = employees.stream().sorted( (e1, e2) -> {
            int result = e1.getSalary().compareTo(e2.getSalary());
            if (result == 0) {
                result = e2.getFirstName().compareTo(e1.getFirstName());
            }
            return result;
        }).collect(Collectors.toList());

        for (Employee employee : employees) {
            System.out.printf("%s %s from %s - %.2f%n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getDepartment().getName(),
                    employee.getSalary());
        }
    }

    private static void seventhTaskEmployeesWithSalaryAbove50000(EntityManager em) {
        List<String> employeesNames =
                em.createQuery("SELECT e.firstName FROM Employee e WHERE e.salary > 50000").
                        getResultList();

        for (String employeesName : employeesNames) {
            System.out.println(employeesName);
        }
    }

    private static void sixthTaskDataRefresh(EntityManager em) {
        Employee employee = em.find(Employee.class, 4);
        employee.setFirstName(employee.getFirstName().toUpperCase());

        em.refresh(employee);//if we use refresh the changes in first name don't apply
        em.persist(employee);//so we persist object with same first name

        Employee employeeUppercase = em.find(Employee.class, 4);
        System.out.println(employeeUppercase.getFirstName());
    }

    private static void fifthTaskContainsEmployee(BufferedReader bf, EntityManager em) throws IOException {
        String employeeName = bf.readLine();

        List<Employee> result = em.createQuery("SELECT e FROM Employee e " +
                "WHERE CONCAT(e.firstName, ' ', e.lastName) LIKE :employeeName")
                .setParameter("employeeName", employeeName).getResultList();

        if (result.size() > 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    private static void fourthTaskRemoveObjects(EntityManager em) {

        //select all towns from database
        List<Town> towns = em.createQuery("SELECT t FROM Town t").getResultList();

        for (Town town : towns) {
            if (town.getName().length() > 5) {
                em.detach(town);//detach objects from persistence context
            }
        }

        for (Town town : towns) {
            //if towns isn't detached from persistence context
            if (em.contains(town)) {
                //set it's nam eto lowerCase
                town.setName(town.getName().toLowerCase());
            }
        }

        //after that all attached objects will be save to database
        // (look in main, em.detTransaction().commit())
    }

    private static void thirdTaskCreatingObjects(EntityManager em) {
        //creating new town
        Town town = new Town("Burgas");

        //new addresses
        Address firstAddress = new Address("Center", town);
        Address secondAddress = new Address("West", town);
        Address thirdAddress = new Address("East", town);

        //find employee with id = 1, which will be manager of new employees
        Employee manager = em.find(Employee.class, 1);

        //new department
        Department department = new Department("Training", manager);

        //find projects for new employees
        Project projectOne = em.find(Project.class, 1);
        Project projectTwo = em.find(Project.class, 2);
        Project projectThree = em.find(Project.class, 3);
        Project projectFour = em.find(Project.class, 4);
        Project projectFive = em.find(Project.class, 5);
        Project projectSix = em.find(Project.class, 6);

        //create new employees
        Employee firstEmployee = new Employee(
                "Nick", "Nick", "A.", "Java Trainer", new Date(),
                new BigDecimal("5000"), department, manager, firstAddress);
        firstEmployee.addProjects(projectOne, projectTwo);

        Employee secondEmployee = new Employee(
                "Alex", "Alex", "B.", "C# Trainer", new Date(),
                new BigDecimal("4000"), department, manager, secondAddress);
        secondEmployee.addProjects(projectThree, projectFour);

        Employee thirdEmployee = new Employee(
                "Ben", "Johnson", "A.", "Javascript Trainer", new Date(),
                new BigDecimal("6000"), department, manager, thirdAddress);
        thirdEmployee.addProjects(projectFive, projectSix);

        em.persist(town);
        em.persist(firstAddress);
        em.persist(secondAddress);
        em.persist(thirdAddress);
        em.persist(department);
        em.persist(firstEmployee);
        em.persist(secondEmployee);
        em.persist(thirdEmployee);
    }
}
