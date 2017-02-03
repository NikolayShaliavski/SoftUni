package email;

import utils.WebUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SendEmailForm {

    public static final String USER_NAME = "suAdmin";
    public static final String PASSWORD = "aDmInPw17";

    private static final String SEND_EMAIL_HTML_PATH =
            "E:\\GitHub\\SoftUni\\03.Web_Fundamentals_2017\\" +
                    "01.Java_Web_Development_Basics\\02.HTTP_Protocol_Exercise\\" +
                    "HomeworkHTTPProtocol\\src\\main\\resources\\static\\html\\email\\send-email.html";

    public static final String EMAIL_REGEX = "^([a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-z]{2,6})$";

    public static void main(String[] args) {
        setContent();

        String html = WebUtils.readHTML(SEND_EMAIL_HTML_PATH);
        System.out.println(html);

        trySendEmail();
    }

    private static void trySendEmail() {
        Map<String, String> emailParams = WebUtils.getParameters();
        String receiver = emailParams.get("receiver");
        String subject = emailParams.get("subject");
        String message = emailParams.get("message");

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(receiver);
        if (!matcher.find()) {
            System.out.println("Invalid email address.");
            return;
        }
        if (subject.length() > 100) {
            System.out.println("Subject cannot be more than 100 symbols.");
            return;
        }

        System.out.printf("Send email to %s...%n", receiver);
    }

    private static void setContent() {
        System.out.println("Content-Type: text/html\n");
    }
}
