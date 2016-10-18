package Repository;

import IO.OutputWriter;
import StaticData.ExceptionMessages;
import StaticData.SessionData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//written in second LAB -> class to read(from file) data about students, their courses and marks,
//initialize it, save it in a Map and finally print this data(for course or for student in course -> see below methods)
public class StudentsRepository {

    public static boolean isDataInitialized = false;
    public static HashMap<String, HashMap<String, ArrayList<Integer>>> studentsByCourse;

    //method to initialize data for students -> we call it from console(readDb command + name of the file, where is data) -> it calls readData()
    public static void initializeData(String fileName) {

        if (isDataInitialized) {
            OutputWriter.writeMessageOnNewLine(ExceptionMessages.DATA_ALREADY_INITIALIZED);
            return;
        }
        studentsByCourse = new HashMap<>();
        readData(fileName);
        if (studentsByCourse.size() > 0) {
            isDataInitialized = true;
            OutputWriter.writeMessageOnNewLine("Data read.");
        } else {
            OutputWriter.writeMessageOnNewLine("Data not read.");
        }

    }

    private static void readData(String fileName) {
        String path = SessionData.currentPath + "\\resources\\files\\" + fileName;//creating path to dataFile
        //regex written in sixth LAB
        String regex = "([A-Z][a-zA-Z#+]*_[A-Z][a-z]{2}_\\d{4})\\s+([A-Z][a-z]{0,3}\\d{2}_\\d{2,4})\\s+(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));

            for (String line : lines) {
                //initializing data for students by splitting current line from dataFile without using regex -> old, fom sixth LAb we use regex
                //and new file with data about courses and students
//                String[] tokens = line.split("\\s+");
//                if (tokens.length != 3) {
//                    continue;
//                }
                String course = null;
                String student = null;
                int mark = 0;

                matcher = pattern.matcher(line);
                if (!line.isEmpty() && matcher.find()) {
                    course = matcher.group(1);
                    student = matcher.group(2);
                    mark = Integer.parseInt(matcher.group(3));
                }

                if (mark >= 0 && mark <= 100) {
                    if (!studentsByCourse.containsKey(course)) {
                        studentsByCourse.put(course, new HashMap<>());
                    }
                    if (!studentsByCourse.get(course).containsKey(student)) {
                        studentsByCourse.get(course).put(student, new ArrayList<>());
                    }
                    studentsByCourse.get(course).get(student).add(mark);
                }
            }
        } catch (IOException e) {
            OutputWriter.displayException("File not found.");
        }
    }

    //############################################################################################################################
//these two methods we call from main to search info about students(after data for students is initialized)
    public static void getStudentMarksInCourse(String course, String student) {

        if (!isQueryForStudentPossible(course, student)) {
            return;
        }
        ArrayList<Integer> marks = studentsByCourse.get(course).get(student);
        OutputWriter.printStudent(student, marks);
    }

    //.........
    public static void getStudentsByCourse(String course) {

        if (!isQueryForCoursePossible(course)) {
            return;
        }
        OutputWriter.writeMessageOnNewLine(course + ":");
        for (Map.Entry<String, ArrayList<Integer>> student : studentsByCourse.get(course).entrySet()) {
            OutputWriter.printStudent(student.getKey(), student.getValue());
        }
    }

    //##########################################################################################################################
//auxiliary methods to check if given course...
    private static boolean isQueryForCoursePossible(String courseName) {
        if (!isDataInitialized) {
            OutputWriter.writeMessageOnNewLine(ExceptionMessages.DATA_NOT_INITIALIZED);
            return false;
        }
        if (!studentsByCourse.containsKey(courseName)) {
            OutputWriter.writeMessageOnNewLine(ExceptionMessages.NON_EXSISTING_COURSE);
            return false;
        }
        return true;
    }

    //or given student exist, if NO -> print exception message
    private static boolean isQueryForStudentPossible(String courseName, String studentName) {
        if (!isQueryForCoursePossible(courseName)) {
            return false;
        }
        if (!studentsByCourse.get(courseName).containsKey(studentName)) {
            OutputWriter.writeMessageOnNewLine(ExceptionMessages.NON_EXSISTING_STUDENT);
            return false;
        }
        return true;
    }

    //written in seventh LAB
    public static void printFilteredStudents(String course, String filter, Integer numberOfStudents) {

        if (!isQueryForCoursePossible(course)) {
            return;
        }
        if (numberOfStudents == null) {
            numberOfStudents = studentsByCourse.get(course).size();
        }
        RepositoryFilters.printFilteredStudents(studentsByCourse.get(course), filter, numberOfStudents);
    }

    public static void printOrderedStudents(String course, String compareType, Integer numberOfStudents) {

        if (!isQueryForCoursePossible(course)) {
            return;
        }
        if (numberOfStudents == null) {
            numberOfStudents = studentsByCourse.get(course).size();
        }
        RepositorySorters.printSortedStudents(studentsByCourse.get(course), compareType, numberOfStudents);
    }

}
