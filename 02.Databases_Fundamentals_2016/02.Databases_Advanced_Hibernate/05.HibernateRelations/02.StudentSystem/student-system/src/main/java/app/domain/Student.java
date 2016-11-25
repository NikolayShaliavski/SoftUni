package app.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

    private Long studentId;

    private String name;

    private String phoneNumber;

    private Date registrationDate;

    private Date birthday;

    private Set<Course> courses;

    private Set<Homework> homeworks;

    public Student() {
        this.setCourses(new HashSet<>());
        this.setHomeworks(new HashSet<>());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long student_id) {
        this.studentId = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "phone_number", nullable = true)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "registration_date")
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Column(name = "birthday", nullable = true)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @ManyToMany(mappedBy = "students", targetEntity = Course.class,
    fetch = FetchType.EAGER)
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @OneToMany(mappedBy = "student", targetEntity = Homework.class,
    fetch = FetchType.EAGER)
    public Set<Homework> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(Set<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void addHomework(Homework homework) {
        this.homeworks.add(homework);
    }
}
