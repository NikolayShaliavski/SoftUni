import java.io.*;
import java.util.*;

public class CSVDatabase {

    public static void main(String[] args) {

        File students = new File("resourses\\students.txt");
        File grades = new File("resourses\\grades.txt");
        Scanner scn = new Scanner(System.in);

        System.out.println("Please insert one of the following commands:");
        System.out.println("-> Search by name\r\n-> Search by ID\r\n-> Delete by ID\r\n-> Insert student\r\n-> Insert grade by ID");
        String command = scn.nextLine();
        int studentID = 0;
        switch (command) {
            case "Search by name":
                System.out.println("Please insert student name:");
                String name = scn.nextLine();
                searchByName(name, students, grades);
                break;
            case "Search by ID":
                System.out.println("Please insert student ID:");
                studentID = scn.nextInt();
                searchByID(studentID, students, grades);
                break;
            case "Delete by ID":
                System.out.println("Please insert student ID:");
                studentID = scn.nextInt();
                deleteByID(studentID, students, grades);
                break;
            case "Insert student":
                System.out.println("Please insert information about student:");
                String info = scn.nextLine();
                insertStudent(info, students, grades);
                break;
            case "Insert grade by ID":
                System.out.println("Please insert student ID, course and grade:");
                String newGrade = scn.nextLine();
                insertGrade(newGrade, grades);
                break;
            default:
                System.out.println("Incorrect command!!!");
                break;
        }
    }

