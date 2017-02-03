package greetings;

import fileWriters.GreetingsFileWriter;
import utils.WebUtils;

import java.util.Map;

public class GreetingsLastName {

    private static final String GREETINS_LAST_NAME_HTML =
            "E:\\GitHub\\SoftUni\\03.Web_Fundamentals_2017\\01.Java_Web_Development_Basics" +
                    "\\02.HTTP_Protocol_Exercise\\HomeworkHTTPProtocol\\src\\main\\" +
                        "resources\\static\\html\\greetings\\greetings-last-name.html";

    private static final String FILE_PATH =
            "E:\\GitHub\\SoftUni\\03.Web_Fundamentals_2017\\01.Java_Web_Development_Basics\\" +
                    "02.HTTP_Protocol_Exercise\\HomeworkHTTPProtocol\\src\\main\\" +
                    "resources\\static\\html\\greetings\\files\\userdata.txt";

    public static void main(String[] args) {
        setContent();
        String html = WebUtils.readHTML(GREETINS_LAST_NAME_HTML);
        System.out.println(html);

        saveFirstName();
    }

    private static void saveFirstName() {
        Map<String, String> params = WebUtils.getParameters();

        String firstName = params.get("first-name");
        GreetingsFileWriter.append(FILE_PATH, firstName, false);
        System.out.println(firstName);
    }

    private static void setContent() {
        System.out.println("Content-Type: text/html\n");
    }
}
