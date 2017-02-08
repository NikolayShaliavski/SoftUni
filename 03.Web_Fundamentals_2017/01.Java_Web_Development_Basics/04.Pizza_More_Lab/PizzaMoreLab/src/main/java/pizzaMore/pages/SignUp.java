package pizzaMore.pages;

import pizzaMore.models.entities.User;
import pizzaMore.models.header.Header;
import pizzaMore.repositories.UserRepository;
import pizzaMore.utils.WebUtils;

import java.util.HashMap;
import java.util.Map;

public class SignUp {

    private static final String SIGN_UP_HTML =
            "E:\\GitHub\\SoftUni\\03.Web_Fundamentals_2017" +
                    "\\01.Java_Web_Development_Basics\\04.Pizza_More_Lab\\" +
                    "PizzaMoreLab\\src\\main\\resources\\static\\html\\sign-up.html";

    private static Map<String, String> parameters;
    private static Header header;
    private static UserRepository userRepository;

    static {
        parameters = new HashMap<>();
        header = new Header();
        userRepository = new UserRepository();
    }

    public static void main(String[] args) {
        readParameters();
        header.printHeader();
        readHtml();
    }

    private static void readHtml() {
        String html = WebUtils.readHTML(SIGN_UP_HTML);
        System.out.println(html);
    }

    public static void readParameters() {
        parameters = WebUtils.getParameters();
        String username = null;
        String password = null;

        for (String param : parameters.keySet()) {
            switch (param) {
                case "username":
                    username = parameters.get("username");
                    break;
                case "password":
                    password = parameters.get("password");
                    break;
                case "signup":
                    if (username.isEmpty() || password.isEmpty()) {
                        return;
                    }
                    User user = new User(username, password);
                    userRepository.createUser(user);
                    header.addLocation("http://localhost/cgi-bin/04.Pizza_More_Lab/sign-in.cgi");
                    break;
                case "home":
                    header.addLocation("http://localhost/cgi-bin/04.Pizza_More_Lab/home.cgi");
                    break;
            }
        }
    }
}
