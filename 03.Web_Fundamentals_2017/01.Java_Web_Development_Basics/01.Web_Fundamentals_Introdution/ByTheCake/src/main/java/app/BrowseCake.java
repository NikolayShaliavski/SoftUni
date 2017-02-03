package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class BrowseCake {

    private static final String BROWSE_CAKE_PATH =
             "E:\\GitHub\\SoftUni\\03.Web_Fundamentals_2017\\01.Java_Web_Development_Basics" +
             "\\01.Web_Fundamentals_Introdution\\ByTheCake\\src\\main\\resources\\static" +
             "\\html\\browse-cake.html";
    private static final String DATAFILE_PATH =
            "C:\\Apache24\\htdocs\\01.Cake-page\\database\\database.txt";

    public static void main(String[] args) {
        setContent();
        String html = HTMLReader.readHTML(BROWSE_CAKE_PATH);
        System.out.println(html);

        String searchContent = System.getProperty("cgi.query_string");
        String value =
                searchContent.substring(searchContent.indexOf('=') + 1).toLowerCase();

        if (value.equals("")) {
            return;
        }
        Map<String, Set<String>> results = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(DATAFILE_PATH))) {

            String line = reader.readLine();
            while (line != null) {
                if (line.toLowerCase().contains(value)) {
                    String[] params = line.split(", ");
                    String name = params[0];
                    String price = params[1];
                    if (!results.containsKey(name)) {
                        results.put(name, new TreeSet<String>());
                    }
                    results.get(name).add(price);
                }

                line = reader.readLine();
            }

            if (results.size() == 0) {
                System.out.println("No results matched.");
            }
            for(Map.Entry<String, Set<String>> cakePair : results.entrySet()) {
                String cakeName = cakePair.getKey();
                for (String price : cakePair.getValue()) {
                    System.out.printf("Name: %s Price: %s<br/>", cakeName, price);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setContent() {
        String content = "Content-type: text/html\n";
        System.out.println(content);
    }
}
