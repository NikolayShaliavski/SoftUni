package app.services;

import app.domain.Student;

import java.util.List;

public interface StudentService {

    void create(Student student);

    Student findStudentById(Long id);
    List<Object[]> getAllStudentsAndHomeworks();
}