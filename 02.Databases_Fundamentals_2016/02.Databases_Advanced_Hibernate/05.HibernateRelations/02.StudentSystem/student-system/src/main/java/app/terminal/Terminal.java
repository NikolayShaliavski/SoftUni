package app.terminal;

import app.domain.Course;
import app.domain.Homework;
import app.domain.Resource;
import app.domain.Student;
import app.enums.ContentType;
import app.enums.ResourceType;
import app.services.CourseService;
import app.services.HomeworkService;
import app.services.ResourceService;
import app.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    private final StudentService studentService;

    private final CourseService courseService;

    private final ResourceService resourceService;

    private final HomeworkService homeworkService;

    @Autowired
    public Terminal(StudentService studentService,
                        CourseService courseService,
                        ResourceService resourceService,
                        HomeworkService homeworkService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.resourceService = resourceService;
        this.homeworkService = homeworkService;
    }

    @Override
    public void run(String... strings) throws Exception {

        // this.seed();

        //testing how to get courses from student table - FetchType.EAGER(isn't correct)
//        Student testStudent = this.studentService.findStudentById(1L);
//        Set<Course> studentCourses = testStudent.getCourses();
//        System.out.println();
        //3.1
//        List<Object[]> studentsAndHomeworks = this.studentService.getAllStudentsAndHomeworks();
//        for (Object[] sh : studentsAndHomeworks) {
//            System.out.printf("%s: %s, %s%n", sh[0], sh[1], (ContentType) sh[2]);
//        }

        //3.2
//        List<Course> coursesAndResources = this.courseService.getCAndRes();
//        for (Course course : coursesAndResources) {
//            System.out.printf("Course: %s, %s%n", course.getName(), course.getDescription());
//            for (Resource res : course.getResources()) {
//                System.out.printf("Resource: %d, %s, %s, %s%n",
//                        res.getResource_id(),
//                        res.getName(),
//                        res.getResourceType(),
//                        res.getUrl());
//            }
//        }

        //3.3
        List<Object[]> courses = this.courseService.getCoursesMoreThanFiveRes();
        for (Object[] course : courses) {
            System.out.printf("%s - %d%n", course[0], (long) course[1]);
            //COUNT() method from DB returns Long!!!!!!!!!!!!!!!
        }

    }

    private void seed() {
        Course course = new Course();
        course.setName("PHP");
        course.setDescription("Good course");
        course.setStartDate(new Date());
        course.setEndDate(new Date());
        course.setPrice(new BigDecimal("0"));

        Student student = new Student();
        student.setName("Asen");
        student.setBirthday(new Date());
        student.setPhoneNumber("089896597864");
        student.setRegistrationDate(new Date());

        Homework homework = new Homework();
        homework.setContent("PHP first homework");
        homework.setContentType(ContentType.PDF);
        homework.setSubmissionDate(new Date());
        homework.setCourse(course);
        homework.setStudent(student);

        Resource resource = new Resource();
        resource.setName("First lecture video PHP");
        resource.setResourceType(ResourceType.VIDEO);
        resource.setUrl("PHP course page url");
        resource.setCourse(course);

        Resource resourceTwo = new Resource();
        resourceTwo.setName("Second lecture video PHP");
        resourceTwo.setResourceType(ResourceType.VIDEO);
        resourceTwo.setUrl("PHP course page url");
        resourceTwo.setCourse(course);

        course.addStudent(student);
        course.addResource(resource);
        course.addResource(resourceTwo);
        course.addHomeworkSubmission(homework);

//        student.addCourse(course);
//        student.addHomework(homework);

        this.courseService.create(course);
        /*
        in Course.class used cascade = CascadeType.ALL
        in relations with other objects
         */
//        this.studentService.create(student);
//        this.homeworkService.create(homework);
//        this.resourceService.create(resource);
    }
}
