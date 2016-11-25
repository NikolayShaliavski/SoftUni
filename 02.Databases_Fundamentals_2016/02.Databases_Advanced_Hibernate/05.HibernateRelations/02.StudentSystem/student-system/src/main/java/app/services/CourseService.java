package app.services;

import app.domain.Course;

import java.util.List;

public interface CourseService {

    void create(Course course);

    //3.2
    List<Course> getCAndRes();
    //3.3
    List<Object[]> getCoursesMoreThanFiveRes();

}