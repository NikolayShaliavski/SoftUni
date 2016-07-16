import java.io.*;

class Course implements  Serializable{
    String name;
    int students;
}

public class CustomObjectInFile {

    public static void main(String[] args) {

        Course course = new Course();
        course.name = "Java Fundamentals March 2016";
        course.students = 200;

        File courseFile = new File("resourses\\course");

        try (ObjectOutputStream outObj = new ObjectOutputStream(new FileOutputStream(courseFile));
             ObjectInputStream inObj = new ObjectInputStream(new FileInputStream(courseFile))) {

            outObj.writeObject(course);

            Course toPrint = (Course) inObj.readObject();

            System.out.println(toPrint.name);
            System.out.println(toPrint.students);

        } catch (FileNotFoundException fnfEx) {
            fnfEx.printStackTrace();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } catch (ClassNotFoundException cnfEx) {
            cnfEx.printStackTrace();
        }

    }

}


