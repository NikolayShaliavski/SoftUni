package problem_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P03_Mankind {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] studentInfo = reader.readLine().split("[\\s]+");
        String[] workerInfo = reader.readLine().split("[\\s]+");

        String studentFirstName = studentInfo[0];
        String studentLastName = studentInfo[1];
        String studentFacNumber = studentInfo[2];

        String workerFirstName = workerInfo[0];
        String workerLastName = workerInfo[1];
        double workerSalary = Double.valueOf(workerInfo[2]);
        double workingHours = Double.valueOf(workerInfo[3]);

        try {
            Student student = new Student(studentFirstName, studentLastName, studentFacNumber);
            Worker worker = new Worker(workerFirstName, workerLastName, workerSalary, workingHours);

            System.out.println(student.toString());
            System.out.println(worker.toString());
        } catch (IllegalArgumentException iaex) {
            System.out.println(iaex.getMessage());
        }
    }
}

class Human {

    private String firstName;
    private String lastName;

    public Human(String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    private String getFirstName() {
        return this.firstName;
    }

    protected void setFirstName(String firstName) {
        Pattern pattern = Pattern.compile("^([A-Z][a-z0-9]+)$");
        Matcher matcher = pattern.matcher(firstName);
        if (firstName.length() < 4) {
            throw new IllegalArgumentException("\"Expected length at least 4 symbols! Argument: firstName\"");
        }
        if (!matcher.find()) {
            throw new IllegalArgumentException("\"Expected upper case letter! Argument: firstName\"");
        }
        this.firstName = firstName;
    }

    private String getLastName() {
        return this.lastName;
    }

    protected void setLastName(String lastName) {
        Pattern pattern = Pattern.compile("^([A-Z][a-z0-9]+)$");
        Matcher matcher = pattern.matcher(lastName);
        if (lastName.length() < 3) {
            throw new IllegalArgumentException("\"Expected length at least 3 symbols! Argument: lastName\"");
        }
        if (!matcher.find()) {
            throw new IllegalArgumentException("\"Expected upper case letter! Argument: lastName\"");
        }
        this.lastName = lastName;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("First Name: %s%n", this.getFirstName()))
                .append(String.format("Last Name: %s%n", this.getLastName()));
        return sb.toString();
    }
}

class Student extends Human {

    private String facNumber;

    public Student(String firstName, String lastName, String facNumber) {
        super(firstName, lastName);
        this.setFacNumber(facNumber);
    }

    private String getFacNumber() {
        return this.facNumber;
    }

    private void setFacNumber(String facNumber) {
        Pattern patternDigit = Pattern.compile("^([0-9]+)$");
        Pattern patternLetter = Pattern.compile("^([A-Za-z]+)$");
        Matcher matcherDigit = patternDigit.matcher(facNumber);
        Matcher matcherLetter = patternLetter.matcher(facNumber);

        if (!matcherDigit.find() && !matcherLetter.find()) {
            throw new IllegalArgumentException("Invalid faculty number!");
        }
        if (facNumber.length() < 5 || facNumber.length() > 10) {
            throw new IllegalArgumentException("Invalid faculty number!");
        }
        this.facNumber = facNumber;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(String.format("Faculty number: %s%n", this.getFacNumber()));
        return builder.toString();
    }
}

class Worker extends Human {

    private double weekSalary;
    private double workingHours;

    public Worker(String firstName, String lastName, double weekSalary, double workingHours) {
        super(firstName, lastName);
        this.setSalary(weekSalary);
        this.setWorkingHours(workingHours);
    }

    private double getWeekSalary() {
        return this.weekSalary;
    }

    private void setSalary(double salary) {
        if (salary < 10) {
            throw new IllegalArgumentException("\"Expected value mismatch! Argument: weekSalary\"");
        }
        this.weekSalary = salary;
    }

    private double getWorkingHours() {
        return this.workingHours;
    }

    private void setWorkingHours(double workingHours) {
        if (workingHours < 1 || workingHours > 12) {
            throw new IllegalArgumentException("\"Expected value mismatch! Argument: workHoursPerDay\"");
        }
        this.workingHours = workingHours;
    }

    private double getMoneyPerHour() {
        return this.weekSalary / (this.workingHours * 7);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(String.format("Week Salary: %.2f%n", this.getWeekSalary()))
                .append(String.format("Hours per day: %.2f%n", this.getWorkingHours()))
                .append(String.format("Salary per hour: %.2f%n", this.getMoneyPerHour()));
        return builder.toString();
    }
}