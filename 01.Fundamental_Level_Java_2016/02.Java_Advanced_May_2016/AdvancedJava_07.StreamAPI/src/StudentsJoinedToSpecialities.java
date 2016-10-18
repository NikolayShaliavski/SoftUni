import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentsJoinedToSpecialities {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Speciality> specialities = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        String line = reader.readLine();

        while (!line.equals("Students:")) {
            String[] tokens = line.split("[\\s]+");
            String spec = tokens[0] + " " + tokens[1];
            String facNumber = tokens[2];
            Speciality speciality = new Speciality(spec, facNumber);
            specialities.add(speciality);
            line = reader.readLine();
        }
        line = reader.readLine();
        while (!line.equals("END")) {
            String[] studentInfo = line.split("[\\s]+");
            String name = studentInfo[1] + " " + studentInfo[2];
            String facNumber = studentInfo[0];
            Student student = new Student(name, facNumber);
            students.add(student);

            line = reader.readLine();
        }

        List<String> studentSpecialities = new ArrayList<>();

        students.stream()
                .flatMap(student ->
                        specialities.stream()
                                .filter(speciality -> {
                                    if (speciality.getFacNumber().equals(student.getFacNumber())) {
                                        studentSpecialities.add(String.format("%s %s %s",
                                                student.getName(),
                                                student.getFacNumber(),
                                                speciality.getSpeciality()));
                                    }
                                    return student.getFacNumber().equals(speciality.getFacNumber());
                                }))
                .collect(Collectors.toList());
        
        List<String> sorted = studentSpecialities.stream()
                .sorted((student1, student2) -> {
                    String[] firstStudent = student1.split("[\\s]+");
                    String[] secondStudent = student2.split("[\\s]+");
                    String firstName = firstStudent[0] + " " + firstStudent[1];
                    String secondName = secondStudent[0] + " " + secondStudent[1];

                    return firstName.compareTo(secondName);
                })
                .collect(Collectors.toList());

        for (String student : sorted) {
            System.out.println(student);
        }
    }
}

class Speciality {

    public String speciality;
    public String facNumber;

    public Speciality(String speciality, String facNumber) {
        this.speciality = speciality;
        this.facNumber = facNumber;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getFacNumber() {
        return facNumber;
    }
}

class Student {

    public String name;
    public String facNumber;

    public Student(String name, String facNumber) {
        this.name = name;
        this.facNumber = facNumber;
    }

    public String getName() {
        return name;
    }

    public String getFacNumber() {
        return facNumber;
    }
}

