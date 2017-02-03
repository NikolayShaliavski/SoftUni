package app;

import java.io.*;
import java.net.URLDecoder;

public class AddCake {

    private static final String ADD_CAKE_PATH =
            "E:\\GitHub\\SoftUni\\03.Web_Fundamentals_2017\\01.Java_Web_Development_Basics" +
            "\\01.Web_Fundamentals_Introdution\\ByTheCake\\src\\main\\resources\\static" +
            "\\html\\add-cake.html";
    private static final String DATAFILE_PATH =
            "C:\\Apache24\\htdocs\\01.Cake-page\\database\\database.txt";

    public static void main(String[] args) throws IOException {
        setContent();
        String html = HTMLReader.readHTML(ADD_CAKE_PATH);
        System.out.println(html);
        writeInDatabase();
    }

    private static void writeInDatabase() throws IOException {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(
                     new OutputStreamWriter(
                             new FileOutputStream(DATAFILE_PATH, true), "UTF-8"))) {

            String[] params = br.readLine().split("&");

            String name = params[0].substring(params[0].indexOf('=') + 1);
            name = URLDecoder.decode(name, "UTF-8");
            String priceString = params[1].substring(params[1].indexOf('=') + 1);

            try {
                Double price = Double.parseDouble(priceString);
                String out = name + ", " + price;
                writer.write(out);
                writer.newLine();
                System.out.printf("Name: %s<br/>Price: %.2f", name, price);

            } catch (NumberFormatException nex) {
                System.out.println("Invalid price.");
            }
        }
    }

    private static void setContent() {
        String content = "Content-type: text/html\n";
        System.out.println(content);
    }
}
