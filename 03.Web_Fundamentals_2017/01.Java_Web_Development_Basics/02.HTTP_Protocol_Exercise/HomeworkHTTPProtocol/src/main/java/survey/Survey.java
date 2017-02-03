package survey;

import fileWriters.SurveyFileWriter;
import utils.WebUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class Survey {

    private static final String SURVEY_HTML_PATH =
            "E:\\GitHub\\SoftUni\\03.Web_Fundamentals_2017\\" +
                    "01.Java_Web_Development_Basics\\02.HTTP_Protocol_Exercise\\" +
                    "HomeworkHTTPProtocol\\src\\main\\resources\\static\\html\\survey\\survey-index.html";

    private static final String SURVEY_FILE_PATH =
            "E:\\GitHub\\SoftUni\\03.Web_Fundamentals_2017\\" +
                    "01.Java_Web_Development_Basics\\02.HTTP_Protocol_Exercise" +
                        "\\HomeworkHTTPProtocol\\src\\main\\resources\\static\\html\\survey\\files\\survey-results.csv";
    public static void main(String[] args) {
        setContent();
        String html = WebUtils.readHTML(SURVEY_HTML_PATH);
        System.out.println(html);

        saveSurvey();
    }

    private static void saveSurvey() {
        String paramsLine = "";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            paramsLine = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] params = new String[0];
        try {
            params = URLDecoder.decode(paramsLine, "UTF-8").split("&");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String firstName = params[0].substring(params[0].indexOf('=') + 1);
        String lastName = params[1].substring(params[1].indexOf('=') + 1);
        String birthday = params[2].substring(params[2].indexOf('=') + 1);
        String gender = params[3].substring(params[3].indexOf('=') + 1);
        String status = params[4].substring(params[4].indexOf('=') + 1);
        String recommendations = params[5].substring(params[5].indexOf('=') + 1);
        if (recommendations.equals("")) {
            recommendations = "none";
        }

        List<String> items = new ArrayList<>();
        for (int i = 6; i < params.length; i++) {
            String item = params[i].substring(params[i].indexOf('=') + 1);
            items.add(item);
        }

        String itemsJoined = "";
        if (items.size() == 0) {
            itemsJoined = "none";
        } else {
            itemsJoined = String.join(", ", items);
        }
        StringBuilder builder = new StringBuilder();
        builder.append("First name: " + firstName).append(System.lineSeparator());
        builder.append("Last name: " + lastName).append(System.lineSeparator());
        builder.append("Birthday: " + birthday).append(System.lineSeparator());
        builder.append("Gender: " + gender).append(System.lineSeparator());
        builder.append("Status: " + status).append(System.lineSeparator());
        builder.append("Recommendations: " + recommendations).append(System.lineSeparator());
        builder.append("Items: " + itemsJoined + "\n\n");

        SurveyFileWriter.write(builder.toString(), SURVEY_FILE_PATH);
    }

    private static void setContent() {
        System.out.println("Content-Type: text/html\n");
    }
}
