package app.services.servicesImpl;

import app.domain.Course;
import app.repositories.CourseRepository;
import app.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void create(Course course) {
        this.courseRepository.saveAndFlush(course);
    }

    //3.2
    @Override
    public List<Course> getCAndRes() {
        return this.courseRepository.getCoursesAndResources();
    }

    //3.3
    @Override
    public List<Object[]> getCoursesMoreThanFiveRes() {
        return this.courseRepository.getCoursesMoreThanFiveRes();
    }
}