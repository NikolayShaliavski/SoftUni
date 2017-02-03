package email;

import utils.WebUtils;

import java.util.Map;

import static email.SendEmailForm.PASSWORD;
import static email.SendEmailForm.USER_NAME;

public class LoginCheck {

    public static void main(String[] args) {
        boolean shouldRedirect = hasError();
        setContent(shouldRedirect);
    }

    private static boolean hasError() {
        Map<String, String> params = WebUtils.getParameters();

        String userName = params.get("username");
        String password = params.get("password");

        return !USER_NAME.equals(userName) || !PASSWORD.equals(password);
    }

    private static void setContent(boolean shouldRedirect) {
        System.out.println("Content-Type: text/html");
        String location;
        if (shouldRedirect) {
            location = "Location: email-login.cgi?error=true";
            System.out.println(location);
        } else {
            location = "Location: send-email.cgi?error=false";
            System.out.println(location);
        }
        System.out.println();
    }
}
