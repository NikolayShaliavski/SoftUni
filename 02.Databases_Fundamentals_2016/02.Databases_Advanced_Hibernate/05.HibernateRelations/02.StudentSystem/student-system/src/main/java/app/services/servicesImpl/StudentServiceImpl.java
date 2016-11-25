package app.services.servicesImpl;

import app.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.repositories.StudentRepository;
import app.services.StudentService;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void create(Student student) {
        this.studentRepository.saveAndFlush(student);
    }

    @Override
    public Student findStudentById(Long id) {
        return this.studentRepository.findOne(id);
    }

    @Override
    public List<Object[]> getAllStudentsAndHomeworks() {
        return this.studentRepository.getAllStudentsAndHomeworks();
    }
}