package pizzaMore.pages;

import pizzaMore.models.cookies.Cookie;
import pizzaMore.models.entities.User;
import pizzaMore.models.header.Header;
import pizzaMore.models.sessions.Session;
import pizzaMore.repositories.SessionRepository;
import pizzaMore.repositories.UserRepository;
import pizzaMore.utils.WebUtils;

import java.util.HashMap;
import java.util.Map;

public class SignIn {

    private static final String SIGN_IN_HTML =
            "E:\\GitHub\\SoftUni\\03.Web_Fundamentals_2017" +
                    "\\01.Java_Web_Development_Basics\\04.Pizza_More_Lab\\" +
                    "PizzaMoreLab\\src\\main\\resources\\static\\html\\sign-in.html";

    private static Map<String, String> parameters;
    private static Map<String, Cookie> cookies;
    private static Header header;
    private static UserRepository userRepository;
    private static SessionRepository sessionRepository;

    static {
        parameters = new HashMap<>();
        cookies = new HashMap<>();
        header = new Header();
        userRepository = new UserRepository();
        sessionRepository = new SessionRepository();
    }

    public static void main(String[] args) {
        readParameters();
        header.printHeader();
        readHtml();
    }

    private static void readHtml() {
        String html = WebUtils.readHTML(SIGN_IN_HTML);
        System.out.println(html);
    }

    public static void readParameters() {
        parameters = WebUtils.getParameters();
        for (String param : parameters.keySet()) {
            switch (param) {
                case "signin":
                    signIn();
                    break;
                case "home":
                    goToHome();
                    break;
            }
        }
    }

    private static void goToHome() {
        header.addLocation("http://localhost/cgi-bin/04.Pizza_More_Lab/home.cgi");
    }

    private static void signIn() {
        String username = parameters.get("username");
        String password = parameters.get("password");

        User user = userRepository.findByUsernameAndPassword(username, password);

        if (user != null) {
            Session userSession = new Session();
            userSession.addData("username", username);
            long sid = sessionRepository.createSession(userSession);
            Cookie sessionCookie = new Cookie("sid", String.valueOf(sid));
            header.addCookie(sessionCookie);
            Cookie rememberCookie = new Cookie("rememberme", "on");
            header.addCookie(rememberCookie);
            header.addLocation("http://localhost/cgi-bin/04.Pizza_More_Lab/home.cgi");
        }
    }
}
