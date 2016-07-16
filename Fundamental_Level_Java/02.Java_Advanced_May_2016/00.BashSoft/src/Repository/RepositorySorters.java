package Repository;

import IO.OutputWriter;

import java.util.*;
import java.util.stream.Collectors;

public class RepositorySorters {

    //written in seventh LAB
    public static void printSortedStudents(HashMap<String, ArrayList<Integer>> courseData, String comparisonType, Integer numberOfStudents) {

        //written in 8 LAB -> comparator using stream API -> gets two ArrayLists of Integers(current student grades),
        //gets their average as Double value and finally compare them
        Comparator<Map.Entry<String, ArrayList<Integer>>> comparator = (x, y) ->
                Double.compare(x.getValue().stream().mapToInt(Integer::valueOf).average().getAsDouble(),
                        y.getValue().stream().mapToInt(Integer::valueOf).average().getAsDouble());
        //sorting students by given course
        List<String> sortedStudents = courseData.entrySet().stream()
                .sorted(comparator)//using our comparator
                .limit(numberOfStudents)//limiting number of students -> we sort all students, but collect only given number; if number is null ->
                                        //we collect all students -> see StudentRepository -> printOrderedStudents()
                .map(x -> x.getKey())//get keys from stream because we need only names of students
                .collect(Collectors.toList());//collect names to List<String>

        if (comparisonType.equals("descending")) {
            Collections.reverse(sortedStudents);
        }

        for (String sortedStudent : sortedStudents) {
            OutputWriter.printStudent(sortedStudent, courseData.get(sortedStudent));
        }
    }

    //written in & LAb -> comparator to sort student by their grades -> from 8 LAb we will use stream API -> look above
//    private static Comparator<Map.Entry<String, ArrayList<Integer>>> createComparator(String comparisonType) {
//        switch (comparisonType) {
//            case "ascending":
//                return new Comparator<Map.Entry<String, ArrayList<Integer>>>() {
//                    @Override
//                    public int compare(Map.Entry<String, ArrayList<Integer>> firstStudent,
//                                       Map.Entry<String, ArrayList<Integer>> secondStudent) {
//                        Double firstStudentTotal = getTotalScore(firstStudent.getValue());
//                        Double secondStudentTotal = getTotalScore(secondStudent.getValue());
//
//                        return firstStudentTotal.compareTo(secondStudentTotal);
//                    }
//                };
//            case "descending":
//                return new Comparator<Map.Entry<String, ArrayList<Integer>>>() {
//                    @Override
//                    public int compare(Map.Entry<String, ArrayList<Integer>> firstStudent,
//                                       Map.Entry<String, ArrayList<Integer>> secondStudent) {
//                        Double firstStudentTotal = getTotalScore(firstStudent.getValue());
//                        Double secondStudentTotal = getTotalScore(secondStudent.getValue());
//
//                        return secondStudentTotal.compareTo(firstStudentTotal);
//                    }
//                };
//            default:
//                return null;
//        }
//    }
//
//    private static Double getTotalScore(ArrayList<Integer> grades) {
//        Double total = 0D;
//        for (Integer score : grades) {
//            total += score;
//        }
//        return total / grades.size();
//    }
}
