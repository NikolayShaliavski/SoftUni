package pizzaMore.pages;

import pizzaMore.models.cookies.Cookie;
import pizzaMore.models.header.Header;
import pizzaMore.models.sessions.Session;
import pizzaMore.models.sessions.SessionData;
import pizzaMore.repositories.SessionRepository;
import pizzaMore.utils.WebUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Home {

    private static final String HOME_HTML_EN =
            "E:\\GitHub\\SoftUni\\03.Web_Fundamentals_2017" +
            "\\01.Java_Web_Development_Basics\\04.Pizza_More_Lab\\" +
                    "PizzaMoreLab\\src\\main\\resources\\static\\html\\home-en.html";

    private static final String HOME_HTML_DE =
            "E:\\GitHub\\SoftUni\\03.Web_Fundamentals_2017" +
                    "\\01.Java_Web_Development_Basics\\04.Pizza_More_Lab\\" +
                    "PizzaMoreLab\\src\\main\\resources\\static\\html\\home-de.html";

    private static Map<String, String> parameters;
    private static Map<String, Cookie> cookies;
    private static Header header;
    private static SessionRepository sessionRepository;

    static {
        Home.parameters = new HashMap<>();
        Home.cookies = new HashMap<>();
        Home.header = new Header();
        sessionRepository = new SessionRepository();
    }

    public static void main(String[] args) {
        readParameters();
        header.printHeader();
        readCookies(args);
        readHtml();
    }

    public static void readParameters() {
        parameters = WebUtils.getParameters();
        for (String param : parameters.keySet()) {
            switch (param) {
                case "language":
                    String value = parameters.get("language");
                    setCookie("lang", value);
                    break;
                case "signin":
                    goToSignIn();
                    break;
                case "signup":
                    goToSignUp();
                    break;
            }
        }
    }

    private static void signOut(long id) {
        sessionRepository.delete(id);
    }

    private static void goToSignUp() {
        header.addLocation("http://localhost/cgi-bin/04.Pizza_More_Lab/sign-up.cgi");
    }

    private static void goToSignIn() {
        header.addLocation("http://localhost/cgi-bin/04.Pizza_More_Lab/sign-in.cgi");
    }

    private static void setCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        header.addCookie(cookie);
        cookies.put(key, cookie);
    }

    private static void readCookies(String[] args) {
        if (args.length == 0) {
            return;
        }

        for (String incomingCookie : args) {
            String[] tokens = incomingCookie.split("=");
            String key = tokens[0];
            String value = tokens[1];
            value = value.replace(";", "");

            Cookie cookie = new Cookie(key, value);
            cookies.put(key, cookie);
        }
    }

    private static void readHtml() {

        Cookie sessionCookie = cookies.get("sid");
        String username = null;

        if (sessionCookie != null) {
            long sid = Long.parseLong(sessionCookie.getValue());
            Session session = sessionRepository.findById(sid);

            //Sign out
            if (parameters.containsKey("signout")) {
                signOut(sid);
                session = null;
            }

            if (session != null) {
                Set<SessionData> sessionData = session.getSessionData();
                for (SessionData data : sessionData) {
                    if (data.getKey().equals("username")) {
                        username = data.getValue();
                    }
                }
            }
        }
        Cookie languageCookie;
        if (!WebUtils.isPost()) {
            if (cookies.containsKey("lang")) {
                languageCookie = cookies.get("lang");
                if (languageCookie.getValue().equals("DE")) {
                    readHtmlDE(username);
                } else {
                    readHtmlEN(username);
                }
            } else {
                readHtmlEN(username);
            }
        } else {
            if (parameters.containsKey("language")) {
                String language = parameters.get("language");
                if (language.equals("DE")) {
                    readHtmlDE(username);
                } else {
                    readHtmlEN(username);
                }
            } else {
                readHtmlEN(username);
            }
        }
    }

    private static void readHtmlEN(String username) {
        String html = WebUtils.readHTML(HOME_HTML_EN);

        String name = "signin";
        String value = "Sign In";
        if (username != null) {
            name = "signout";
            value = "Sign Out(" + username + ")";
        }
//        System.out.println(String.format(html, ENGLISH, ENGLISH_SIGN_IN, ENGLISH_SIGN_UP));
        System.out.println(String.format(html, name, value));
    }

    private static void readHtmlDE(String username) {
        String html = WebUtils.readHTML(HOME_HTML_DE);

        String name = "signin";
        String value = "Einloggen";
        if (username != null) {
            name = "signout";
            value = "Ausloggen(" + username + ")";
        }
//        System.out.println(String.format(html, GERMAN, GERMAN_SIGN_IN, GERMAN_SIGN_UP));
        System.out.println(String.format(html, name, value));
    }
}