    private static void searchByName(String name, File students, File grades) {

        try (BufferedReader readerStudents = new BufferedReader(new FileReader(students));
             BufferedReader readerGrades = new BufferedReader(new FileReader(grades))) {

            String lineStudents = readerStudents.readLine();
            int numberID = 0;
            boolean notFound = true;
            while (lineStudents != null) {

                String[] studentsArr = lineStudents.split(",");
                String studentName = studentsArr[1] + " " + studentsArr[2];
                if (studentName.equals(name)) {
                    notFound = false;
                    numberID = Integer.parseInt(studentsArr[0]);
                    System.out.printf("%s(age: %s, town: %s)\r\n", name, studentsArr[3], studentsArr[4]);
                    break;
                }
                lineStudents = readerStudents.readLine();
            }
            if (notFound) {
                System.out.println("Student does not exist.");
                return;
            }
            String lineGrades = readerGrades.readLine();
            while (lineGrades != null) {
                String[] studentGrades = lineGrades.split(",");
                int currentID = Integer.parseInt(studentGrades[0]);

                if (numberID == currentID) {
                    for (int i = 1; i < studentGrades.length; i++) {
                        String[] course = studentGrades[i].split(" ");
                        String currentCourse = course[0];
                        System.out.printf("# %s:", currentCourse);
                        for (int j = 1; j < course.length; j++) {
                            if (j < course.length - 1) {
                                System.out.printf(" %s,", course[j]);
                            } else {
                                System.out.printf(" %s\r\n", course[j]);
                            }
                        }
                    }
                    break;
                }
                lineGrades = readerGrades.readLine();
            }
        } catch (FileNotFoundException fnfEx) {
            fnfEx.printStackTrace();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    private static void searchByID(Integer studentID, File students, File grades) {

        try (BufferedReader readerStudents = new BufferedReader(new FileReader(students));
             BufferedReader readerGrades = new BufferedReader(new FileReader(grades))) {

            String lineStudents = readerStudents.readLine();
            boolean notFound = true;
            while (lineStudents != null) {
                String[] studentsArr = lineStudents.split(",");
                if (studentID == Integer.parseInt(studentsArr[0])) {
                    notFound = false;
                    String studentName = studentsArr[1] + " " + studentsArr[2];
                    System.out.printf("%s(age: %s, town: %s)\r\n", studentName, studentsArr[3], studentsArr[4]);
                    break;
                }
                lineStudents = readerStudents.readLine();
            }
            if (notFound) {
                System.out.println("Student does not exist.");
                return;
            }
            String lineGrades = readerGrades.readLine();
            while (lineGrades != null) {
                String[] studentGrades = lineGrades.split(",");
                int currentID = Integer.parseInt(studentGrades[0]);

                if (studentID == currentID) {
                    for (int i = 1; i < studentGrades.length; i++) {
                        String[] course = studentGrades[i].split(" ");
                        String currentCourse = course[0];
                        System.out.printf("# %s:", currentCourse);
                        for (int j = 1; j < course.length; j++) {
                            if (j < course.length - 1) {
                                System.out.printf(" %s,", course[j]);
                            } else {
                                System.out.printf(" %s\r\n", course[j]);
                            }
                        }
                    }
                    break;
                }
                lineGrades = readerGrades.readLine();
            }
        } catch (FileNotFoundException fnfEx) {
            fnfEx.printStackTrace();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    private static void deleteByID(Integer studentID, File students, File grades) {

        File tempStudents = new File(students + ".tmp");
        tempStudents.getParentFile().mkdirs();
        File tempGrades = new File(grades + ".tmp");
        tempGrades.getParentFile().mkdirs();

        try (BufferedReader readerStudents = new BufferedReader(new FileReader(students));
             PrintWriter writerStudents = new PrintWriter(new FileWriter(tempStudents, false));
             BufferedReader readerGrades = new BufferedReader(new FileReader(grades));
             PrintWriter writerGrades = new PrintWriter(new FileWriter(tempGrades, false))) {

            String lineStudents = readerStudents.readLine();
            boolean notFound = true;
            while (lineStudents != null) {
                String[] studentsArr = lineStudents.split(",");
                if (studentID == Integer.parseInt(studentsArr[0])) {
                    notFound = false;
                } else {
                    writerStudents.println(lineStudents);
                }
                lineStudents = readerStudents.readLine();
            }
            if (notFound) {
                tempStudents.deleteOnExit();
                tempGrades.deleteOnExit();
                System.out.println("Student does not exist.");
                return;
            }
            String lineGrades = readerGrades.readLine();
            while (lineGrades != null) {
                String[] gradesArr = lineGrades.split(",");
                if (studentID != Integer.parseInt(gradesArr[0])) {
                    writerGrades.println(lineGrades);
                }
                lineGrades = readerGrades.readLine();
            }
        } catch (FileNotFoundException fnfEx) {
            fnfEx.printStackTrace();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
        if (students.delete()) {
            tempStudents.renameTo(students);
        }
        if (grades.delete()) {
            tempGrades.renameTo(grades);
        }
    }

    private static void insertStudent(String info, File students, File grades) {

        try (BufferedReader reader = new BufferedReader(new FileReader(students));
             PrintWriter writerStudents = new PrintWriter(new FileWriter(students, true));
             PrintWriter writerGrades = new PrintWriter(new FileWriter(grades, true))) {

            ArrayList<Integer> numbers = new ArrayList<>();
            String[] infoArr = info.split(" ");
            String lineStudents = reader.readLine();

            while (lineStudents != null) {
                String[] line = lineStudents.split(",");
                numbers.add(Integer.parseInt(line[0]));
                lineStudents = reader.readLine();
            }
            Collections.sort(numbers);
            Collections.reverse(numbers);
            int maxNum = numbers.get(0) + 1;

            String newInfo = maxNum + "," + infoArr[0] + "," + infoArr[1] + "," + infoArr[2] + "," + infoArr[3];
            writerStudents.println(newInfo);
            writerGrades.println(maxNum);
        } catch (FileNotFoundException fnfEx) {
            fnfEx.printStackTrace();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    private static void insertGrade(String gradeInfo, File grades) {

        File tempGrades = new File(grades + ".tmp");
        tempGrades.getParentFile().mkdirs();
        String[] info = gradeInfo.split(" ");
        String studentID = info[0];
        String newCourse = info[1];
        String newGrade = info[2];

        LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> gradesMap = new LinkedHashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(grades));
             PrintWriter writer = new PrintWriter(new FileWriter(tempGrades))) {

            String lineGrades = reader.readLine();
            while (lineGrades != null) {
                String[] line = lineGrades.split(",");
                String currentID = line[0];
                gradesMap.put(currentID, new LinkedHashMap<>());

                for (int i = 1; i < line.length; i++) {
                    String[] course = line[i].split(" ");
                    String courseName = course[0];
                    //saving all info from grades.txt to LinkedHashMap
                    if (!gradesMap.get(currentID).containsKey(courseName)) {
                        gradesMap.get(currentID).put(courseName, new ArrayList<>());
                    }
                    for (int j = 1; j < course.length; j++) {
                        gradesMap.get(currentID).get(courseName).add(course[j]);
                    }
                }
                lineGrades = reader.readLine();
            }
            if (!gradesMap.containsKey(studentID)) {
                tempGrades.deleteOnExit();
                System.out.println("Student does not exist.");
                return;
            }
            if (!gradesMap.get(studentID).containsKey(newCourse)) {
                gradesMap.get(studentID).put(newCourse, new ArrayList<>());
            }
            gradesMap.get(studentID).get(newCourse).add(newGrade);

            for (Map.Entry entry1 : gradesMap.entrySet()) {//printing updated HashMap in file
                StringBuilder builder = new StringBuilder();
                builder.append(entry1.getKey());
                for (Map.Entry entry2 : gradesMap.get(entry1.getKey()).entrySet()) {
                    builder.append(',');
                    builder.append(entry2.getKey());
                    ArrayList current = gradesMap.get(entry1.getKey()).get(entry2.getKey());
                    for (int i = 0; i < current.size(); i++) {
                        builder.append(' ');
                        builder.append(current.get(i));
                    }
                }
                String lineToPrint = builder.toString();
                writer.println(lineToPrint);
            }

        } catch (FileNotFoundException fnfEx) {
            fnfEx.printStackTrace();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
        if (grades.delete()) {
            tempGrades.renameTo(grades);
        }
    }
}

