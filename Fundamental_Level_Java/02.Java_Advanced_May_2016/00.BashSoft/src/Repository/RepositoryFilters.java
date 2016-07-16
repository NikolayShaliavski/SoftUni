package Repository;

import IO.OutputWriter;
import StaticData.ExceptionMessages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

public class RepositoryFilters {

    //written in seventh LAB
    public static void printFilteredStudents(HashMap<String, ArrayList<Integer>> courseData, String filterType, Integer numberOfStudents) {

        Predicate<Double> filter = createFilter(filterType);
        if (filter == null) {
            OutputWriter.displayException(ExceptionMessages.INVALID_FILTER);
            return;
        }

        int studentsCount = 0;
        for (String student : courseData.keySet()) {
            if (studentsCount == numberOfStudents) {
                break;
            }

            ArrayList<Integer> studentsMarks = courseData.get(student);
            //written in 8 LAB -> lambda expression using stream API instead calling getStudentsAverageGrade() -> it was deleted
            Double averageMark = studentsMarks.stream().mapToInt(Integer::valueOf)
                    .average().getAsDouble();
            Double percentageOfFulfiltment = averageMark / 100;
            Double mark = percentageOfFulfiltment * 4 + 2;

            if (filter.test(mark)) {
                OutputWriter.printStudent(student, studentsMarks);
                studentsCount++;
            }
        }
    }

    private static Predicate<Double> createFilter(String filterType) {
        switch (filterType) {
            case "excellent":
                return mark -> mark >= 5.0;
            case "average":
                return mark -> mark >= 3.50 && mark < 5.0;
            case "poor":
                return mark -> mark < 3.50;
            default:
                return null;
        }
    }
}
