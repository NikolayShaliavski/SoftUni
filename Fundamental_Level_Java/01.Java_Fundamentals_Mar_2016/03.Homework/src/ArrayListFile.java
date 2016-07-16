import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListFile {

    public static void main(String[] args) {

        File doubles = new File("resourses\\doubles");

        try (ObjectOutputStream outObj = new ObjectOutputStream(new FileOutputStream(doubles));
             ObjectInputStream inObj = new ObjectInputStream(new FileInputStream(doubles));
        ) {

            ArrayList<Double> collection = new ArrayList<>(Arrays.asList(3.14, 2.25, 3.659, 0.698));

            outObj.writeObject(collection);

            System.out.println(inObj.readObject());
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } catch (ClassNotFoundException cnfEx) {
            cnfEx.printStackTrace();
        }
    }
}
