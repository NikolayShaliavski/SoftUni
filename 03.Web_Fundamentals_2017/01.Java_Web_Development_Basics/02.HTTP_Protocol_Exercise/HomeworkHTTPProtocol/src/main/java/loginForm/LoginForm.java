package loginForm;

import utils.WebUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginForm {

    private static final String LOGIN_HTML_PATH =
            "E:\\GitHub\\SoftUni\\03.Web_Fundamentals_2017\\01.Java_Web_Development_Basics\\" +
            "02.HTTP_Protocol_Exercise\\HomeworkHTTPProtocol\\src\\main\\resources\\static\\html\\login.html";

    public static void main(String[] args) {
        setContent();
        String html = WebUtils.readHTML(LOGIN_HTML_PATH);
        System.out.println(html);
        login();
    }

    private static void login() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] params = reader.readLine().split("&");

            String userName = params[0].substring(params[0].indexOf('=') + 1);
            String password = params[1].substring(params[1].indexOf('=') + 1);

            System.out.printf("Hi %s, your password is %s<br/>", userName, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setContent() {
        System.out.println("Content-Type: text/html\n");
    }
}
