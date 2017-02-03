package greetings;

import utils.WebUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class GreetMe {

    private static final String FILE_PATH =
            "E:\\GitHub\\SoftUni\\03.Web_Fundamentals_2017\\01.Java_Web_Development_Basics\\" +
                    "02.HTTP_Protocol_Exercise\\HomeworkHTTPProtocol\\src\\main\\" +
                    "resources\\static\\html\\greetings\\files\\userdata.txt";

    public static void main(String[] args) {
        setContent();
        greetMe();
    }

    private static void greetMe() {

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {

            Map<String, String> params = WebUtils.getParameters();

            String[] userParams = reader.readLine().split(" ");
            String firstName = userParams[0];
            String lastName = userParams[1];
            String age = params.get("age");

            System.out.printf("Hello %s %s at age %s!%n", firstName, lastName, age);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setContent() {
        System.out.println("Content-Type: text/html\n");
    }
}
