import java.io.*;

public class AllCapitals {

    //check first SumLines, because after this code file lines.txt will be overwritten UPPERCASE!!!!!!!!!!!!!!

    public static void main(String[] args) {

        File path = new File("resourses\\lines.txt");
        File newPath = new File(path + ".tmp");
        newPath.getParentFile().mkdirs();

        try(BufferedReader reader = new BufferedReader(new FileReader(path));
            PrintWriter writer = new PrintWriter(new FileWriter(newPath, false))) {

            String line = reader.readLine();

            while (line != null) {
                line = line.toUpperCase();

                writer.println(line);
                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean del = path.delete();
        if (del) {
            newPath.renameTo(path);
        }
    }
}
