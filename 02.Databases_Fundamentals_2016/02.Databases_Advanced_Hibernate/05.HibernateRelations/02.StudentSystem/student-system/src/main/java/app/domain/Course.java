package app.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    @Basic
    private String name;

    @Column(nullable = true)
    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Basic
    private BigDecimal price;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "courses_students",
    joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "student_id"))
    private Set<Student> students;

    @OneToMany(mappedBy = "course", targetEntity = Resource.class,
    cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Resource> resources;

    @OneToMany(mappedBy = "course", targetEntity = Homework.class,
    cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Homework> homeworkSubmissions;

    public Course() {
        this.setStudents(new HashSet<>());
        this.setResources(new HashSet<>());
        this.setHomeworkSubmissions(new HashSet<>());
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long course_id) {
        this.courseId = course_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    public Set<Homework> getHomeworkSubmissions() {
        return homeworkSubmissions;
    }

    public void setHomeworkSubmissions(Set<Homework> homeworkSubmissions) {
        this.homeworkSubmissions = homeworkSubmissions;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void addResource(Resource resource) {
        this.resources.add(resource);
    }

    public void addHomeworkSubmission(Homework homeworkSubmission) {
        this.homeworkSubmissions.add(homeworkSubmission);
    }
}
