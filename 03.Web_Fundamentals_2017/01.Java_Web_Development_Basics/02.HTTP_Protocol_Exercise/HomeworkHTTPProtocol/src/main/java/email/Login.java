package email;

import utils.WebUtils;

import java.util.Map;

public class Login {

    private static final String LOGIN_HTML_PATH =
            "E:\\GitHub\\SoftUni\\03.Web_Fundamentals_2017\\" +
                    "01.Java_Web_Development_Basics\\02.HTTP_Protocol_Exercise\\" +
                        "HomeworkHTTPProtocol\\src\\main\\resources\\static\\html\\email\\email-login.html";

    public static void main(String[] args) {
        setContent();
        String html = WebUtils.readHTML(LOGIN_HTML_PATH);
        System.out.println(html);

        Map<String, String> params = WebUtils.getParameters();
        String error = params.get("error");
        if ("true".equals(error)) {
            System.out.println("Invalid username or password!");
        }
    }

    private static void setContent() {
        System.out.println("Content-Type: text/html\n");
    }
}
