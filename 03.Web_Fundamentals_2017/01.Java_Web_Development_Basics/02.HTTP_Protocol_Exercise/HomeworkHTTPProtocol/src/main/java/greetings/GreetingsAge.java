package greetings;

import fileWriters.GreetingsFileWriter;
import utils.WebUtils;

import java.util.Map;

public class GreetingsAge {

    private static final String GREETINS_AGE =
            "E:\\GitHub\\SoftUni\\03.Web_Fundamentals_2017\\01.Java_Web_Development_Basics" +
                    "\\02.HTTP_Protocol_Exercise\\HomeworkHTTPProtocol\\src\\main\\" +
                        "resources\\static\\html\\greetings\\greetings-age.html";

    private static final String FILE_PATH =
            "E:\\GitHub\\SoftUni\\03.Web_Fundamentals_2017\\01.Java_Web_Development_Basics\\" +
                    "02.HTTP_Protocol_Exercise\\HomeworkHTTPProtocol\\src\\main\\" +
                    "resources\\static\\html\\greetings\\files\\userdata.txt";

    public static void main(String[] args) {
        setContent();
        String html = WebUtils.readHTML(GREETINS_AGE);
        System.out.println(html);

        saveLastName();
    }

    private static void saveLastName() {
        Map<String, String> params = WebUtils.getParameters();

        String lastName = params.get("last-name");
        GreetingsFileWriter.append(FILE_PATH, lastName, true);
        System.out.println(lastName);
    }

    private static void setContent() {
        System.out.println("Content-Type: text/html\n");
    }
}
