package bg.softuni.models;

import bg.softuni.exceptions.DuplicateEntryInStructureException;
import bg.softuni.exceptions.InvalidNumberOfScores;
import bg.softuni.exceptions.InvalidStringException;
import bg.softuni.exceptions.KeyNotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Student {

    private String userName;
    private LinkedHashMap<String, Course> enrolledCourses;
    private LinkedHashMap<String, Double> marksByCourseName;

    public Student(String userName) {
        this.setUserName(userName);
        this.enrolledCourses = new LinkedHashMap<>();
        this.marksByCourseName = new LinkedHashMap<>();
    }

    public String getUserName() {
        return this.userName;
    }

    private void setUserName(String userNAme) {
        if (userNAme == null || userNAme.equals("")) {
            throw new InvalidStringException();
        }
        this.userName = userNAme;
    }

    public Map<String, Course> getEnrolledCourses() {
        return Collections.unmodifiableMap(this.enrolledCourses);
    }

    public Map<String, Double> getMarksByCourseName() {
        return Collections.unmodifiableMap(this.marksByCourseName);
    }

    public void enrollInCourse(Course course) {
        if (this.enrolledCourses.containsKey(course.getName())) {
            throw new DuplicateEntryInStructureException(this.userName, course.getName());
        }
        this.enrolledCourses.put(course.getName(), course);
    }

    public void setMarksOnCourse(String courseName, int...scores) {
        if (!this.enrolledCourses.containsKey(courseName)) {
            throw new KeyNotFoundException();
        }
        if (scores.length > Course.NUMBER_OF_TASKS_ON_EXAM) {
            throw new InvalidNumberOfScores();
        }
        double mark = calculateMark(scores);
        this.marksByCourseName.put(courseName, mark);
    }

    private double calculateMark(int[] scores) {
        double percentageOfSolvedExam = Arrays.stream(scores).sum() /
                (double) (Course.NUMBER_OF_TASKS_ON_EXAM * Course.MAX_SCORE_ON_EXAM_TASK);
        double mark = percentageOfSolvedExam * 4 + 2;
        return mark;
    }
}
